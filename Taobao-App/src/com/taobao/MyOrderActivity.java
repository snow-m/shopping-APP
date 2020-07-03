package com.taobao;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.taobao.bll.DataProcess;
import com.taobao.entity.OrderJson;
import com.taobao.utils.ResourcePool;
import com.taobao.utils.UrlUtil;

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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyOrderActivity extends BaseActivity {
	@ViewInject(R.id.title)TextView title;
	@ViewInject(R.id.back_btn)Button back;
	@ViewInject(R.id.btnRight)Button btnRight;
	@ViewInject(R.id.main_list)ListView orderList;
	@ViewInject(R.id.title_layout)RelativeLayout titleLayout;
	private List<OrderJson> orders;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		titleLayout.setVisibility(View.VISIBLE);
		title.setVisibility(View.VISIBLE);
		title.setText("我的订单");
		btnRight.setVisibility(View.GONE);
		orderList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				OrderJson order = orders.get(position);
				Intent intent = new Intent(MyOrderActivity.this, OrderDetailActivity.class);
				intent.putExtra("orderId", order.getId());
				startActivity(intent);
			}
		});
		orderList.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				OrderJson order = orders.get(position);
				View view = LayoutInflater.from(MyOrderActivity.this).inflate(R.layout.order_item, null);
				TextView orderId = (TextView) view.findViewById(R.id.orderId);
				TextView orderPrice = (TextView) view.findViewById(R.id.orderPrice);
				orderId.setText(order.getTime());
				orderPrice.setText("￥"+order.getTotal()+"元");
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
				return orders==null?0:orders.size();
			}
		});
		new GetOrders().execute("");
	}
	public void back(View view) {
		// TODO Auto-generated method stub
		finish();
	}
	class GetOrders extends AsyncTask<String, Boolean, List<OrderJson>>{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		@Override
		protected List<OrderJson> doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			List<OrderJson> result= DataProcess.getOrders(UrlUtil.GET_ORDER, getNameValuePair());
			return result;
		}
		@Override
		protected void onPostExecute(List<OrderJson> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result!=null){
				orders = result;
				((BaseAdapter)orderList.getAdapter()).notifyDataSetChanged();
			}
		}
	}
	private List<NameValuePair> getNameValuePair() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userId", ""+ResourcePool.getInstance().getUser().getId()));
		return params;
	}
}
