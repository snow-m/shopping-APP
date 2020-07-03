package com.taobao.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * @author ���� :
 * @version ����ʱ�䣺2013-10-9 ����09:23:25 ��˵��
 */

public class DataRetrieve
{

	public static String getSearchData(String url, List<NameValuePair> params)
	{
		String result = "";
		try
		{
			// 查询最新版本请求地址
			HttpPost request = new HttpPost(url);
			HttpResponse response;
			request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
			defaultHttpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
			defaultHttpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
			response = defaultHttpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200)
			{
				result = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			}
			else
			{
				return "";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public static String getServiceObjectString(String httpPath)
	{
		InputStream fis = null;
		HttpURLConnection httpUrlConnection = null;
		StringBuffer sb = null;
		try
		{
			URL httpUrl = new URL(httpPath);
			httpUrlConnection = (HttpURLConnection) httpUrl.openConnection();
			int httpState = 404;
			try
			{
				httpUrlConnection.setConnectTimeout(10000);
				httpUrlConnection.setReadTimeout(10000);
				httpState = httpUrlConnection.getResponseCode();
			}
			catch (ConnectException e)
			{
				e.printStackTrace();
			}
			if (httpState < 400)
			{
				httpUrlConnection.connect();
				fis = httpUrlConnection.getInputStream();
				sb = new StringBuffer();
				int i = 0;
				byte[] b = new byte[1024 * 1024];
				while ((i = fis.read(b)) != -1)
				{
					sb.append(new String(b, 0, i));
				}
				return sb.toString().trim();
				// writeFile(sb.toString());
			}
			else
			{
				return "error";
			}
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
			return "error";
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return "error";
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return "error";
		}
		finally
		{
			try
			{
				if (fis != null)
				{
					fis.close();
				}
				sb = null;
				httpUrlConnection.disconnect();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
