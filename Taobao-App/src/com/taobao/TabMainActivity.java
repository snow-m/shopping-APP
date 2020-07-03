package com.taobao;

import com.taobao.utils.ActivityUtils;
import com.taobao.view.PopupListWindow;

import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Type;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

/**
 * 
 * @author 
 *显示主界面 
 */
@SuppressWarnings("deprecation")
public class TabMainActivity extends TabActivity implements OnCheckedChangeListener{
	private TabHost tabHost;
	private TabSpec tabSpec;
	private static final String HOME = "home";
	private static final String TYPE = "type";
	private static final String SEARCH = "search";
	private static final String USER = "user";
	private static final String TROLL = "troll";
	private RadioGroup rGroup;  
	private TextView title;
	private Button back;
	private PopupListWindow popupWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.main);
		
		ActivityUtils.getInstance().pushActivity(this);
		init();  //初始化view
		setLister(); //绑定事件
		popupWindow = new PopupListWindow(new MyAdapter(), this);
		popupWindow.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				// TODO Auto-generated method stub
				switch (position) {
//				case 0:
//					tabHost.setCurrentTab(0);
//					title.setText("淘宝");
//					break;
				case 0:
					tabHost.setCurrentTab(0);
					title.setText("商品分类");
					break;
				case 1:
					tabHost.setCurrentTab(1);
					title.setText("搜索商品");
					break;
				case 2:
					tabHost.setCurrentTab(2);
					title.setText("购物车");
					break;
				case 3:
					tabHost.setCurrentTab(3);
					title.setText("我的帐号");
					break;

				default:
					break;
				}
				popupWindow.dismiss();
			}
		});
	}
	
	private void init() {
		title = (TextView) findViewById(R.id.title);
		back = (Button) findViewById(R.id.back_btn);
		back.setVisibility(View.GONE);
		title.setText("商品分类");
		rGroup = (RadioGroup) findViewById(R.id.RadioGroup);
		tabHost = this.getTabHost();
//		tabSpec = tabHost.newTabSpec(HOME).setIndicator(HOME)
//		.setContent(new Intent(this, HomeActivity.class));  //
//		tabHost.addTab(tabSpec);
		tabSpec = tabHost.newTabSpec(TYPE).setIndicator(TYPE)
		.setContent(new Intent(this, TypeActivity.class));   //分类
		tabHost.addTab(tabSpec);
		tabSpec = tabHost.newTabSpec(SEARCH).setIndicator(SEARCH)  
		.setContent(new Intent(this, SearchActivity.class));  //搜索
		tabHost.addTab(tabSpec);
		tabSpec = tabHost.newTabSpec(TROLL).setIndicator(TROLL)
		.setContent(new Intent(this, TorllActivity.class));  //购物车
		tabHost.addTab(tabSpec);
		tabSpec = tabHost.newTabSpec(USER).setIndicator(USER)
		.setContent(new Intent(this, UserActivity.class));  //我的账户
		tabHost.addTab(tabSpec);
	}

	private void setLister() {
		rGroup.setOnCheckedChangeListener(this);
	}
	//用RadioGroup代替tabSpec，作为切换视图用
	public void onCheckedChanged(RadioGroup group, int checkedId) { //点击下面5个button，触发这里的事件
		switch (checkedId) {
//		case R.id.home:
//			tabHost.setCurrentTab(0);
//			title.setText("淘宝");
//			break;
		case R.id.type:
			tabHost.setCurrentTab(0);
			title.setText("商品分类");
			break;
		case R.id.search:
			tabHost.setCurrentTab(1);
			title.setText("搜索商品");
			break;
		case R.id.torll:
			tabHost.setCurrentTab(2);
			title.setText("购物车");
			break;
		case R.id.user:
			tabHost.setCurrentTab(3);
			title.setText("我的帐户");
			break;
		}
	}
	public void show(View view) {
		// TODO Auto-generated method stub
		popupWindow.showAsDropDown(view);
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
	class MyAdapter extends BaseAdapter{
		private String[] strs = new String[]{"分类","搜索","购物车","我的帐号"};
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return strs.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup arg2) {
			// TODO Auto-generated method stub
			TextView content = new TextView(TabMainActivity.this);
			content.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
			content.setPadding(0, ActivityUtils.dip2px(TabMainActivity.this, 10), 0,ActivityUtils.dip2px(TabMainActivity.this, 10));
			content.setTextColor(Color.WHITE);
			content.setText(strs[position]);
			content.setGravity(Gravity.CENTER_HORIZONTAL);
			content.setTextSize(16);
			return content;
		}
		
	}
}
