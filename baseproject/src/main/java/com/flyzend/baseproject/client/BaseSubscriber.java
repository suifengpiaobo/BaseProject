package com.flyzend.baseproject.client;


import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.flyzend.baseproject.AppManager;
import com.flyzend.baseproject.utils.LogUtil;
import com.flyzend.baseproject.utils.ToastUtil;
import com.flyzend.baseproject.utils.Util;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * 订阅者的基类，封装对话框等操作
 * Created by 王灿 on 2016/12/20.
 */

public abstract class BaseSubscriber implements Subscriber<ResponseBody> {
    protected ToastUtil mToastUtil;
    private static final String TAG = "BaseSubscriber";
    //加载对话框
    private ProgressDialog mLoadingDialog;
    //是否显示加载对话框
    private boolean mIsShowDialog;
    protected Context mContext;
    private String mLoadText;


    public BaseSubscriber(Context context, boolean isShowDialog) {
        mContext = context;
        mIsShowDialog = isShowDialog;
        mToastUtil = new ToastUtil(mContext);
    }

    public BaseSubscriber(Context context) {
        this(context, true);
    }

    public BaseSubscriber(boolean isShowDialog) {
        mContext = AppManager.getInstance().currentActivity();
        mIsShowDialog = isShowDialog;
        mToastUtil = new ToastUtil(mContext);
    }

    public BaseSubscriber() {
        this(true);
    }

    public BaseSubscriber(String loadText){
        this(true);
        mLoadText = loadText;
    }

    private void showDialog() {
        if (mLoadingDialog == null) {
            if (Util.isEmpty(mLoadText)){
                mLoadText = "正在努力加载中...";
            }
            mLoadingDialog = ProgressDialog.show(mContext, null, mLoadText,
                    true, true);
        }
        mLoadingDialog.show();
    }

    private void disMissDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void onSubscribe(Subscription s) {
        //示对话框
        if (mIsShowDialog) {
            showDialog();
        }
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            String result = responseBody.string();
            //执行具体的解析数据逻辑
            doOnNext(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable t) {
        //显示错误
        if (mIsShowDialog) {
            disMissDialog();
        }

        new ToastUtil(mContext).showToast("网络异常");

        if (t != null) {
            t.printStackTrace();
        }
        LogUtil.e(TAG, "onError" + (TextUtils.isEmpty(t.getMessage()) ? "" : t.getMessage()));
    }

    @Override
    public void onComplete() {
        //消失对话框
        if (mIsShowDialog) {
            disMissDialog();
        }
    }

    //子类去实现具体的解析逻辑
    public abstract void doOnNext(String result);
}
