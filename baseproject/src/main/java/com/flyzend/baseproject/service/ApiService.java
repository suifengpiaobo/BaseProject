package com.flyzend.baseproject.service;


import com.flyzend.baseproject.client.RetrofitClient;
import com.trello.rxlifecycle2.components.RxFragment;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.TreeMap;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by 王灿 on 2016/12/19.
 * API接口实例获取类，通过此类访问对应API
 */

public class ApiService {
    private static IBaseService sIBaseService;

    private static IBaseService getIBaseService() {
        if (sIBaseService == null) {
            sIBaseService = RetrofitClient.getInstanse().getRetrofitClient().create(IBaseService.class);
        }
        return sIBaseService;
    }

    public static BuildFlowable build(RxAppCompatActivity rxAppCompatActivity){
        return new BuildFlowable(rxAppCompatActivity);
    }

    public static BuildFlowable build(RxFragment rxFragment){
        return new BuildFlowable(rxFragment);
    }

    public static class BuildFlowable{
        private RxAppCompatActivity mRxAppCompatActivity;
        private RxFragment mRxFragment;

        public BuildFlowable(RxAppCompatActivity rxAppCompatActivity) {
            mRxAppCompatActivity = rxAppCompatActivity;
        }

        public BuildFlowable(RxFragment rxFragment) {
            mRxFragment = rxFragment;
        }

        private Flowable<ResponseBody> bindLifeCycle(Flowable<ResponseBody> flowable){
            flowable = flowable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
            if (mRxAppCompatActivity != null){
                return flowable.compose(mRxAppCompatActivity.<ResponseBody>bindToLifecycle());
            }else if (mRxFragment != null){
                return flowable.compose(mRxFragment.<ResponseBody>bindToLifecycle());
            }
            return flowable;
        }

        public Flowable<ResponseBody> post(String url, TreeMap<String, String> params){
            return bindLifeCycle(getIBaseService().post(url,params));
        }

        public Flowable<ResponseBody> get(String url){
            return bindLifeCycle(getIBaseService().get(url));
        }

        public Flowable<ResponseBody> postJson(String url, RequestBody body){
            return bindLifeCycle(getIBaseService().postJson(url,body));
        }
    }
}
