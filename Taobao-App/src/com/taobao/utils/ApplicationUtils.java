package com.taobao.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;

import com.taobao.HHApp;
import com.taobao.R;

public class ApplicationUtils {
	private static ImageMemoryCache imageMemoryCache = new ImageMemoryCache(HHApp.getContext());
	private static ImageFileCache imageFileCache = new ImageFileCache();
	public static Bitmap getUrlBitmap(final String url) {
		Bitmap result = imageMemoryCache.getBitmapFromCache(url);
		if (result == null) {
			result = imageFileCache.getImage(url);
			if (result == null) {
				result = ImageGetFromHttp.downloadBitmap(url);
				if (result != null) {
					imageFileCache.saveBitmap(result, url);
					imageMemoryCache.addBitmapToCache(url, result);
				}
			} else {
				imageMemoryCache.addBitmapToCache(url, result);
			}
		}
		return result;
	}
}
