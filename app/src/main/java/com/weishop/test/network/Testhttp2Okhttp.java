package com.weishop.test.network;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/**
 * Created by wangyongchao on 2019/12/30 17:10
 * http 2.0测试
 */
public class Testhttp2Okhttp {
    public static String httpsGetURl = "https://publicobject.com/helloworld.txt";

    //http 2.0 多路复用
    public static String https2 = "https://http2.golang.org/gophertiles?latency=0";//get
    public static String https2_gophertiles1 = "https://http2.golang" +
            ".org/gophertiles?x=0&y=0&cachebust=1578550891964614272&latency=0";//get
    public static String https2_gophertiles2 = "https://http2.golang" +
            ".org/gophertiles?x=1&y=0&cachebust=1578550891964614272&latency=0";//get
    // 请求https
    private final Context mContext;
    private OkHttpClient mOkHttpClient;

    public Testhttp2Okhttp(Context context) {
        this.mContext = context;
        initOkHttpClient();
    }

    private void initOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (this) {
                if (mOkHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    try {

                        builder.eventListener(new DefaultEventListener());
//                        builder.eventListenerFactory() 为每个调用设置监听事件
//                        HttpUtils.createSslFactory(builder,
//                                new InputStream[]{mContext.getAssets().open("charlesCertificate" +
//                                        ".pem")});
                        mOkHttpClient = builder
                                .connectTimeout(10, TimeUnit.SECONDS)
                                .writeTimeout(10, TimeUnit.SECONDS)
                                .readTimeout(10, TimeUnit.SECONDS)
                                .hostnameVerifier(new HttpUtils.TrustAllHostnameVerifier())
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
            testDuoluFuyong();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testAsync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    post();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 测试多路复用
     * okhttp中支持连接重用，发往一个主机的共享一个连接
     *
     * @throws Exception
     */
    private void testDuoluFuyong() throws Exception {

        Request request = new Request.Builder()
                .url(https2)
                .build();

        try (Response response = mOkHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println("response11111111111");
        }
        System.out.println(
                "------------------------------------------------------------------------------------");

//        Request request2 = new Request.Builder()
//                .url(https2_gophertiles1)
//                .build();
//
//        try (Response response = mOkHttpClient.newCall(request2).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//            System.out.println("response22222222");
//        }

        post();


        System.out.println(
                "------------------------------------------------------------------------------------");

        Request request3 = new Request.Builder()
                .url(https2_gophertiles2)
                .build();

        try (Response response = mOkHttpClient.newCall(request3).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println("response3333333");
        }

    }

    void post() throws IOException {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        String json = bowlingJson("Jesse", "Jake");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("https://support.google.com/chrome/?p=ui_security_indicator")
//                .url("http://www.roundsapp.com/post")
                .post(body)
                .build();
        try (Response response = mOkHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            System.out.println("response post json");
        }
    }

    String bowlingJson(String player1, String player2) {
        return "{'winCondition':'HIGH_SCORE',"
                + "'name':'Bowling',"
                + "'round':4,"
                + "'lastSaved':1367702411696,"
                + "'dateStarted':1367702378785,"
                + "'players':["
                + "{'name':'" + player1 + "','history':[10,8,6,7,8],'color':-13388315,'total':39},"
                + "{'name':'" + player2 + "','history':[6,10,5,10,10],'color':-48060,'total':41}"
                + "]}";
    }

}
