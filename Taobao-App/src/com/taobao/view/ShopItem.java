package com.taobao.view;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.taobao.R;
import com.taobao.entity.Shop;
import com.taobao.utils.AsynImageLoader;

public class ShopItem extends RelativeLayout{
	private TextView shopDesc;//商品名称
	private TextView price;//价格
	private ImageView shopImg;//商品logo
	private CheckBox checkBox;//商品logo
	private ImageView minusBtn,addBtn;
	private OnChangeNum mOnChangeNum;
	private TextView num ;
	public ShopItem(final Context context,final Shop shop,OnChangeNum onChangeNum) {
		super(context,null);
		this.mOnChangeNum = onChangeNum;
		View view = LayoutInflater.from(context).inflate(R.layout.shop_item, null);
		shopDesc = (TextView) view.findViewById(R.id.shop_desc);
		num = (TextView) view.findViewById(R.id.goods_num);
		price = (TextView) view.findViewById(R.id.price);
		shopImg = (ImageView) view.findViewById(R.id.shop_img);
		checkBox = (CheckBox) view.findViewById(R.id.checked);
		minusBtn = (ImageView) view.findViewById(R.id.minus_btn);
		minusBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
					mOnChangeNum.onMinus();
			}
		});
		addBtn = (ImageView) view.findViewById(R.id.add_btn);
		addBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mOnChangeNum.onAdd();
			}
		});
		AsynImageLoader.showImageAsyn(shopImg, shop.getImg(), R.drawable.small_load, context);
		shopDesc.setText(shop.getDescp());
		price.setText("￥"+shop.getPrice()+"元");
		num.setText(""+shop.num);
		addView(view);
	}
	public void setCheckBoxVisible(boolean flag) {
		// TODO Auto-generated method stub
		checkBox.setVisibility(View.VISIBLE);
		if(flag){
			checkBox.setChecked(true);
		}else{
			checkBox.setChecked(false);
		}
	}
	public interface OnChangeNum{
		// TODO Auto-generated method stub
		void onMinus();
		void onAdd();
	}
}
