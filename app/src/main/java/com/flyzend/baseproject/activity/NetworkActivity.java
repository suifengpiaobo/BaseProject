package com.flyzend.baseproject.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.flyzend.baseproject.R;
import com.flyzend.baseproject.bean.WeatherBean;
import com.flyzend.baseproject.client.BaseSubscriber;
import com.flyzend.baseproject.service.ApiService;
import com.flyzend.baseproject.utils.Util;

import java.util.HashMap;
import java.util.Map;

public class NetworkActivity extends BaseActivity {
    private String url = "http://apicloud.mob.com/v1/weather/query";
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        initViews();
        Map<String,String> map = new HashMap<>();
        map.put("key","520520test");
        map.put("city","南京");
        map.put("province","江苏");
        ApiService.build(this).get(url,map).subscribe(new BaseSubscriber() {
            @Override
            public void doOnNext(String result) {
                mTextView.setText("get请求结果："+result);
                WeatherBean bean = Util.parseGson(result, WeatherBean.class);
                if (bean != null){
                    //do whatever you want
                }
            }
        });
//        ApiService.build(this).post(url,map).subscribe(new BaseSubscriber() {
//            @Override
//            public void doOnNext(String result) {
//                mTextView.setText("post请求结果："+result);
//            }
//        });
//        ApiService.build(this).postJson(url,map).subscribe(new BaseSubscriber() {
//            @Override
//            public void doOnNext(String result) {
//                mTextView.setText("postJson请求结果："+result);
//            }
//        });
    }

    @Override
    protected void initViews() {
        mTextView = findAviewById(R.id.text);
    }

    @Override
    protected void initData() {

    }
}
