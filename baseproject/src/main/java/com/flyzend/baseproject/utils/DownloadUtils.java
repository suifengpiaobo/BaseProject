//package com.hibood.www.baseproject.utils;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//
//import com.hibood.www.baseproject.R;
//
//import org.wlf.filedownloader.DownloadFileInfo;
//import org.wlf.filedownloader.listener.simple.OnSimpleFileDownloadStatusListener;
//
//import java.io.File;
//
///**
// * Created by 王灿 on 2017/1/11.
// */
//
//public class DownloadUtils extends OnSimpleFileDownloadStatusListener {
//    private ProgressDialog mProgressDialog;
//    private Context mContext;
//
//    public DownloadUtils (Context context){
//        mContext = context;
//        mProgressDialog = new ProgressDialog(mContext);
//        mProgressDialog.setTitle(context.getString(R.string.downloading));
//        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//    }
//
//    @Override
//    public void onFileDownloadStatusPrepared(DownloadFileInfo downloadFileInfo) {
//        super.onFileDownloadStatusPrepared(downloadFileInfo);
//        mProgressDialog.setMax((int) downloadFileInfo.getFileSizeLong());
//        mProgressDialog.show();
//    }
//
//    @Override
//    public void onFileDownloadStatusDownloading(DownloadFileInfo downloadFileInfo, float downloadSpeed, long remainingTime) {
//        super.onFileDownloadStatusDownloading(downloadFileInfo, downloadSpeed, remainingTime);
//        mProgressDialog.setProgress((int) downloadFileInfo.getDownloadedSizeLong());
//    }
//
//    @Override
//    public void onFileDownloadStatusCompleted(DownloadFileInfo downloadFileInfo) {
//        new ToastUtil(mContext).showToast("下载完成");
//        mProgressDialog.dismiss();
//
//        String filePath = downloadFileInfo.getFilePath();
//        File file = new File(filePath);
//        openFile(file);
//    }
//
//    @Override
//    public void onFileDownloadStatusFailed(String url, DownloadFileInfo downloadFileInfo, FileDownloadStatusFailReason failReason) {
//        super.onFileDownloadStatusFailed(url, downloadFileInfo, failReason);
//        mProgressDialog.dismiss();
//    }
//
//    private void openFile(File file) {
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction(android.content.Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.fromFile(file),
//                "application/vnd.android.package-archive");
//        mContext.startActivity(intent);
//    }
//}
