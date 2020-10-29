package com.weishop.test;

import android.app.Application;
import android.content.Context;

import com.dianping.logan.Logan;
import com.dianping.logan.LoganConfig;
import com.weishop.test.util.LogUtils;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import xcrash.ICrashCallback;
import xcrash.TombstoneParser;
import xcrash.XCrash;

/**
 * Created by wangyongchao on 16/7/1.
 */
public class MyApplication extends Application {


    private String clientId = "dsfa323234342";


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        PluginManager.getInstance(base).init();
        initXCrash();
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        Debug.startMethodTracing(Environment.getExternalStorageDirectory() + "/test/testcpu
//        .trace");
//        init();

//        Debug.stopMethodTracing();
//        BlockCanary.install(this, new AppBlockCanaryContext()).start();
//        CrashHandler.getInstance().init(this);
//        LeakCanary.install(this);

    }

    private static String customTagPrefix = "custom";

    private static String generateTag(StackTraceElement stack){
        String tag = "%s.%s(L:%d)";
        String className = stack.getClassName();
        className = className.substring(className.lastIndexOf(".")+1);
        tag = String.format(tag, stack.getClassName(),className,stack.getLineNumber());
        tag = customTagPrefix==null?tag:customTagPrefix+":"+tag;
        return tag;
    }

    private void initXCrash() {
        ICrashCallback callback = new ICrashCallback() {
            @Override
            public void onCrash(String logPath, String emergency) throws Exception {
                try {
                    TombstoneParser.parse(logPath, emergency);
                    LogUtils.d("logPath=" + logPath + ",emergency=" + emergency);
                    File file = new File(logPath);
                    if(file.exists()){
                        long l = file.lastModified();
                        LogUtils.d("l=" + l+",current="+System.currentTimeMillis());
                    }
                    Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
                    Collection<StackTraceElement[]> values = allStackTraces.values();
                    Iterator<StackTraceElement[]> iterator = values.iterator();
                    while (iterator.hasNext()){
                        StringBuilder stringBuilder = new StringBuilder();
                        StackTraceElement[] elements = iterator.next();
                        for(int  i=0;i<elements.length;i++){
                            String s = generateTag(elements[i]);
                            stringBuilder.append(s+"\r\n");
                        }
                        LogUtils.d("s="+stringBuilder.toString());
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        XCrash.init(this, new XCrash.InitParameters()
                .setAppVersion("test1.0")
                .setJavaRethrow(true)
                .setJavaLogCountMax(10)
                .setJavaDumpAllThreadsWhiteList(new String[]{"^main$", "^Binder:.*", ".*Finalizer" +
                        ".*"})
                .setJavaDumpAllThreadsCountMax(10)
                .setJavaCallback(callback)
                .setNativeRethrow(true)
                .setNativeLogCountMax(10)
                .setNativeDumpAllThreadsWhiteList(new String[]{"^xcrash\\.sample$", "^Signal " +
                        "Catcher$", "^Jit thread pool$", ".*(R|r)ender.*", ".*Chrome.*"})
                .setNativeDumpAllThreadsCountMax(10)
                .setNativeCallback(callback)
                .setAnrRethrow(true)
                .setAnrLogCountMax(10)
                .setAnrCallback(callback)
                .setPlaceholderCountMax(3)
                .setPlaceholderSizeKb(512)
                .setLogFileMaintainDelayMs(1000));



    }

    private void initLog() {
        LoganConfig config = new LoganConfig.Builder()
                .setCachePath(getApplicationContext().getFilesDir().getAbsolutePath())
                .setPath(getApplicationContext().getExternalFilesDir(null).getAbsolutePath()
                        + File.separator + "logan_v1")
                .setEncryptKey16("0123456789012345".getBytes())
                .setEncryptIV16("0123456789012345".getBytes())
                .build();
        Logan.init(config);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogUtils.d("MyApplication onLowMemory");
    }

    private void init() {
        testSleep();
    }

    private void testSleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
