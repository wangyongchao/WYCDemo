package com.weishop.test.network;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by wangyongchao on 2019/12/30 17:10
 */
public class TestOkhttp {

    public static String redirect = "http://publicobject.com/helloworld.txt";//get请求永久重定向
    public static String httpsGetURl = "https://raw.github.com/square/okhttp/master/README.md";//get请求重定向
    public static String httpsGetHello = "https://publicobject.com/helloworld.txt";//get
    public static String httpsGetBaidu = "https://www.baidu.com";//get
    // 请求https
    private final Context mContext;
    private OkHttpClient mOkHttpClient;

    public TestOkhttp(Context context) {
        this.mContext = context;
        initOkHttpClient();
    }

    private void initOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (this) {
                if (mOkHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    try {
                        RxUtils.createSslFactory(builder,
                                new InputStream[]{mContext.getAssets().open("charlesCertificate" +
                                        ".pem")});
                        mOkHttpClient = builder
                                .hostnameVerifier(new RxUtils.TrustAllHostnameVerifier())
                                .retryOnConnectionFailure(true).build();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }

    public void test() {
        try {
            runAsynchronousGet(httpsGetURl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 同步get请求
     *
     * @param url
     * @throws Exception
     */

    public void runSynchronousGet(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = mOkHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }

            System.out.println(response.body().string());
        }
    }

    /**
     * 异步get请求
     *
     * @param url
     * @throws Exception
     */
    public void runAsynchronousGet(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("thread=" + Thread.currentThread().getName());
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    System.out.println(responseBody.string());
                    System.out.println("after");
                }
            }
        });
    }
}
