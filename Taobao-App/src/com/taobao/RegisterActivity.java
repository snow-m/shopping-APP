package com.taobao;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.taobao.bll.DataProcess;
import com.taobao.utils.UrlUtil;
import com.taobao.view.MyProgressDialog;

public class RegisterActivity extends BaseActivity {
	@ViewInject(value=R.id.title)private TextView title;
	@ViewInject(value=R.id.username)private EditText username;
	@ViewInject(value=R.id.password)private EditText password;
	@ViewInject(value=R.id.phone)private EditText phone;
	@ViewInject(value=R.id.address)private EditText address;
	private MyProgressDialog dialog;
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				finish();
				Toast.makeText(RegisterActivity.this, "注册新用户成功！", 0).show();
				break;
			case 1:
				Toast.makeText(RegisterActivity.this, "注册失败，用户名已存在！", 0).show();
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
		setContentView(R.layout.register_activity);
		ViewUtils.inject(this);
		dialog = MyProgressDialog.getInstance(this);
		title.setText("注册新用户");
	}
	@OnClick(R.id.reset)
	public void reset(View view){
		username.setText("");
		password.setText("");
	}
	@OnClick(R.id.register)
	public void register(View view){
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
		ExecutorService e = Executors.newCachedThreadPool();
		e.execute(new Runnable() {
			@Override
			public void run() {
				boolean result = DataProcess.test(UrlUtil.REGISTER,getNameValuePair());
				if(result){
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
		params.add(new BasicNameValuePair("phone", phone.getText().toString()));
		params.add(new BasicNameValuePair("address", address.getText().toString()));
		return params;
	}
	@OnClick(R.id.back_btn)
	public void back(View view){
		finish();
		hideKeyBoard(view);
	}
}
