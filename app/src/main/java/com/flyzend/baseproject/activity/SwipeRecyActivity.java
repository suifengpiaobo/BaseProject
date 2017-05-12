package com.flyzend.baseproject.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyzend.baseproject.R;
import com.flyzend.baseproject.adapter.SwipeRecyAdapter;
import com.flyzend.baseproject.datas.DataServer;
import com.flyzend.baseproject.view.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class SwipeRecyActivity extends BaseActivity
        implements BaseQuickAdapter.RequestLoadMoreListener,SwipeRefreshLayout.OnRefreshListener{
    private SwipeRecyclerView mSwipeRecyclerView;
    private SwipeRecyAdapter mSwipeRecyAdapter;
    private final int PAGE_SIZE = 10;
    private int PAGE = 1;
    private List<String> datas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_recy);
        initViews();
        initData();
    }

    protected void initViews() {
        mSwipeRecyclerView = findAviewById(R.id.swipeRecyclerView);
        //设置下拉刷新监听
        mSwipeRecyclerView.setOnRefreshListener(this);
        //第一次进入需要加载数据，设置刷新为true
        mSwipeRecyclerView.setRefreshing(true);
    }

    protected void initData() {
        //模拟网络耗时操作，2秒后执行接下来操作
        Flowable.timer(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                //获取数据
                datas = DataServer.getData(PAGE,PAGE_SIZE);
                mSwipeRecyAdapter = new SwipeRecyAdapter(datas);
                //设置加载更多的监听
                mSwipeRecyAdapter.setOnLoadMoreListener(SwipeRecyActivity.this);
                //设置刷新为false 此刻已经刷新完成
                mSwipeRecyclerView.setRefreshing(false);
                //设置adapter
                mSwipeRecyclerView.setBaseQuickAdapter(mSwipeRecyAdapter);
            }
        });
    }

    //下拉刷新回调
    @Override
    public void onRefresh() {
        PAGE = 1;
        Flowable.timer(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                datas = DataServer.getData(PAGE,PAGE_SIZE);
                mSwipeRecyAdapter.setNewData(datas);
                mSwipeRecyclerView.setRefreshing(false);
            }
        });
    }

    //加载更多回调
    @Override
    public void onLoadMoreRequested() {
        Flowable.timer(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                PAGE++;
                datas = DataServer.getData(PAGE,PAGE_SIZE);
                mSwipeRecyAdapter.addData(datas);
                mSwipeRecyAdapter.loadMoreComplete();
            }
        });
    }
}
