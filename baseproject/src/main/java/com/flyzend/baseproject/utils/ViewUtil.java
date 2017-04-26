package com.flyzend.baseproject.utils;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 王灿 on 2017/1/10.
 */

public class ViewUtil {
    public static <T extends View> T findViewInContainer(ViewGroup containerView, int toFindViewResId) {
        if (toFindViewResId < 1 || containerView == null) {
            return null;
        }
        return (T) containerView.findViewById(toFindViewResId);
    }

    public static <T extends View> T findAViewById(Activity curActivity, int toFindViewResId) {
        if (curActivity == null || toFindViewResId < 1) {
            return null;
        }
        return (T) curActivity.findViewById(toFindViewResId);
    }

    public static int dp2px(Context context , int dp){
        return  (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,dp,
                context.getResources().getDisplayMetrics());
    }
    public static int sp2px(Context context,int sp){
        return  (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,sp,
                context.getResources().getDisplayMetrics());
    }

    /**
     * 将colorId 转换成ResId
     * @param mContext
     * @param color
     * @return
     */
    public static int getColor(Context mContext,int color) {
        return mContext.getResources().getColor(color);
    }
}
