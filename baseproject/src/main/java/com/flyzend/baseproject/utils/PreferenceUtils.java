package com.flyzend.baseproject.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.Map;

/**
* @ClassName: PreferenceUtils
* @Description: SharePreference管理类
* @author 王灿
* @date 2016年10月8日
*
 */
public class PreferenceUtils {
	private static final String FILL_NAME = "def_app_config";
	private static SharedPreferences mSharedPreferences;
	private static PreferenceUtils instance;
	
	private PreferenceUtils(Context context){
		//单例
		mSharedPreferences = context.getSharedPreferences(FILL_NAME,Context.MODE_PRIVATE);
	}
	
	//放到application 中进行全局初始化
	public static synchronized void init (Context context){
		if (instance == null) {
			instance = new PreferenceUtils(context);
		}
	}
	
	/**
	* @Title: getInstance
	* @Description: 获取单例
	* @param @return    参数
	* @return PreferenceUtils    返回类型
	* @throws
	 */
	public static PreferenceUtils getInstance(){
		if (instance == null) {
			throw new RuntimeException("preference should be init in application");
		}
		if (mSharedPreferences == null) {
			throw new RuntimeException("sharedPreferences init fail");
		}
		return instance;
	}

    /**
     * 存入某个key对应的value值
     *
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        Editor edit = mSharedPreferences.edit();
        if (value instanceof String) {
            edit.putString(key, (String) value);
        } else if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        }
        edit.apply();
    }

    /**
     * 得到某个key对应的值
     *
     * @param key
     * @param defValue
     * @return
     */
    public Object get(String key, Object defValue) {
        if (defValue instanceof String) {
            return mSharedPreferences.getString(key, (String) defValue);
        } else if (defValue instanceof Integer) {
            return mSharedPreferences.getInt(key, (Integer) defValue);
        } else if (defValue instanceof Boolean) {
            return mSharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if (defValue instanceof Float) {
            return mSharedPreferences.getFloat(key, (Float) defValue);
        } else if (defValue instanceof Long) {
            return mSharedPreferences.getLong(key, (Long) defValue);
        }
        return null;
    }

    /**
     * 返回所有数据
     *
     * @return
     */
    public Map<String, ?> getAll() {
        return mSharedPreferences.getAll();
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public void remove(String key) {
        Editor edit = mSharedPreferences.edit();
        edit.remove(key);
        edit.apply();
    }

    /**
     * 清除所有内容
     */
    public void clear() {
        Editor edit = mSharedPreferences.edit();
        edit.clear();
        edit.apply();
    }

}
