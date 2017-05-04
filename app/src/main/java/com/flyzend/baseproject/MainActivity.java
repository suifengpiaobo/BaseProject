package com.flyzend.baseproject;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.flyzend.baseproject.activity.BaseActivity;
import com.flyzend.baseproject.activity.FragmentActivity;
import com.flyzend.baseproject.activity.NetworkActivity;
import com.flyzend.baseproject.activity.SwipeRecyActivity;
import com.flyzend.baseproject.activity.ViewpagerActivity;
import com.flyzend.baseproject.download.SimpleFileDownloadListener;
import com.flyzend.baseproject.utils.PreferenceUtils;
import com.flyzend.baseproject.utils.SettingUtils;
import com.flyzend.baseproject.view.DimImageView;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {
    private DimImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        //关于配置项使用示例
        configSetting();
        //文件下载示例，下载图片并用imageView展示
        fileDownload();
        //loading对话框
        showLoading();
        Flowable.interval(3, TimeUnit.SECONDS).take(1).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                dismissLoading();
            }
        });
        //显示系统风格对话框
        //showSystemStyleDialog();
    }

    private void showSystemStyleDialog() {
        showDialog("标题", "消息内容", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("确定");
                dialog.dismiss();
            }
        });
    }

    private void fileDownload() {
        FileDownloader.getImpl()
                //下载地址
                .create("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png")
                //保存路径
                .setPath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "baidu.png")
                .setListener(new SimpleFileDownloadListener() {
                    @Override
                    protected void completed(BaseDownloadTask task) {
                        Bitmap bitmap = BitmapFactory.decodeFile(task.getTargetFilePath());
                        image.setImageBitmap(bitmap);
                    }
                })
                .start();
    }

    private void configSetting() {
        //可以直接使用PreferenceUtils来做配置项
        PreferenceUtils.getInstance().put("is_login", true);//设置登录配置项
        PreferenceUtils.getInstance().get("is_login", false);//获取登录配置项，false为默认值
        //也可以新增SettingUtils类来省去编写配置项Key
        SettingUtils.getInstance().setIsLogin(true);//设置登录配置项
        SettingUtils.getInstance().getIsLogin();//获取登录配置项，false为默认值
    }

    @Override
    protected void initViews() {
        image = findAviewById(R.id.image);
        //Toast
        (findAviewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("aaa");
                //showLongToast("bbb");
            }
        });
        (findAviewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jumpToActivity  Activiy跳转
                jumpToActivity(FragmentActivity.class);
                /**
                 //传参数跳转
                 Bundle bundle = new Bundle();
                 bundle.putString("name","123");
                 jumpToActivity(FragmentActivity.class,bundle);
                 //需要返回结果的跳转（startActivityForResult）,requestCode不为-1才能得到返回结果
                 jumpToActivity(FragmentActivity.class,1001);
                 //需要返回结果的跳转，同时携带参数
                 jumpToActivity(FragmentActivity.class,bundle,1001);
                 */
            }
        });
        (findAviewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jumpToActivity  Activiy跳转
                jumpToActivity(NetworkActivity.class);
            }
        });
        (findAviewById(R.id.button4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jumpToActivity  Activiy跳转
                jumpToActivity(ViewpagerActivity.class);
            }
        });
        (findAviewById(R.id.button5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //jumpToActivity  Activiy跳转
                jumpToActivity(SwipeRecyActivity.class);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
