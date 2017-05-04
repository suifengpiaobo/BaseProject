package com.flyzend.baseproject.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.flyzend.baseproject.R;
import com.flyzend.baseproject.adapter.BasePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class ViewpagerActivity extends BaseActivity {
    private ViewPager mViewPager;
    private List<View> mViews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        initViews();
        initData();
    }

    @Override
    protected void initViews() {
        mViewPager = findAviewById(R.id.viewpager);
        mViews = new ArrayList<>();
    }

    @Override
    protected void initData() {
        Flowable.just("view1","view2","view3","view4").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                TextView textView = new TextView(mContext);
                textView.setGravity(Gravity.CENTER);
                textView.setText(s);
                mViews.add(textView);
            }
        });
        mViewPager.setAdapter(new BasePagerAdapter<>(mViews));
    }
}
