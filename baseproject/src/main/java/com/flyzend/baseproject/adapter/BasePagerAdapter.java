package com.flyzend.baseproject.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 王灿 on 2017/2/13.
 * Viewpager 的基础Adapter
 */

public class BasePagerAdapter<T extends View> extends PagerAdapter {
    private List<T> mList;

    public BasePagerAdapter(List<T> list) {
        mList = list;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);
    }
}
