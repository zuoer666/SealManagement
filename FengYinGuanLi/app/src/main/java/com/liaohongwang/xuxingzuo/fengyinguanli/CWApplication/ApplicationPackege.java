package com.liaohongwang.xuxingzuo.fengyinguanli.CWApplication;

import android.app.Application;
import android.content.Context;

public class ApplicationPackege extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initImageLoader(getApplicationContext());

    }

    private static Context context;

    public static Context getGloableApplicationContext(){
        return context;
    }

    /**
     * 初始化ImageLoader
     */
    public static void initImageLoader(Context context) {
//        File cacheDir = StorageUtils.getOwnCacheDirectory(context,
//               "bmobim/Cache");// 获取到缓存的目录地址
        // 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
//                // 线程池内加载的数量
//                .threadPoolSize(3)
//                .threadPriority(Thread.NORM_PRIORITY - 2)
//                .memoryCache(new WeakMemoryCache())
//                .denyCacheImageMultipleSizesInMemory()
//                .discCacheFileNameGenerator(new Md5FileNameGenerator())
//                        // 将保存的时候的URI名称用MD5 加密
//                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                        //  .discCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
//                        // .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
//                .writeDebugLogs() // Remove for release app
//                .build();
//        // Initialize ImageLoader with configuration.
//        ImageLoader.getInstance().init(config);// 全局初始化此配置
    }

}
