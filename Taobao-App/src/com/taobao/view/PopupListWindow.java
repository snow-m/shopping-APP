package com.taobao.view;

import com.taobao.R;
import com.taobao.utils.ActivityUtils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.PopupWindow.OnDismissListener;


public class PopupListWindow
{
 
	private Context mContext;
	private PopupWindow popupWindow;
	private ListView mListView;
	private DisplayMetrics dm;
	public PopupListWindow(Context context){
		this.mContext = context;
		this.dm = context.getResources().getDisplayMetrics();
	}
	public PopupListWindow(ListAdapter adapter, Context context)
	{
		this(adapter, context, null);
	}

	public PopupListWindow(ListAdapter adapter, Context context, ListView listView)
	{
		super();
 
		this.mContext = context;
		this.mListView = listView;
		this.dm = context.getResources().getDisplayMetrics();
		setPopupWindow(adapter,R.drawable.pop_back);
	}

	public void showAsDropDown(View v)
	{
		this.popupWindow.showAsDropDown(v,0,(int) (10*dm.density));
	}
	public void showAsDropDown(View v,int x,int y) {
		// TODO Auto-generated method stub
		
		popupWindow.showAsDropDown(v, ActivityUtils.px2dip(mContext, x),  ActivityUtils.px2dip(mContext, y));
	}
	public void setListAdapter(ListAdapter adapter)
	{
		this.mListView.setAdapter(adapter);
	}
	public void setOnItemClickListener(OnItemClickListener listener)
	{
		this.mListView.setOnItemClickListener(listener);
	}
	 public void dismiss()
	 {
		 if(this.popupWindow.isShowing())
		 {
			 this.popupWindow.dismiss();
		 }
	 }
	 public void setOnItemSelectedListener(OnItemSelectedListener listener)
	 {
		 this.mListView.setOnItemSelectedListener(listener);
		 
	 }
	 public void setOnDismissListener(OnDismissListener listener)
	 {
		 this.popupWindow.setOnDismissListener(listener);
	 }
	 public void setPopupWindow(ListAdapter adapter,int resid)
		{
		 View view = LayoutInflater.from(mContext).inflate(R.layout.pop_view, null);
			this.mListView = (ListView)view.findViewById(R.id.pop_list);		 
			mListView.setLayoutParams(new RelativeLayout.LayoutParams(ActivityUtils.dip2px(mContext,170), RelativeLayout.LayoutParams.MATCH_PARENT));
			mListView.setBackgroundResource(resid);
			setListAdapter(adapter);
			int actualHeight = (int)(45*adapter.getCount()*dm.density);
			int max = (int) (dm.heightPixels -125*dm.density);
			if(actualHeight > max)
			{
				actualHeight = max;
			}
			this.popupWindow =  new PopupWindow(view, (int)(72*dm.density), actualHeight);
			this.popupWindow.setOutsideTouchable(false);
			this.popupWindow.setBackgroundDrawable(new ColorDrawable());
			this.popupWindow.setFocusable(true);
			this.popupWindow.setTouchInterceptor(new OnTouchListener()
			{
				@Override
				public boolean onTouch(View v, MotionEvent event)
				{
					return false;
					
				}
			});
		}
	 public void setUserPopupWindow(ListAdapter adapter,int resid)
	 {
		 setPopupWindow(adapter, resid);
		 mListView.setPadding(0, ActivityUtils.dip2px(mContext, 5), 0, 0);
		 mListView.setLayoutParams(new RelativeLayout.LayoutParams(ActivityUtils.dip2px(mContext, 70), RelativeLayout.LayoutParams.MATCH_PARENT));
	 }
}
