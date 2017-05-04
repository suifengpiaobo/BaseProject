package com.flyzend.baseproject.download;

import com.flyzend.baseproject.utils.LogUtil;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;

/**
 * Created by 王灿 on 2017/4/13.
 */

public abstract class SimpleFileDownloadListener extends FileDownloadListener {
    private static final String TAG = "SimpleFileDownloadListener";

    @Override
    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
    }

    @Override
    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
    }

    @Override
    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
        int percent = (int) (((float) soFarBytes / totalBytes) * 100);
        updateProgress(percent);
    }

    protected void updateProgress(int percent) {

    }

    @Override
    protected void blockComplete(BaseDownloadTask task) {
    }

    @Override
    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
    }

    @Override
    protected abstract void completed(BaseDownloadTask task);

    @Override
    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
    }

    @Override
    protected void error(BaseDownloadTask task, Throwable e) {
        LogUtil.e(TAG, task.getFilename() + "下载失败:" + e.getMessage());
        e.printStackTrace();
    }

    @Override
    protected void warn(BaseDownloadTask task) {
    }
}
