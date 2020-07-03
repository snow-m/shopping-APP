package com.taobao;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.taobao.entity.Collection;
import com.taobao.entity.Shop;
import com.taobao.utils.ResourcePool;
import com.taobao.view.ShopItem;

@SuppressLint("SimpleDateFormat")
public class CollectionActivity extends BaseActivity {
	@ViewInject(R.id.title)TextView title;
	@ViewInject(R.id.back_btn)Button back;
	@ViewInject(R.id.btnRight)Button btnRight;
	@ViewInject(R.id.title_layout)RelativeLayout titleLayout;
	@ViewInject(R.id.main_list)ListView shopsList;
	@ViewInject(R.id.noContent)TextView noContent;
	@ViewInject(R.id.bottom)LinearLayout bottom;
	@ViewInject(R.id.no)Button n0;
	@ViewInject(R.id.yes)Button yes;

	private List<Collection> collections ;
	private List<Collection> buyShops = new ArrayList<Collection>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		titleLayout.setVisibility(View.VISIBLE);
		back.setVisibility(View.VISIBLE);
		btnRight.setVisibility(View.GONE);
		title.setText("我的收藏");
		bottom.setVisibility(View.VISIBLE);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		buyShops = new ArrayList<Collection>();
		try {
			collections = dbUtils.findAll(Selector.from(Collection.class).where("username","=",sp.getString("username", "")));
			if(collections==null){
				collections = new ArrayList<Collection>();
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			collections = new ArrayList<Collection>();
		}
		if(collections.size()==0){
			noContent.setText("暂无收藏");
			noContent.setVisibility(View.VISIBLE);
		}else{
			noContent.setVisibility(View.GONE);
		}
		shopsList.setAdapter(new BaseAdapter() {

			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				final Collection collection = collections.get(position);
				final Shop shop = new Shop(collection.getImg(), collection.getImg2(), collection.getDescp(), collection.getPrice(), "", "",collection.num);
				ShopItem shopItem = new ShopItem(CollectionActivity.this, shop,new ShopItem.OnChangeNum() {
					
					@Override
					public void onMinus() {
						// TODO Auto-generated method stub
						if(collection.num>1){
							collection.setNum(collection.num-1);
							notifyDataSetChanged();
						}else{
							Toast.makeText(CollectionActivity.this, "数量至少是1", 0).show();
						}
					}
					@Override
					public void onAdd() {
						// TODO Auto-generated method stub
						collection.setNum(collection.num+1);
						notifyDataSetChanged();
					}
				});
				shopItem.setCheckBoxVisible(buyShops.contains(collection));
				shopItem.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(buyShops.contains( collections.get(position))){
							buyShops.remove(collections.get(position));
						}else{
							buyShops.add(collections.get(position));
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
				return collections.size();
			}
		});
	}
	public void back(View view) {
		finish();
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
			buyShops = new ArrayList<Collection>();
			collections = dbUtils.findAll(Selector.from(Collection.class).where("username","=",sp.getString("username", "")));
			if(collections==null){
				collections = new ArrayList<Collection>();
			}
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			collections = new ArrayList<Collection>();
		}
		((BaseAdapter)shopsList.getAdapter()).notifyDataSetChanged();
		if(collections.size()==0){
			noContent.setText("暂无收藏");
			noContent.setVisibility(View.VISIBLE);
		}else{
			noContent.setVisibility(View.GONE);
		}
	}
	public void yes(View view) {
		// TODO Auto-generated method stub
		if(buyShops.size()==0){
			Toast.makeText(this, "请先选择要购买的商品！", 0).show();
			return;
		}
		ResourcePool.getInstance().setBuyCollectionList(buyShops);
		Intent intent = new Intent(this, SubmitCollectionOrderActivity.class);
		startActivity(intent);
	}
}
