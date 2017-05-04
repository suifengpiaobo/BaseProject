/**
* @Title: SettingsUtils.java
* @Package com.whty.electronicClass.utils
* @Description: TODO(用一句话描述该文件做什么)
* @author 王灿
* @date 2016年10月9日
* @version V1.0
*/
package com.flyzend.baseproject.utils;

/**
* @ClassName: SettingsUtils
* @Description: 封装各个设置的配置方法
* @author 王灿
* @date 2016年10月9日
*
*/
public class SettingUtils {
	private static SettingUtils instance;
	private PreferenceUtils preferenceUtils;
	private static final String IS_LOGIN = "is_login";//用户是否登录

	public void setIsLogin(boolean isLogin){
		preferenceUtils.put(IS_LOGIN,isLogin);
	}

	public boolean getIsLogin(){
		return (Boolean) preferenceUtils.get(IS_LOGIN,false);
	}


	/**
	* 创建一个新的实例 SettingsUtils.
	*/
	private SettingUtils() {
		preferenceUtils = PreferenceUtils.getInstance();
	}
	
	public static synchronized SettingUtils getInstance(){
		if (instance == null) {
			instance = new SettingUtils();
		}
		return instance;
	}
	
	public void clearSettings() {
		preferenceUtils.clear();
	}
}
