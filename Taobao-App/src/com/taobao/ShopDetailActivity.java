package com.taobao;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.taobao.entity.Collection;
import com.taobao.entity.Shop;
import com.taobao.entity.Torll;
import com.taobao.utils.AsynImageLoader;
import com.taobao.view.MyProgressDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShopDetailActivity extends BaseActivity {
	@ViewInject(R.id.title)TextView title;
	@ViewInject(R.id.back_btn)Button back;
	@ViewInject(R.id.btnRight)Button btnRight;
	@ViewInject(R.id.img)ImageView img;
	@ViewInject(R.id.desc)TextView desc;
	@ViewInject(R.id.price)TextView price;
	@ViewInject(R.id.minus_btn)ImageView minusBtn;
	@ViewInject(R.id.add_btn)ImageView addBtn;
	@ViewInject(R.id.goods_num)TextView num;
	Shop shop ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_detail);
		ViewUtils.inject(this);
		shop = (Shop) getIntent().getSerializableExtra("shop");
		title.setVisibility(View.VISIBLE);
		title.setText("商品详情");
		num.setText(shop.num+"");
		btnRight.setVisibility(View.GONE);
		AsynImageLoader.showImageAsyn(img, shop.getImg2(), R.drawable.big_load, this);
		desc.setText(shop.getDescp());
		price.setText("￥"+shop.getPrice()+"元");
		minusBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(shop.num>1){
					shop.setNum(shop.num-1);
					num.setText(shop.num+"");
				}else{
					Toast.makeText(ShopDetailActivity.this, "数量至少1", 0).show();
				}
			}
		});
		addBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				shop.setNum(shop.num+1);
				num.setText(shop.num+"");
			}
		});
	}
	public void back(View view) {
		// TODO Auto-generated method stub
		finish();
	}
	//收藏
	public void collect(View view) {
		// TODO Auto-generated method stub
		try {
			Collection collection = dbUtils.findFirst(Selector.from(Collection.class).where("username","=",sp.getString("username", "")).and("shopId","=",shop.getId()));
			if(collection==null){
				dbUtils.save(new Collection(sp.getString("username", ""), shop.getImg(),shop.getImg2(),shop.getDescp(),shop.getPrice(),1,shop.getId()));
				Toast.makeText(this, "收藏成功!", 0).show();
			}else{
				Toast.makeText(this, "已经收藏过该商品!", 0).show();
			}
		} catch (DbException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	//购买
	public void buy(View view) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this,SubmitOrderActivity.class);
		intent.putExtra("torll", new Torll(sp.getString("username", ""), shop.getImg(),shop.getImg2(),shop.getDescp(),shop.getPrice(),shop.num,shop.getId()));
		intent.putExtra("flag", true);
		startActivity(intent);
	}
	//加入购物车
	public void torll(View view) {
		// TODO Auto-generated method stub
		try {
			Torll torll = dbUtils.findFirst(Selector.from(Torll.class).where("username","=",sp.getString("username", "")).and("shopId","=",shop.getId()));
			if(torll==null){
				dbUtils.save(new Torll(sp.getString("username", ""), shop.getImg(),shop.getImg2(),shop.getDescp(),shop.getPrice(),shop.num,shop.getId()));
				Toast.makeText(this, "加入成功!", 0).show();
			}else{
				Toast.makeText(this, "购物车中已经有该商品!", 0).show();
			}
		} catch (DbException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
