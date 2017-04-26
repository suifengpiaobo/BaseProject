package com.flyzend.baseproject.utils;

/**
 * Created by 王灿 on 2017/1/10.
 */

public class Util {
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
}
