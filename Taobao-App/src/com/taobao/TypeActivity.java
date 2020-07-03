package com.taobao;

import java.util.ArrayList;
import java.util.List;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.taobao.entity.ColumnInfo;
import com.taobao.utils.ActivityUtils;
import com.taobao.view.ListViewItem;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class TypeActivity extends BaseActivity {
	@ViewInject(R.id.title)TextView title;
	@ViewInject(R.id.back_btn)Button back;
	@ViewInject(R.id.btnRight)Button btnRight;
	@ViewInject(R.id.main_list)ListView mainList;
	List<ColumnInfo> list;
	String[] types = new String[]{"服装内衣","鞋包配饰","家居生活","护肤彩妆","美食特产","手机数码"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		back.setVisibility(View.GONE);
		btnRight.setVisibility(View.VISIBLE);
		title.setText("商品分类");
		list = new ArrayList<ColumnInfo>();
		list.add(new ColumnInfo(types[0], R.drawable.type_1));
		list.add(new ColumnInfo(types[1], R.drawable.type_2));
		list.add(new ColumnInfo(types[2], R.drawable.type_3));
		list.add(new ColumnInfo(types[3], R.drawable.type_4));
		list.add(new ColumnInfo(types[4], R.drawable.type_5));
		list.add(new ColumnInfo(types[5], R.drawable.type_6));
		mainList.setAdapter(new BaseAdapter() {

			@Override
			public View getView(final int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				ColumnInfo columnInfo = list.get(position);
				ListViewItem item = new ListViewItem(TypeActivity.this, columnInfo);
				item.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(TypeActivity.this, ShopActivity.class);
						intent.putExtra("flag", 1);
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
