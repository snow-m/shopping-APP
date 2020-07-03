package com.taobao.utils;



import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import android.content.Context;
import android.widget.ImageView;


public class AsynImageLoader {
	/**
	 *
	 * @param imageView 需要延迟加载图片的对象
	 * @param url 图片的URL地址
	 * @param resId 图片加载过程中显示的图片资源
	 */
	public static void showImageAsyn(ImageView imageView, String url, int resId,Context context){
		DisplayImageOptions options = new DisplayImageOptions.Builder()  
		.showImageOnLoading(resId)        // 设置图片下载期间显示的图片  
		.showImageForEmptyUri(resId)  // 设置图片Uri为空或是错误的时候显示的图片  
		.showImageOnFail(resId)       // 设置图片加载或解码过程中发生错误显示的图片      
		.cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中  
		.cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中  
		.build();                                   // 创建配置过得DisplayImageOption对象  
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		imageLoader.displayImage(url, imageView, options);
	}

}

