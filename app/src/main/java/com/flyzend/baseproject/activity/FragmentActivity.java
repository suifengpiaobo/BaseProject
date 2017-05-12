package com.flyzend.baseproject.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.flyzend.baseproject.R;
import com.flyzend.baseproject.fragment.FourFragment;
import com.flyzend.baseproject.fragment.OneFragment;
import com.flyzend.baseproject.fragment.ThreeFragment;
import com.flyzend.baseproject.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentActivity extends BaseActivity {
    private TabLayout mTabLayout ;
    private List<Fragment> mFragments = new ArrayList<>();//存储Fragment的列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        initViews();
        initData();
    }

    protected void initViews() {
        mTabLayout = findAviewById(R.id.tab);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText("one"));
        mTabLayout.addTab(mTabLayout.newTab().setText("two"));
        mTabLayout.addTab(mTabLayout.newTab().setText("three"));
        mTabLayout.addTab(mTabLayout.newTab().setText("four"));
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switchFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    protected void initData() {
        mFragments.add(new OneFragment());
        mFragments.add(new TwoFragment());
        mFragments.add(new ThreeFragment());
        mFragments.add(new FourFragment());
        setFragmentContainer(R.id.fragmentLayout);//fragment所展示的布局id
        addFragments(mFragments);//添加Fragment列表
        switchFragment(0);//默认显示第一个Fragment
    }
}
