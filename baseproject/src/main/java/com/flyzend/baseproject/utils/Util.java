package com.flyzend.baseproject.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by 王灿 on 2017/1/10.
 */

public class Util {
    private static final String TAG = "Util";
    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.toString() == null || str.toString().trim()
                .length() == 0 || str.length() == 0 || "null".equalsIgnoreCase(str.toString())) {
            return true;
        } else {
        }
        return false;
    }
    /**
     * Gson解析
     * **********************
     * 注意对返回的T对象进行判空
     * **********************
     */
    public static <T> T parseGson(String json, Class<T> object) {
        Gson gson = new Gson();
        T t = null;
        try {
            t = gson.fromJson(json, object);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            LogUtil.e(TAG, object.getSimpleName() + "解析JsonSyntaxException异常");
            LogUtil.e(TAG, e.getMessage());
        }
        return t;
    }
}
