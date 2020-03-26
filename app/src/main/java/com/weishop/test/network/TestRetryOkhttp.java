package com.weishop.test.network;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 失败重连
 */
public class TestRetryOkhttp {

    private final int cacheSize = 10 * 1024 * 1024; // 10 MiB
    public static String redirect = "http://publicobject.com/helloworld.txt";//get请求永久重定向
    public static String httpsGetURl = "https://raw.github.com/square/okhttp/master/README.md";
    //get请求重定向
    public static String httpsGetHello = "https://publicobject.com/helloworld.txt";//get
    public static String httpsGetBaidu = "https://www.baidu.com";//get

    String mUrl = "https://www.baidu.com/";
    // 请求https
    private final Context mContext;
    private OkHttpClient mOkHttpClient;

    public TestRetryOkhttp(Context context) {
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
//                                .addInterceptor(new TestInterceptor())
//                                .addNetworkInterceptor(new LoggingInterceptor())
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
            testRetry();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testAsync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testRetry();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void testRetry() throws Exception {

        Request request = new Request.Builder()
                .url(httpsGetHello)
                .build();

        Response response = mOkHttpClient.newCall(request).execute();
        response.body().close();

    }

    class TestInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String url = request.url().toString();
            System.out.println("intercept url=" + url);
            Response response = chain.proceed(request);
            response = response.newBuilder().addHeader("code", "400").build();
            return response;
        }
    }


}
