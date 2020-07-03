package com.taobao;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.taobao.entity.ColumnInfo;
import com.taobao.utils.ActivityUtils;
import com.taobao.view.ListViewItem;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class HomeActivity extends BaseActivity {
	ListView mainList;
	List<ColumnInfo> list;
	String[] types = new String[]{"热卖","最新","优惠","推荐"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		mainList = (ListView) findViewById(R.id.main_list);
		list = new ArrayList<ColumnInfo>();
		list.add(new ColumnInfo(types[0]+"商品", R.drawable.hots));
		list.add(new ColumnInfo(types[1]+"商品", R.drawable.news));
		list.add(new ColumnInfo(types[2]+"商品", R.drawable.youhui));
		list.add(new ColumnInfo(types[3]+"商品", R.drawable.tuijian));
		mainList.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				final ColumnInfo columnInfo = list.get(position);
				ListViewItem item = new ListViewItem(HomeActivity.this, columnInfo);
				item.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(HomeActivity.this, ShopActivity.class);
						intent.putExtra("flag", 0);
						intent.putExtra("type",types[position]);
						startActivity(intent);
					}
				});
				return item;
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
				return list.size();
			}
		});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			ActivityUtils.getInstance().ConfirmExit(this);
		}
		return super.onKeyDown(keyCode, event);
	}
}
