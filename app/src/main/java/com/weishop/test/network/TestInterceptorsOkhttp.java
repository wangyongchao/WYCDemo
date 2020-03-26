package com.weishop.test.network;

import android.content.Context;
import android.os.Environment;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Connection;
import okhttp3.Credentials;
import okhttp3.EventListener;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okio.BufferedSink;

/**
 * Created by wangyongchao on 2019/12/30 17:10
 */
public class TestInterceptorsOkhttp {

    private final int cacheSize = 10 * 1024 * 1024; // 10 MiB
    public static String redirect = "http://publicobject.com/helloworld.txt";//get请求永久重定向
    public static String httpsGetURl = "https://raw.github.com/square/okhttp/master/README.md";
    //get请求重定向
    public static String httpsGetHello = "https://publicobject.com/helloworld.txt";//get
    public static String httpsGetBaidu = "https://www.baidu.com";//get
    // 请求https
    private final Context mContext;
    private OkHttpClient mOkHttpClient;

    public TestInterceptorsOkhttp(Context context) {
        this.mContext = context;
        initOkHttpClient();
    }

    private void initOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (this) {
                if (mOkHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    try {

                        Cache cache = new Cache(mContext.getExternalCacheDir(), cacheSize);
//                        builder.cache(cache); //需要缓存的时候用
                        builder.eventListener(new DefaultEventListener());
//                        HttpUtils.createSslFactory(builder,
//                                new InputStream[]{mContext.getAssets().open("charlesCertificate" +
//                                        ".pem")});
                        mOkHttpClient = builder
                                .connectTimeout(10, TimeUnit.SECONDS)
//                                .writeTimeout(10, TimeUnit.SECONDS)
//                                .readTimeout(30, TimeUnit.SECONDS)
//                                .addInterceptor(new LoggingInterceptor())
                                .addNetworkInterceptor(new LoggingInterceptor())
//                                .hostnameVerifier(new HttpUtils.TrustAllHostnameVerifier())
                                .retryOnConnectionFailure(true).build();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }


    public void test() {
        try {
            testInterceptors();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testAsync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testInterceptors();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 拦截器是个非常强大机制，可以监控，重写，重试所有的调用。
     * 拦截器有两种 应用拦截器和网络拦截器
     * 注册有一个应用拦截器addInterceptor
     * 本例子中http://www.publicobject.com/helloworld.txt
     * 会重定向到https://www.publicobject.com/helloworld.txt
     * OkHttp 会自动的跟踪重定向
     *
     *我们的应用拦截器只会调用一次并且从chain.proceed(request)返回的response就是重定向后的
     * response
     */
    private void testInterceptors() throws Exception {

        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example")
                .build();

        Response response = mOkHttpClient.newCall(request).execute();
        response.body().close();

    }


    /**
     * 响应缓存  cacheControl() 控制缓存
     */
    public void testResponseCache() throws Exception {
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();

        String response1Body;
        try (Response response1 = mOkHttpClient.newCall(request).execute()) {
            if (!response1.isSuccessful())
                throw new IOException("Unexpected code " + response1);

            response1Body = response1.body().string();
            System.out.println("Response 1 response:          " + response1);
            System.out.println("Response 1 cache response:    " + response1.cacheResponse());
            System.out.println("Response 1 network response:  " + response1.networkResponse());
        }

        String response2Body;
        try (Response response2 = mOkHttpClient.newCall(request).execute()) {
            if (!response2.isSuccessful())
                throw new IOException("Unexpected code " + response2);

            response2Body = response2.body().string();
            System.out.println("Response 2 response:          " + response2);
            System.out.println("Response 2 cache response:    " + response2.cacheResponse());
            System.out.println("Response 2 network response:  " + response2.networkResponse());
        }

        System.out.println("Response 2 equals Response 1? " + response1Body.equals(response2Body));

    }

    class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            System.out.println(String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            System.out.println(String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }


}
