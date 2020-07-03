package com.taobao;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.lidroid.xutils.DbUtils;
import com.taobao.utils.ActivityUtils;
import com.taobao.utils.FileHelper;


public class BaseActivity extends Activity
{
	public static final String Version_Check_Date = "Version_Check_Date";
	public DbUtils dbUtils = null;
	public SharedPreferences sp =null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ActivityUtils.getInstance().pushActivity(this);
		dbUtils = DbUtils.create(this, FileHelper.getFileDirectory(this, "Taobao").getPath(), "taobao.db");
		sp =  getSharedPreferences("shared", Context.MODE_PRIVATE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}


	@Override
	protected void onResume()
	{
		super.onResume();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
	}

	@Override
	public void onStop()
	{
		super.onPause();
		// RobooStatistic.onStop(this);
	}

	/** 隐藏输入法 */
	public void hideKeyBoard(View view)
	{
		InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
}
