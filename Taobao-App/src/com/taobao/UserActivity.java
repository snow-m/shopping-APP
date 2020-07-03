package com.taobao;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.taobao.utils.ActivityUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_center);
		ViewUtils.inject(this);
	}
	public void myCollection(View view) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(UserActivity.this, CollectionActivity.class);
		startActivity(intent);
	}
	public void order(View view) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(UserActivity.this, MyOrderActivity.class);
		startActivity(intent);
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

