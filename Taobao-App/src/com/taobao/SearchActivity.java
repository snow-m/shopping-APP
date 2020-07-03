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
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.taobao.bll.DataProcess;
import com.taobao.entity.Shop;
import com.taobao.utils.ActivityUtils;
import com.taobao.utils.UrlUtil;
import com.taobao.view.MyProgressDialog;
import com.taobao.view.ShopItem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends BaseActivity {
	@ViewInject(R.id.main_list)ListView shopsList;
	@ViewInject(R.id.search_word)EditText searchWord;
	@ViewInject(R.id.search_bar)RelativeLayout searchView;
	@ViewInject(R.id.noContent)TextView noContent;
	@ViewInject(R.id.code)ImageView code;

	//	private int flag;//判断是首页的还是分类的
	private List<Shop> shops ;
	private MyProgressDialog dialog ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		code.setBackgroundResource(R.drawable.ic_mall_two_dimension_code);
		code.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SearchActivity.this, CaptureActivity.class);
				startActivityForResult(intent,1001);
			}
		});
		noContent.setText("未能搜索到商品!");
		dialog = MyProgressDialog.getInstance(this);
		searchView.setVisibility(View.VISIBLE);
		shops = new ArrayList<Shop>();
		shopsList.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				final Shop shop = shops.get(position);
				ShopItem shopItem = new ShopItem(SearchActivity.this, shop,new ShopItem.OnChangeNum() {

					@Override
					public void onMinus() {
						// TODO Auto-generated method stub
						if(shop.num>1){
							shop.setNum(shop.num-1);
							notifyDataSetChanged();
						}else{
							Toast.makeText(SearchActivity.this, "数量至少是1", 0).show();
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
				Intent intent = new Intent(SearchActivity.this, ShopDetailActivity.class);
				intent.putExtra("shop", shops.get(position));
				startActivity(intent);
			}
		});
	}
	@OnClick(R.id.search)
	public void search(View view){

		new SearchTask().execute(searchWord.getText().toString());
		//			ExecutorService e = Executors.newCachedThreadPool();
		//			e.execute(new Runnable() {
		//				@Override
		//				public void run() {
		//					// TODO Auto-generated method stub
		//					List<NameValuePair>  params = new ArrayList<NameValuePair>();
		//					params.add(new BasicNameValuePair("descp", searchWord.getText().toString()));
		//					shops = DataProcess.getShops(SearchActivity.this, UrlUtil.GET_SHOPS, params);
		//					shops.clear();
		//					handler.sendEmptyMessage(0);
		//				}
		//			});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1001&&resultCode==RESULT_OK){
			final String barcode = data.getStringExtra("displayContents");
			searchWord.setText(barcode);
			new SearchTask().execute(barcode);
			// TODO Auto-generated method stub
		}
	}

	class SearchTask extends AsyncTask<String,List<Shop>, List<Shop>>{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}
		@Override
		protected List<Shop> doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			List<NameValuePair>  params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("barcode", arg0[0]));
			return DataProcess.getCodeShops(SearchActivity.this, UrlUtil.GET_CODE_SHOPS, params);
		}
		@Override
		protected void onPostExecute(List<Shop> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			shops.clear();
			shops.addAll(result);
			((BaseAdapter)shopsList.getAdapter()).notifyDataSetChanged();
			if(shops.size()==0){
				noContent.setVisibility(View.VISIBLE);
			}else{
				noContent.setVisibility(View.GONE);
			}
			dialog.cancel();
		}

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
