package com.flyzend.baseproject.utils;

import android.content.Context;
import android.widget.Toast;

public final class ToastUtil {
	private Context mContext;
	private Toast mToast;

	public ToastUtil(final Context context) {
		mContext = context;
	}

	public void showToast(final int stringId) {
		if (mToast == null) {
			mToast = Toast.makeText(mContext, mContext.getResources()
					.getString(stringId), Toast.LENGTH_SHORT);
		} else {
			mToast.setText(stringId);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	public void showToast(final String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(msg);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	public void showToastLong(final String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
		} else {
			mToast.setText(msg);
			mToast.setDuration(Toast.LENGTH_LONG);
		}
		mToast.show();
	}

	public void cancelToast() {
		if (mToast != null) {
			mToast.cancel();
		}
	}
}
