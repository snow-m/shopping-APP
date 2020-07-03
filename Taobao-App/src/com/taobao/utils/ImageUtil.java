package com.taobao.utils;

import android.graphics.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 
 */
public class ImageUtil {
    /**
     * Read image
     */
    public static Bitmap readImageFile(String imagePath, int maxSize) {
        Bitmap bitmap = null;
        try {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                BitmapFactory.Options options = new BitmapFactory.Options();

                if (maxSize < 1) {
                    options.inJustDecodeBounds = false;
                    bitmap = BitmapFactory.decodeFile(imagePath, options);
                    return bitmap;
                } else {
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(imagePath, options);

                    // the resource image max size
                    int sourceMaxSize = Math.max(options.outWidth, options.outHeight);

                    if (sourceMaxSize < 1) {
                        return null;
                    }
                    int scale = sourceMaxSize / maxSize;

                    options.inJustDecodeBounds = false;
                    options.inSampleSize = scale;
                    // narrow image
                    bitmap = BitmapFactory.decodeFile(imagePath, options);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * Save image
     */
    public static boolean saveImageFile(Bitmap bitmap, String path) {
        boolean isSuccess = false;
        try {
            File dirFile = new File(path);
            // create file directory
            if (!dirFile.getParentFile().exists()) {
                dirFile.getParentFile().mkdirs();
            }
            // create Hidden Image File .nomedia move to PDApplication
            File file = new File(path);
            BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(file));
            // write image stream
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);           
//            bitmap.compress(Bitmap.CompressFormat.PNG, 80, outStream);          
            outStream.flush();
            isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }


    /**
     * 
     * @param bitmap
     * @param pixels
     * @return
     */
    public static Bitmap getRoundCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     */
    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left,top,right,bottom,dst_left,dst_top,dst_right,dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int)left, (int)top, (int)right, (int)bottom);
        final Rect dst = new Rect((int)dst_left, (int)dst_top, (int)dst_right, (int)dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }


    public static Bitmap cutImage(Bitmap bitmap, int orientation) {
        if(orientation != 0){
            Matrix m = new Matrix();
            m.setRotate(orientation, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
            try {
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            } catch (OutOfMemoryError ex) {
            }
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int w, h;
        int x, y;
        if(width > height){
            w = height;
            h = height;
            x = (width - height) / 2;
            y = 0;
        }else{
            w = width;
            h = width;
            x = 0;
            y = (height - width)/2;
        }

        return Bitmap.createBitmap(bitmap, x, y, w, h);
    }
}
