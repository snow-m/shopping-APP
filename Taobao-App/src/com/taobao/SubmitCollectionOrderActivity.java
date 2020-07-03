package com.taobao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.taobao.entity.Collection;
import com.taobao.entity.OrderShop;
import com.taobao.entity.Shop;
import com.taobao.entity.Torll;
import com.taobao.utils.ResourcePool;
import com.taobao.view.MyProgressDialog;
import com.taobao.view.ShopItem;

public class SubmitCollectionOrderActivity extends BaseActivity {
	@ViewInject(R.id.title)TextView title;
	@ViewInject(R.id.back_btn)Button back;
	@ViewInject(R.id.btnRight)Button btnRight;
	@ViewInject(R.id.main_list)ListView shopsList;
	@ViewInject(R.id.title_layout)RelativeLayout titleLayout;
	private List<Collection> shops ;
	@ViewInject(R.id.bottom)LinearLayout bottom;
	@ViewInject(R.id.no)Button n0;
	@ViewInject(R.id.yes)Button yes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		bottom.setVisibility(View.VISIBLE);
		n0.setText("取消");
		yes.setText("确认订单");
		titleLayout.setVisibility(View.VISIBLE);
		title.setVisibility(View.VISIBLE);
		title.setText("确认订单");
		btnRight.setVisibility(View.GONE);
		shops = ResourcePool.getInstance().getBuyCollectionList();
		shopsList.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
					final Collection collection = shops.get(position);
					final Shop shop = new Shop(collection.getImg(), collection.getImg2(), collection.getDescp(), collection.getPrice(), "", "",collection.num);
					ShopItem shopItem = new ShopItem(SubmitCollectionOrderActivity.this, shop,new ShopItem.OnChangeNum() {
						@Override
						public void onMinus() {
							// TODO Auto-generated method stub
							if(collection.num>1){
								collection.setNum(collection.num-1);
								notifyDataSetChanged();
							}else{
								Toast.makeText(SubmitCollectionOrderActivity.this, "数量至少是1", 0).show();
							}
						}
						@Override
						public void onAdd() {
							// TODO Auto-generated method stub
							collection.setNum(collection.num+1);
							notifyDataSetChanged();
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
				return shops.size();
			}
		});
	}
	
	public void back(View view) {
		finish();
		ResourcePool.getInstance().setBuyList(new ArrayList<Torll>());
	}
	public void cancel(View view) {
		finish();
		ResourcePool.getInstance().setBuyList(new ArrayList<Torll>());
	}
	
	public void yes(View view) {
		List<OrderShop> list = new ArrayList<OrderShop>();
		float price = 0;
		for(Collection collection : shops){
				price +=  Double.parseDouble(collection.getPrice());
				list.add(new OrderShop(collection.shopId, collection.num));
		}
		long number = System.currentTimeMillis();
		String detail = "";
		String total = price+"";
		String shops = JSON.toJSONString(list);
		Intent intent = new Intent(this, PaymentActivity.class);
		intent.putExtra("number", ""+number);
		intent.putExtra("userId", ""+ResourcePool.getInstance().getUser().getId());
		intent.putExtra("detail", "");
		intent.putExtra("total", ""+total);
		intent.putExtra("shops", shops);
		startActivity(intent);
		finish();
		ResourcePool.getInstance().setBuyList(new ArrayList<Torll>());
	}
}
