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
import okio.GzipSink;
import okio.Okio;

/**
 * Created by wangyongchao on 2019/12/30 17:10
 * 拦截器测试
 */
public class TestInterceptorsOkhttp {

    private final int cacheSize = 10 * 1024 * 1024; // 10 MiB
    public static String redirect = "http://publicobject.com/helloworld.txt";//get请求永久重定向
    public static String httpsGetURl = "https://raw.github.com/square/okhttp/master/README.md";
    //get请求重定向
    public static String httpsGetHello = "https://publicobject.com/helloworld.txt";//get
    public static String httpsGetBaidu = "https://www.baidu.com";//get
    //http 2.0 多路复用
    public static String https2 = "https://http2.golang.org/gophertiles?latency=0";//get
    public static String https2_gophertiles1 = "https://http2.golang.org/gophertiles?x=0&y=0&cachebust=1578550891964614272&latency=0";//get
    public static String https2_gophertiles2 = "https://http2.golang.org/gophertiles?x=1&y=0&cachebust=1578550891964614272&latency=0";//get
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

//                        Cache cache = new Cache(mContext.getExternalCacheDir(), cacheSize);
//                        builder.cache(cache); //需要缓存的时候用
                        builder.eventListener(new DefaultEventListener());
                        HttpUtils.createSslFactory(builder,
                                new InputStream[]{mContext.getAssets().open("charlesCertificate" +
                                        ".pem")});
                        mOkHttpClient = builder
                                .connectTimeout(10, TimeUnit.SECONDS)
//                                .writeTimeout(10, TimeUnit.SECONDS)
//                                .readTimeout(30, TimeUnit.SECONDS)
                                .addInterceptor(new LoggingInterceptor())
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
     * <p>
     * 注册有一个应用拦截器addInterceptor
     * 本例子中http://www.publicobject.com/helloworld.txt
     * 会重定向到https://www.publicobject.com/helloworld.txt
     * OkHttp 会自动的跟踪重定向
     * <p>
     * 我们的应用拦截器只会调用一次并且从chain.proceed(request)返回的response就是重定向后的
     * response
     * 拦截器只会被调用一次，如果发生重定向也只会被调用一次
     * <p>
     * 注册一个网络拦截器 addNetworkInterceptor,
     * 拦截器会被调用两次，重定向前和重定向后都会调用
     * <p>
     * chain.proceed(request);之前的代码是发送请求之前，之后的代码是获取到response之后调用
     */
    private void testInterceptors() throws Exception {

        Request request = new Request.Builder()
                .url(https2)
                .header("User-Agent", "OkHttp Example")
                .build();

        try (Response response = mOkHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        }

    }

    /**
     * 指定一个file ，然后post到服务器
     * 底层都是读取file然后转换为流
     *
     * @throws Exception
     */
    public void postFile() throws Exception {
        MediaType MEDIA_TYPE_MARKDOWN
                = MediaType.parse("text/x-markdown; charset=utf-8");
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File file = new File(externalStorageDirectory, "/test/file/README.md");
        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();

        try (Response response = mOkHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        }
    }

    /**
     * This interceptor compresses the HTTP request body. Many webservers can't handle this!
     * 重写请求头，然后对请求体统一进行gzip压缩，并通过Content-Encoding协议头告服务器压缩方式
     */
    final class GzipRequestInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request originalRequest = chain.request();
            if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
                return chain.proceed(originalRequest);
            }

            Request compressedRequest = originalRequest.newBuilder()
                    .header("Content-Encoding", "gzip")
                    .method(originalRequest.method(), gzip(originalRequest.body()))
                    .build();
            return chain.proceed(compressedRequest);
        }

        private RequestBody gzip(final RequestBody body) {
            return new RequestBody() {
                @Override
                public MediaType contentType() {
                    return body.contentType();
                }

                @Override
                public long contentLength() {
                    return -1; // We don't know the compressed length in advance!
                }

                @Override
                public void writeTo(BufferedSink sink) throws IOException {
                    BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                    body.writeTo(gzipSink);
                    gzipSink.close();
                }
            };
        }
    }

    /**
     * Dangerous interceptor that rewrites the server's cache-control header.
     * 重写响应头
     */
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("Cache-Control", "max-age=60")
                    .build();
        }
    };

    public static class LoggingInterceptor implements Interceptor {
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


    private class DefaultEventListener extends EventListener {
        @Override
        public void callStart(Call call) {
            System.out.println("callStart =" + call.request().url());
        }

        @Override
        public void callEnd(Call call) {
            System.out.println("callEnd =" + call.request().url());
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

}
