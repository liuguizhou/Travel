package com.travel.liuyun.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import com.travel.liuyun.Constants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Joe
 * Date: 13-2-21 下午10:49
 * Email: joesupper@vip.qq.com
 */
public final class ImageUtil {
    public static void startPhotoZoom(Activity activity, Uri uri, int width, int height) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", width / 100);
        intent.putExtra("aspectY", height / 100);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, Constants.PHOTORESOULT);
    }

    public static String getRealPathFromURI(Context mContext, Uri contentUri) {
        final String url = contentUri.toString();
        if (url.startsWith("file://")) return url.substring(7);
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(mContext, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        if (cursor != null) {
            cursor.close();
            cursor = null;
        }
        return path;
    }

    public static void saveBitmapToPath(Bitmap bitmap, String path) {
        try {
            final File avatar = new File(path);
            if (!avatar.exists()) avatar.createNewFile();
            FileOutputStream fOut = new FileOutputStream(avatar);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);// (0 - 100)压缩文件
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            //
        }
    }

    public static Bitmap compressImage(Bitmap bitmap, int maxKb) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        final int kb = baos.toByteArray().length / 1024;

        if (kb > maxKb) {
            baos.reset();
            options = (int) ((maxKb * 100.0) / (kb * 1.0));
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        bitmap.recycle();
        return BitmapFactory.decodeStream(isBm, null, null);
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int rotate) {
        if (bitmap == null)
            return null;

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        // Setting post rotate to 90
        Matrix mtx = new Matrix();
        mtx.postRotate(rotate);
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
    }

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static void copyFileTo(File src, File to) {
        try {
            to.delete();
            to.createNewFile();
            Bitmap pic = BitmapFactory.decodeFile(src.getAbsolutePath());
            final Bitmap temp = pic.copy(Bitmap.Config.ARGB_8888, true);
            FileOutputStream fos = new FileOutputStream(to);
            temp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            pic.recycle();
            temp.recycle();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(String filePath, int reqWidth, int reqHeight) {
        InputStream stream = null;
        try {
            stream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(stream, null, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(stream, null, options);
    }

    public static void gray(Drawable mDrawable) {
        //Make this drawable mutable.
        //A mutable drawable is guaranteed to not share its state with any other drawable.
        mDrawable.mutate();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter cf = new ColorMatrixColorFilter(cm);
        mDrawable.setColorFilter(cf);
    }

    public static Drawable resizeImage(final Bitmap bitmap, final float w, final float h) {
        // load the origial Bitmap
        if (null == bitmap) return null;

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // calculate the scale
        float scaleWidth = w / width;
        float scaleHeight = h / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the Bitmap
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
                height, matrix, true);

        // make a Drawable from Bitmap to allow to set the Bitmap
        // to the ImageView, ImageButton or what ever
        return new BitmapDrawable(resizedBitmap);
    }

    public static Bitmap resizeBitmap(final Bitmap bitmap, final float w, final float h) {
        // load the origial Bitmap
        if (null == bitmap) return null;

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        // calculate the scale
        float scaleWidth = w / width;
        float scaleHeight = h / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the Bitmap
        matrix.postScale(scaleWidth, scaleHeight);
        // if you want to rotate the Bitmap
        // matrix.postRotate(45);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width,
                height, matrix, true);

        // make a Drawable from Bitmap to allow to set the Bitmap
        // to the ImageView, ImageButton or what ever
        return resizedBitmap;
    }

    /*
    压缩图片，处理某些手机拍照角度旋转的问题
    */
    public static String compressImage(Context context, String filePath, int q) throws FileNotFoundException {
        final String targetPath = context.getFilesDir().getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
        System.out.println("compressImagePath =  " + targetPath);
        Bitmap bm = getSmallBitmap(filePath);
        int degree = readPictureDegree(filePath);
        if (degree != 0) {//旋转照片角度
            bm = rotateBitmap(bm, degree);
        }
        File outputFile = new File(targetPath);
        FileOutputStream out = new FileOutputStream(outputFile);
        bm.compress(Bitmap.CompressFormat.JPEG, q, out);
        return outputFile.getPath();
    }

    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }
}

