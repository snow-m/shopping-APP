package com.taobao;

import android.app.Application;

public class HHApp extends Application {
	private static HHApp hhApp;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		hhApp = this;
	}
	public static HHApp getContext() {
		// TODO Auto-generated method stub
		return hhApp;
	}
}
