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
                        HttpUtils.createSslFactory(builder,
                                new InputStream[]{mContext.getAssets().open("charlesCertificate" +
                                        ".pem")});
                        mOkHttpClient = builder
                                .connectTimeout(10, TimeUnit.SECONDS)
//                                .writeTimeout(10, TimeUnit.SECONDS)
//                                .readTimeout(30, TimeUnit.SECONDS)
                                .hostnameVerifier(new HttpUtils.TrustAllHostnameVerifier())
                                .retryOnConnectionFailure(true).build();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

    }

    private class DefaultEventListener extends EventListener {
        @Override
        public void callStart(Call call) {
            System.out.println("callStart =" + call.request().url());
        }

        @Override
        public void dnsStart(Call call, String domainName) {
            System.out.println("dnsStart =" + call.request().url() + "," +
                    "domainName=" + domainName);
        }

        @Override
        public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
            String address = "";
            if (inetAddressList != null) {
                for (int i = 0; i < inetAddressList.size(); i++) {
                    InetAddress inetAddress = inetAddressList.get(i);
                    address = address + inetAddress.toString() + ",";
                }

            }
            System.out.println("dnsEnd =" + call.request().url() + "," +
                    "domainName=" + domainName + ",address=" + address);
        }

        @Override
        public void connectStart(Call call,
                                 InetSocketAddress inetSocketAddress,
                                 Proxy proxy) {
            String hostName = "";
            if (inetSocketAddress != null) {

                hostName = inetSocketAddress.toString();
            }
            System.out.println("connectStart =" + call.request().url() + "," +
                    "hostName=" + hostName);
        }

        @Override
        public void connectEnd(Call call, InetSocketAddress inetSocketAddress
                , Proxy proxy, Protocol protocol) {
            String hostName = "";
            if (inetSocketAddress != null) {

                hostName = inetSocketAddress.toString();
            }
            System.out.println("connectEnd =" + call.request().url() + "," +
                    "hostName=" + hostName);
        }

        @Override
        public void connectionReleased(Call call, Connection connection) {
            System.out.println("connectionReleased =" + call.request().url() + "," +
                    "connection=" + connection.toString());
        }

        @Override
        public void requestHeadersStart(Call call) {
            System.out.println("requestHeadersStart =" + call.request().url());
        }

        @Override
        public void requestBodyStart(Call call) {
            System.out.println("requestBodyStart =" + call.request().url());
        }

        @Override
        public void responseBodyStart(Call call) {
            System.out.println("responseBodyStart =" + call.request().url());
        }

        @Override
        public void callFailed(Call call, IOException ioe) {
            System.out.println("callFailed =" + call.request().url() + ",ioe=" + ioe);
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
     */
    private void testInterceptors() throws Exception {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        Request request = new Request.Builder()
                .url("http://www.publicobject.com/helloworld.txt")
                .header("User-Agent", "OkHttp Example")
                .build();

        Response response = client.newCall(request).execute();
        response.body().close();

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
