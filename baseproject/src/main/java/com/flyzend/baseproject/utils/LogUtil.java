package com.flyzend.baseproject.utils;

import android.util.Log;




/**
 * 模块功能：日志打印的工具类，DEBUG为true表示开启日志打印，false表示关闭日志打印<br>
 */
public final class LogUtil {
	private static final boolean DEBUG = BuildConfig.DEBUG;

	private LogUtil() {

	}

	public static void e(final String tag, final String msg) {
		if (DEBUG) {
			Log.e(tag, msg);
		}
	}

	public static void e(final String tag, final String msg, final Exception e) {
		if (DEBUG) {
			Log.e(tag, msg, e);
		}
	}

	public static void v(final String tag, final String msg) {
		if (DEBUG) {
			Log.v(tag, msg);
		}
	}

	public static void i(final String tag, final String msg) {
		if (DEBUG) {
			Log.i(tag, msg);
		}
	}

	public static void d(final String tag, final String msg) {
		if (DEBUG) {
			Log.d(tag, msg);
		}
	}
}
