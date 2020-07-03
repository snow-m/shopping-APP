package com.taobao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.taobao.bll.DataProcess;
import com.taobao.entity.Shop;
import com.taobao.utils.UrlUtil;
import com.taobao.view.MyProgressDialog;
import com.taobao.view.ShopItem;

import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShopActivity extends BaseActivity {
	@ViewInject(R.id.title)TextView title;
	@ViewInject(R.id.title_layout)RelativeLayout titleLayout;
	@ViewInject(R.id.back_btn)Button back;
	@ViewInject(R.id.btnRight)Button btnRight;
	@ViewInject(R.id.main_list)ListView shopsList;
	private int flag;//判断是首页的还是分类的
	private String type;//搜索的关键词
	private List<Shop> shops ;
	private MyProgressDialog dialog ;
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				shopsList.setAdapter(new BaseAdapter() {

					@Override
					public View getView(int position, View convertView, ViewGroup parent) {
						// TODO Auto-generated method stub
						final Shop shop = shops.get(position);
						ShopItem shopItem = new ShopItem(ShopActivity.this, shop,new ShopItem.OnChangeNum() {
							
							@Override
							public void onMinus() {
								// TODO Auto-generated method stub
								if(shop.num>1){
									shop.setNum(shop.num-1);
									notifyDataSetChanged();
								}else{
									Toast.makeText(ShopActivity.this, "数量至少是1", 0).show();
								}
							}
							@Override
							public void onAdd() {
								// TODO Auto-generated method stub
								shop.setNum(shop.num+1);
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
				shopsList.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View view, int position,
							long arg3) {
						// TODO Auto-generated method stub
						Intent intent = new Intent(ShopActivity.this, ShopDetailActivity.class);
						intent.putExtra("shop", shops.get(position));
						startActivity(intent);
					}
				});
				break;
			case 1:
				break;
			default:
				break;
			}
			dialog.cancel();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		dialog = MyProgressDialog.getInstance(this);
		titleLayout.setVisibility(View.VISIBLE);
		btnRight.setVisibility(View.GONE);
		flag = getIntent().getIntExtra("flag", 0);
		type = getIntent().getStringExtra("type");
		final List<NameValuePair>  params = new ArrayList<NameValuePair>();
		if(flag==0){//首页
			title.setText(type+"商品");
			params.add(new BasicNameValuePair("shopType", type));
		}else{//分类
			title.setText(type);
			params.add(new BasicNameValuePair("type", type));
		}
		dialog.show();
		ExecutorService e = Executors.newCachedThreadPool();
		e.execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				shops = DataProcess.getShops(ShopActivity.this,UrlUtil.GET_SHOPS, params);
				handler.sendEmptyMessage(0);
			}
		});
	}
	public void back(View view) {
		// TODO Auto-generated method stub
		finish();
	}
}
