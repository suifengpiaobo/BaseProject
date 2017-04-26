package com.flyzend.baseproject.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageButton;

/**
 * Created by 王灿 on 2017/2/28.
 * 通过滤镜来设置Img的点击变灰效果
 */
public class DimImageView extends ImageButton {
    public DimImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //代码设置background null
        setBackgroundDrawable(null);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus) {
            setFilter();
        } else {
            removeFilter();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            setFilter();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            removeFilter();
        }
        return super.onTouchEvent(event);
    }

    /**
     * 设置滤镜
     */
    public void setFilter() {
        //先获取设置的src图片
        Drawable drawable = getDrawable();
        //当src图片为Null，获取背景图片
        if (drawable == null) {
            drawable = getBackground();
        }
        if (drawable != null) {
            //设置滤镜
            drawable.setColorFilter(Color.parseColor("#FFD1D1D1"), PorterDuff.Mode.MULTIPLY);
        }
    }

    /**
     * 清除滤镜
     */
    public void removeFilter() {
        //先获取设置的src图片
        Drawable drawable = getDrawable();
        //当src图片为Null，获取背景图片
        if (drawable == null) {
            drawable = getBackground();
        }
        if (drawable != null) {
            //清除滤镜
            drawable.clearColorFilter();
        }
    }
}
