package com.flyzend.baseproject;

import android.app.Application;

import com.flyzend.baseproject.utils.PreferenceUtils;
import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by 王灿 on 2017/1/10.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FileDownloader.init(getApplicationContext());
        PreferenceUtils.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        AppManager.getInstance().exitWholeApp(this);
    }
}
