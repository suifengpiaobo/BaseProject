package com.flyzend.baseproject.datas;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * Created by 王灿 on 2017/5/4.
 */

public class DataServer {
    public static List<String> getData(int page,int page_size){
        final List<String> datas = new ArrayList<>();
        if (page <= 0){
            page = 1;
        }
        if (page_size <=0){
            page_size = 1;
        }
        Flowable.range((page-1)*page_size,page_size).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                datas.add("第"+integer+"数据");
            }
        });
        return datas;
    }
}
