package com.taobao;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.taobao.bll.DataProcess;
import com.taobao.utils.UrlUtil;
import com.taobao.view.MyProgressDialog;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends BaseActivity {
	@ViewInject(R.id.title)TextView title;
	@ViewInject(R.id.desc)TextView desc;
	@ViewInject(R.id.btnRight)Button btnRight;
	private MyProgressDialog dialog ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment);
		ViewUtils.inject(this);
		dialog = MyProgressDialog.getInstance(this);
		title.setText("支付宝支付");
		btnRight.setVisibility(View.GONE);
		String number = getIntent().getStringExtra("number");
		String total = getIntent().getStringExtra("total");
		String str = "欢迎来到支付宝支付界面"+"\n\n"
					+"订单号："+number+"\n"
					+"总价："+total;
		desc.setText(str);
	}
	public void back(View view) {
		finish();
	}
	public void pay(View view) {
		new SubmitOrder().execute("");
	}
	private List<NameValuePair> getNameValuePair() {
		// TODO Auto-generated method stub
		String number = getIntent().getStringExtra("number");
		String userId = getIntent().getStringExtra("userId");
		String detail = getIntent().getStringExtra("detail");
		String total = getIntent().getStringExtra("total");
		String shops = getIntent().getStringExtra("shops");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("number", number));
		params.add(new BasicNameValuePair("userId", userId));
		params.add(new BasicNameValuePair("detail", detail));
		params.add(new BasicNameValuePair("total", total));
		params.add(new BasicNameValuePair("shops", shops));
		params.add(new BasicNameValuePair("state", "2"));
		return params;
	}
	class SubmitOrder extends AsyncTask<String, Boolean, Boolean>{
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.show();
		}
		@Override
		protected Boolean doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			boolean result = DataProcess.test(UrlUtil.ADD_ORDER, getNameValuePair());
			return result;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result){
				finish();
				Toast.makeText(PaymentActivity.this, "购买成功，订单已经生成!", 0).show();
			}else{
				finish();
				Toast.makeText(PaymentActivity.this, "购买失败!", 0).show();
			}
			dialog.cancel();
		}
	}
}
