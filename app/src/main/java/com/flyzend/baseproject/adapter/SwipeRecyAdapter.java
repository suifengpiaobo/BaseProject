package com.flyzend.baseproject.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.flyzend.baseproject.R;

import java.util.List;

/**
 * Created by 王灿 on 2017/5/4.
 */

public class SwipeRecyAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public SwipeRecyAdapter(List<String> data) {
        super(R.layout.swipe_recy_item, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.text,s);
    }
}
