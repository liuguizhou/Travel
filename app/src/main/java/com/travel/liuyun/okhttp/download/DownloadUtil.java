package com.travel.liuyun.okhttp.download;


import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadUtil {

    private Handler handler;
    private static DownloadUtil downloadUtil;
    private final OkHttpClient okHttpClient;

    public static DownloadUtil get() {
        if (downloadUtil == null) {
            downloadUtil = new DownloadUtil();
        }
        return downloadUtil;
    }

    private DownloadUtil() {
        okHttpClient = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }


    /**
     * @param url      下载连接
     * @param saveDir  储存下载文件的SDCard目录
     * @param listener 下载监听
     */
    public void download(final String url, final String saveDir, final OnDownloadListener listener) {
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                listener.onDownloadFailed();
//                callFailed(listener);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                String savePath = isExistDir(saveDir);
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    String fileName = getNameFromUrl(url);
                    File file = new File(savePath, fileName);
//                    getRealFileName(file, fileName, savePath, total);//下载文件已经存在时，再次下载，对文件进行重命名
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
//                        callInProgress(listener, progress);
                        listener.onDownloading(progress);
                    }
                    fos.flush();
                    // 下载完成
//                    callSuccess(listener,file.getAbsolutePath());
                    listener.onDownloadSuccess(file.getAbsolutePath());
                } catch (Exception e) {
//                    callFailed(listener);
                    listener.onDownloadFailed();
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null) fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    /**
     * 如果本地已存在下载文件，在此处理作出判断，重新命名文件名
     *
     * @param file
     * @param fileName
     * @param savePath
     * @param total
     */
    private void getRealFileName(File file, String fileName, String savePath, long total) {
        long existlength = 0;
        if (file.exists()) {
            //找到了文件,代表已经下载过,则获取其长度
            existlength = file.length();
        }
        //之前下载过,需要重新来一个文件
        int i = 1;
        while (existlength >= total) {
            int dotIndex = fileName.lastIndexOf(".");
            String fileNameOther;
            if (dotIndex == -1) {
                fileNameOther = fileName + "(" + i + ")";
            } else {
                fileNameOther = fileName.substring(0, dotIndex)
                        + "(" + i + ")" + fileName.substring(dotIndex);
            }
            File newFile = new File(savePath, fileNameOther);
            file = newFile;
            existlength = newFile.length();
            i++;

        }
    }

    private void callSuccess(final OnDownloadListener listener, final String s) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onDownloadSuccess(s);
            }
        });
    }

    private void callFailed(final OnDownloadListener listener) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onDownloadFailed();
            }
        });
    }

    private void callInProgress(final OnDownloadListener listener, final int progress) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onDownloading(progress);
            }
        });
    }


    /**
     * @param saveDir * @return * @throws IOException * 判断下载目录是否存在
     */
    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }

    /**
     * @param url * @return * 从下载连接中解析出文件名
     */
    @NonNull
    private String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}

