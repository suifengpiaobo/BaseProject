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
public class Settings {
	private static Settings instance;
	private PreferenceUtils preferenceUtils;

	//缓存json数据
	public void setCacheJsonData(String key, String json) {
		preferenceUtils.put(key, json);
	}

	//读取缓存json数据
	public String getCacheJsonData(String key) {
		return (String) preferenceUtils.get(key, "");
	}


	/**
	* 创建一个新的实例 SettingsUtils.
	*/
	private Settings() {
		preferenceUtils = PreferenceUtils.getInstance();
	}
	
	public static synchronized Settings getInstance(){
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}
	
	public void clearSettings() {
		preferenceUtils.clear();
	}
}
