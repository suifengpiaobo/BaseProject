package com.flyzend.baseproject.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyzend.baseproject.dialog.BaseDialog;
import com.flyzend.baseproject.utils.LogUtil;
import com.flyzend.baseproject.utils.ToastUtil;
import com.trello.rxlifecycle2.components.RxFragment;

/**
 * Created by 王灿 on 2017/1/13.
 */

public abstract class BaseFragment extends RxFragment implements View.OnClickListener{
    protected final String TAG = getClass().getSimpleName();
    protected Context mContext;
    private ToastUtil mToastUtil;
    private ProgressDialog mLoadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getActivity();
        mToastUtil = new ToastUtil(mContext);
    }

    protected <T extends View> T findAviewById(int viewId) {
        if (viewId > 0) {
            return (T) getActivity().findViewById(viewId);
        }
        return null;
    }

    protected <T extends View> T findAviewInContainer(ViewGroup containerView, int childViewId) {
        if (containerView == null || childViewId <= 0) {
            return null;
        }
        return (T) containerView.findViewById(childViewId);
    }

    /**
     * 最终调用的跳转方法
     * @param startIntent intent
     * @param bundle bundle数据
     * @param requestCode 请求码 -1表示不需要回调
     */
    private void jumpToActivity(Intent startIntent, Bundle bundle, int requestCode) {
        if (startIntent != null) {
            if (bundle != null) {
                startIntent.putExtras(bundle);
            }
            if (requestCode != -1) {
                startActivity(startIntent);
            } else {
                startActivityForResult(startIntent, requestCode);
            }
        }
    }

    protected void jumpToActivity(Class<?> targetActivityClass) {
        jumpToActivity(targetActivityClass,null);
    }

    protected void jumpToActivity(Class<?> targetActivityClass, Bundle bundle) {
        jumpToActivity(targetActivityClass,bundle,-1);
    }

    protected void jumpToActivity(Class<?> targetActiviyClass, int requestCode) {
        jumpToActivity(targetActiviyClass,null,requestCode);
    }

    protected void jumpToActivity(Class<?> targetActiviyClass,Bundle bundle, int requestCode) {
        Intent startIntent = new Intent(mContext, targetActiviyClass);
        jumpToActivity(startIntent,bundle, requestCode);
    }

    /**
     * 基类提供普通Log输出之error级信息输出
     * @param logBody
     */
    protected void e(String logBody) {
        LogUtil.e(TAG, logBody);
    }

    protected void showToast(String msg){
        mToastUtil.showToast(msg);
    }

    protected void showLongToast(String msg){
        mToastUtil.showToastLong(msg);
    }

    protected void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = ProgressDialog.show(mContext, null, "正在努力加载中...", true, true);
        }
        mLoadingDialog.show();
    }

    protected void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {

    }

    protected void showDialog(String title, String msg, DialogInterface.OnClickListener listener){
        new BaseDialog(mContext).showDialog(title,msg,listener);
    }
}
