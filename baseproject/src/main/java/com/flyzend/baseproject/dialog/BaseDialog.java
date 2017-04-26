package com.flyzend.baseproject.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

/**
 * Created by 王灿 on 2017/2/27.
 */

public class BaseDialog extends Dialog {
    private Context mContext;

    public BaseDialog(Context context) {
        super(context);
        mContext = context;
    }

    public void showDialog(String title, String message, OnClickListener listener) {
        new AlertDialog.Builder(mContext)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", listener)
                .setNegativeButton("取消", null)
                .create()
                .show();
    }
}
