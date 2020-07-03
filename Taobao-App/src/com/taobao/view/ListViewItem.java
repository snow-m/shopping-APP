package com.taobao.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.taobao.R;
import com.taobao.entity.ColumnInfo;

public class ListViewItem extends RelativeLayout{
	private TextView mTvColumnName;//栏目名称
	private ImageView mIvColumnImg;//栏目logo
	public ListViewItem(Context context,ColumnInfo columnInfo) {
		super(context,null);
		View view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
		mTvColumnName = (TextView) view.findViewById(R.id.column_name);
		mIvColumnImg = (ImageView) view.findViewById(R.id.column_img);
		mIvColumnImg.setImageResource(columnInfo.getImg());
		mTvColumnName.setText(columnInfo.getName());
		addView(view);
	}
}
