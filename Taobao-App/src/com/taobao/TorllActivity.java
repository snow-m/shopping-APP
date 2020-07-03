package com.taobao;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.taobao.entity.Shop;
import com.taobao.entity.Torll;
import com.taobao.utils.ActivityUtils;
import com.taobao.utils.ResourcePool;
import com.taobao.view.ShopItem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class TorllActivity extends BaseActivity {
	@ViewInject(R.id.main_list)ListView shopsList;
	@ViewInject(R.id.noContent)TextView noContent;
	@ViewInject(R.id.bottom)LinearLayout bottom;
	@ViewInject(R.id.no)Button n0;
	@ViewInject(R.id.yes)Button yes;
	private List<Torll> torlls ;
	private List<Torll> buyShops = new ArrayList<Torll>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		bottom.setVisibility(View.VISIBLE);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		buyShops = new ArrayList<Torll>();
		try {
			torlls = dbUtils.findAll(Selector.from(Torll.class).where("username","=",sp.getString("username", "")));
			if(torlls==null){
				torlls = new ArrayList<Torll>();
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			torlls = new ArrayList<Torll>();
		}
		if(torlls.size()==0){
			noContent.setVisibility(View.VISIBLE);
		}else{
			noContent.setVisibility(View.GONE);
		}
		shopsList.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
					final Torll torll = torlls.get(position);
					final Shop shop = new Shop(torll.getImg(), torll.getImg2(), torll.getDescp(), torll.getPrice(), "", "",torll.num);
					ShopItem shopItem = new ShopItem(TorllActivity.this, shop,new ShopItem.OnChangeNum() {
						
						@Override
						public void onMinus() {
							// TODO Auto-generated method stub
							if(torll.num>1){
								torll.setNum(torll.num-1);
								notifyDataSetChanged();
							}else{
								Toast.makeText(TorllActivity.this, "数量至少是1", 0).show();
							}
						}
						@Override
						public void onAdd() {
							// TODO Auto-generated method stub
							torll.setNum(torll.num+1);
							notifyDataSetChanged();
						}
					});
					shopItem.setCheckBoxVisible(buyShops.contains(torll));
					shopItem.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if(buyShops.contains( torlls.get(position))){
								buyShops.remove(torlls.get(position));
							}else{
								buyShops.add(torlls.get(position));
							}
							((BaseAdapter)shopsList.getAdapter()).notifyDataSetChanged();
						}
					});
					return shopItem;
			}
			
			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return torlls.size();
			}
		});
	}
	public void cancel(View view) {
		// TODO Auto-generated method stub
		try {
			if(buyShops.size()==0){
				Toast.makeText(this, "请选择要删除的商品！", 0).show();
				return;
			}

			dbUtils.deleteAll(buyShops);
			Toast.makeText(this, "删除成功！", 0).show();
			buyShops = new ArrayList<Torll>();
			torlls = dbUtils.findAll(Selector.from(Torll.class).where("username","=",sp.getString("username", "")));
			if(torlls==null){
				torlls = new ArrayList<Torll>();
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			torlls = new ArrayList<Torll>();
		}
		((BaseAdapter)shopsList.getAdapter()).notifyDataSetChanged();
		if(torlls.size()==0){
			noContent.setVisibility(View.VISIBLE);
		}else{
			noContent.setVisibility(View.GONE);
		}
	}
	public void yes(View view) {
		// TODO Auto-generated method stub
		if(buyShops.size()==0){
			Toast.makeText(this, "请先选择要购买的商品！", 0).show();
			return ;
		}
		ResourcePool.getInstance().setBuyList(buyShops);
		Intent intent = new Intent(this, SubmitOrderActivity.class);
		startActivity(intent);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			ActivityUtils.getInstance().ConfirmExit(this);
		}
		return super.onKeyDown(keyCode, event);
	}
}
