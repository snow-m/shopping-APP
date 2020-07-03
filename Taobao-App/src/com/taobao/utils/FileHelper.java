package com.taobao.utils;

import java.io.File;

import android.content.Context;
import android.os.Environment;



/**
 * @author ���� :
 * @version ����ʱ�䣺2013-10-9 ����09:22:00
 * ��˵��
 */

public class FileHelper {

	public static File getFileDirectory(Context context,String dirName) {
		File dirFile=null;
//		if(ExistSDCard())
//		{
//			File file = new File(Environment.getExternalStorageDirectory(),"taobao");
//			if(!file.exists()){
//				file.mkdirs();
//			}
//			dirFile=new File(file.getPath(),dirName);
//		}
//		else {
			String installPath= context.getFilesDir().getAbsolutePath() + File.separator;
			dirFile = new File(installPath + dirName );
//		}
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		return dirFile;
	}

	public static boolean isFileExist(Context context,String dirName,String FileName)
	{
		File file = new File(getFileDirectory(context,dirName), FileName);
		return file.exists();
	}

	public static File getFile(Context context,String dirName,String FileName)
	{
		File file = new File(getFileDirectory(context,dirName), FileName);
		return file;
	}

	private static boolean ExistSDCard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else
			return false;
	}
}
