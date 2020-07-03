package com.taobao;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.taobao.bll.DataProcess;
import com.taobao.entity.User;
import com.taobao.utils.ActivityUtils;
import com.taobao.utils.ResourcePool;
import com.taobao.utils.UrlUtil;
import com.taobao.view.MyProgressDialog;

@SuppressLint("HandlerLeak")
public class LoginActivity extends BaseActivity {
	@ViewInject(value=R.id.title)private TextView title;
	@ViewInject(value=R.id.back_btn)private Button back;
	@ViewInject(value=R.id.login_name_edit)private EditText username;
	@ViewInject(value=R.id.login_pwd_edit)private EditText password;
	
	private MyProgressDialog dialog;
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				Toast.makeText(LoginActivity.this, "登陆成功", 0).show();
				Intent intent = new Intent(LoginActivity.this, TabMainActivity.class);
				finish();
				startActivity(intent);
				Editor e = sp.edit();
				e.putString("username", username.getText().toString());
				e.putString("password", password.getText().toString());
				e.commit();
				break;
			case 1:
				Toast.makeText(LoginActivity.this, "登录失败，用户名密码不匹配", 0).show();
				break;

			default:
				break;
			}
			dialog.cancel();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		ViewUtils.inject(this);
		dialog = MyProgressDialog.getInstance(this);
		title.setText("登陆");
		back.setVisibility(View.GONE);
		
	}
	@OnClick(R.id.login)
	public void login(View view){
		hideKeyBoard(view);
		if("".equals(username.getText().toString())){
			Toast.makeText(this, "用户名必填", 0).show();
			return;
		}
		if("".equals(password.getText().toString())){
			Toast.makeText(this, "密码必填", 0).show();
			return;
		}
		dialog.show();
		Message msg = new Message();
		ExecutorService e = Executors.newCachedThreadPool();
		e.execute(new Runnable() {
			@Override
			public void run() {
				User result = DataProcess.login(UrlUtil.LOGIN,getNameValuePair());
				if(result!=null){
					ResourcePool.getInstance().setUser(result);
					handler.sendEmptyMessage(0);
				}else{
					handler.sendEmptyMessage(1);
				}
			}
		});
	}
	private List<NameValuePair> getNameValuePair() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", username.getText().toString()));
		params.add(new BasicNameValuePair("password", password.getText().toString()));
		return params;
	}
	@OnClick(R.id.reset)
	public void reset(View view){
		username.setText("");
		password.setText("");
	}
	@OnClick(R.id.register)
	public void register(View view){
		Intent intent = new Intent(this, RegisterActivity.class);
		startActivity(intent);
	}
	@OnClick(R.id.exit)
	public void exit(View view){
		ActivityUtils.getInstance().ConfirmExit(this);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{// 捕捉返回键
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			ActivityUtils.getInstance().ConfirmExit(this);
		}
		return true;
	}
}
