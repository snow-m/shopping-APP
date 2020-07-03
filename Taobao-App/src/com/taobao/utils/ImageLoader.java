package com.taobao.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

public class ImageLoader {

	private static ImageLoader instance;

	private ExecutorService executorService;
	private ImageMemoryCache memoryCache;
	private ImageFileCache fileCache;
	private Map<String, TaskItem> taskMap;
	private boolean allowLoad = true;
	private Context mContext;
	private OnLoadCompleteListener mLoadCompleteListener = null;

	private ImageLoader(Context context) {
		int cpuNums = Runtime.getRuntime().availableProcessors();
		this.executorService = Executors.newFixedThreadPool(cpuNums + 1);

		this.mContext = context;
		this.memoryCache = new ImageMemoryCache(context);
		this.fileCache = new ImageFileCache();
		this.taskMap = new HashMap<String, TaskItem>();
	}

	/**
	 * getInstance
	 */
	public static ImageLoader getInstance(Context context) {
		if (instance == null)
			instance = new ImageLoader(context);
		return instance;
	}

	/**
	 * restore allowLoad
	 */
	public void restore() {
		this.allowLoad = true;
	}

	/**
	 * lock
	 */
	public void lock() {
		this.allowLoad = false;
	}

	/**
	 * unlock doTask
	 */
	public void unlock() {
		this.allowLoad = true;
		doTask();
	}

	/**
	 * addTask
	 */
	public void addTask(String url, View img) {
		addTask(url, img, null);
	}
	
	public void addTask(String url, View img, OnLoadCompleteListener loadCompleteListener) {
		// memoryCache
		Bitmap bitmap = memoryCache.getBitmapFromCache(url);
		if (bitmap != null) {
			if (loadCompleteListener != null) {
				loadCompleteListener.OnComplete(img, bitmap);
			} else {
				if (img instanceof ImageView) {
					((ImageView) img).setImageBitmap(bitmap);
				}
			}
		} else {
			synchronized (taskMap) {
				TaskItem item=new TaskItem();
				item.url=url;
				item.view=img;
				item.loadCompleteListener=loadCompleteListener;
				taskMap.put(Integer.toString(img.hashCode()), item);
			}
			if (allowLoad) {
				doTask();
			}
		}
	}

	/**
	 * doTask
	 */
	private void doTask() {
		synchronized (taskMap) {
			Collection<TaskItem> con = taskMap.values();
			for (TaskItem i : con) {
				if (i != null) {
					loadImage(i.url,i);
				}
			}
			taskMap.clear();
		}
	}

	private void loadImage(String url, TaskItem item) {
		this.executorService.submit(new TaskWithResult(new TaskHandler(url, item), url));
	}

	/*** getBitmap memoryCache fileCache network ***/
	public Bitmap getBitmap(String url) {
		// memoryCache
		Bitmap result = memoryCache.getBitmapFromCache(url);
		if (result == null) {
			// fileCache
			result = fileCache.getImage(url);
			if (result == null) {
				// network
				result = ImageGetFromHttp.downloadBitmap(url);
				if (result != null) {
					fileCache.saveBitmap(result, url);
					memoryCache.addBitmapToCache(url, result);
				}
			} else {
				// memoryCache
				memoryCache.addBitmapToCache(url, result);
			}
		}
		return result;
	}
	
	private class TaskItem {
		public View view;
		public String url;
		public OnLoadCompleteListener loadCompleteListener=null;		
	}

	/*** TaskWithResult ***/
	private class TaskWithResult implements Callable<String> {
		private String url;
		private Handler handler;

		public TaskWithResult(Handler handler, String url) {
			this.url = url;
			this.handler = handler;
		}

		@Override
		public String call() throws Exception {
			Message msg = new Message();
			msg.obj = getBitmap(url);
			if (msg.obj != null) {
				handler.sendMessage(msg);
			}
			return url;
		}
	}

	/*** TaskHandler ***/
	private class TaskHandler extends Handler {
		TaskItem mTaskItem;
		String url;

		public TaskHandler(String url, TaskItem item) {
			this.url=url;
			this.mTaskItem = item;
		}

		@Override
		public void handleMessage(Message msg) {
			/*** ImageView tag ***/
			if (!TextUtils.isEmpty(mTaskItem.url)&&mTaskItem.url.equals(url)) {
				if (msg.obj != null) {
					Bitmap bitmap = (Bitmap) msg.obj;
					if (mTaskItem.loadCompleteListener!=null){
						mTaskItem.loadCompleteListener.OnComplete(mTaskItem.view, bitmap);
					} else {
						if (mTaskItem.view instanceof ImageView) {
							((ImageView) mTaskItem.view).setImageBitmap(bitmap);
						}
					}
				}
			}
		}
	}

	public interface OnLoadCompleteListener {
		public void OnComplete(View view, Bitmap bitmap);
	}
}
