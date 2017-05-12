package com.flyzend.baseproject.client;

import android.content.Context;
import android.text.TextUtils;

import com.flyzend.baseproject.AppManager;
import com.flyzend.baseproject.utils.Config;
import com.flyzend.baseproject.utils.LogUtil;
import com.flyzend.baseproject.utils.PreferenceUtils;
import com.flyzend.baseproject.utils.ToastUtil;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Created by 王灿 on 2017/5/11.
 */

public abstract class CacheSubscriber extends BaseSubscriber {
    private static final String TAG = "CacheSubscriber";
    private String mCacheKey;

    public CacheSubscriber(Context context, boolean isShowDialog, String cacheKey) {
        mContext = context;
        mIsShowDialog = isShowDialog;
        mCacheKey = cacheKey;
    }

    public CacheSubscriber(Context context, String cacheKey) {
        this(context, true, cacheKey);
    }

    public CacheSubscriber(String cacheKey, boolean isShowDialog) {
        mContext = AppManager.getInstance().currentActivity();
        mIsShowDialog = isShowDialog;
        mCacheKey = cacheKey;
    }

    public CacheSubscriber(String cacheKey) {
        this(cacheKey, true);
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            String result = responseBody.string();
            if (Config.IS_CACHE_JSON_DATA) {
                PreferenceUtils.getInstance().put(mCacheKey, result);
            }
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
        if (Config.IS_CACHE_JSON_DATA) {
            String cache = (String) PreferenceUtils.getInstance().get(mCacheKey,"");
            if (TextUtils.isEmpty(cache)) {
                //表示网络异常，并且没有缓存数据。缓存具体在RetrofitClient类中实现
                new ToastUtil(mContext).showToast("网络异常，并且没有获取到缓存数据！");
                LogUtil.e(TAG, "网络异常，并且没有获取到缓存数据！");
                return;
            }
            doOnNext(cache);
        } else {
            new ToastUtil(mContext).showToast("网络异常");
        }
        if (t != null) {
            t.printStackTrace();
        }
        LogUtil.e(TAG, "onError" + (TextUtils.isEmpty(t.getMessage()) ? "" : t.getMessage()));
    }

}
