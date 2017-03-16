package com.travel.liuyun.okhttp.download;

/**
 * Created by liuguizhou on 2017/3/16.
 */

public interface OnDownloadListener {
    /**
     * 下载成功
     */
    void onDownloadSuccess(String str);

    /**
     * @param progress * 下载进度
     */
    void onDownloading(int progress);

    /**
     * 下载失败
     */
    void onDownloadFailed();
}
