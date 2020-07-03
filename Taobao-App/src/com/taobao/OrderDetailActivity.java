package com.taobao;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.taobao.bll.DataProcess;
import com.taobao.entity.OrderJson;
import com.taobao.entity.Shop;
import com.taobao.utils.AsynImageLoader;
import com.taobao.utils.ResourcePool;
import com.taobao.utils.UrlUtil;
import com.taobao.view.MyProgressDialog;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class OrderDetailActivity extends BaseActivity {
	@ViewInject(R.id.title)TextView title;
	@ViewInject(R.id.back_btn)Button back;
	@ViewInject(R.id.btnRight)Button btnRight;
	@ViewInject(R.id.main_list)ListView shopList;
	@ViewInject(R.id.title_layout)RelativeLayout titleLayout;
	private List<Shop> shops;
	private int orderId;
	private MyProgressDialog dialog = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		dialog = MyProgressDialog.getInstance(this);
		titleLayout.setVisibility(View.VISIBLE);
		title.setVisibility(View.VISIBLE);
		orderId = getIntent().getIntExtra("orderId", 0);
		title.setText("订单详情");
		btnRight.setVisibility(View.GONE);
		shopList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(OrderDetailActivity.this, ShopDetailActivity.class);
				intent.putExtra("shop", shops.get(position));
				startActivity(intent);
			}
		});
		shopList.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				Shop shop = shops.get(position);
				View view = LayoutInflater.from(OrderDetailActivity.this).inflate(R.layout.order_detail_item, null);
				TextView shopDesc = (TextView) view.findViewById(R.id.shop_desc);
				TextView num = (TextView) view.findViewById(R.id.goods_num);
				TextView price = (TextView) view.findViewById(R.id.price);
				ImageView shopImg = (ImageView) view.findViewById(R.id.shop_img);
				AsynImageLoader.showImageAsyn(shopImg, shop.getImg(), R.drawable.small_load, OrderDetailActivity.this);
				shopDesc.setText(shop.getDescp());
				price.setText("￥"+shop.getPrice()+"元");
				num.setText(""+shop.num);
				return view;
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
				return shops==null?0:shops.size();
			}
		});
		new GetOrders().execute("");
	}
	public void back(View view) {
		// TODO Auto-generated method stub
		finish();
	}
	class GetOrders extends AsyncTask<String, Boolean, List<Shop>>{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}
		@Override
		protected List<Shop> doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			List<Shop> result= DataProcess.getOrderShops(UrlUtil.GET_ORDER_SHOPS, getNameValuePair());
			return result;
		}
		@Override
		protected void onPostExecute(List<Shop> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result!=null){
				shops = result;
				((BaseAdapter)shopList.getAdapter()).notifyDataSetChanged();
			}
			dialog.cancel();
		}
	}
	private List<NameValuePair> getNameValuePair() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", ""+orderId));
		return params;
	}
}
