package com.weishop.test.network;

import android.content.Context;
import android.content.res.AssetManager;
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
 * okhttp基本使用
 */
public class TestRecipesOkhttp {

    private final int cacheSize = 10 * 1024 * 1024; // 10 MiB
    public static String redirect = "http://publicobject.com/helloworld.txt";//get请求永久重定向
    public static String httpsGetURl = "https://raw.github.com/square/okhttp/master/README.md";
    //get请求重定向
    public static String httpsGetHello = "https://publicobject.com/helloworld.txt";//get
    public static String httpsGetBaidu = "https://www.baidu.com";//get
    // 请求https
    private final Context mContext;
    private OkHttpClient mOkHttpClient;

    public TestRecipesOkhttp(Context context) {
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
//                        HttpUtils.createSslFactory(builder,
//                                new InputStream[]{mContext.getAssets().open("charlesCertificate" +
//                                        ".pem")}); charles证书
                        HttpUtils.createAllFactory(builder);//信任所有证书
                        mOkHttpClient = builder
                                .connectTimeout(10, TimeUnit.SECONDS)
//                                .writeTimeout(10, TimeUnit.SECONDS)
//                                .readTimeout(30, TimeUnit.SECONDS)
                                .retryOnConnectionFailure(true).build();
                    } catch (Exception e) {
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
            runAsynchronousGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testAsync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    postForm();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    /**
     * 拦截器是个非常强大机制，可以监控，重写，重试所有的调用。
     */
    private void testInterceptors() {

    }


    //----------------------------------------

    /**
     * 授权访问
     * okhttp可以自动重试未授权的请求，当服务器返回响应码401未授权的时候，
     * authenticator.Authenticator 会被自动调用提供授权。为了避免多次重试，可以在某种条件下，返回null。
     * Authorization 请求头
     */
    private void testAuthentication() throws Exception {

        OkHttpClient okHttpClient = mOkHttpClient.newBuilder()
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        System.out.println("authenticate");
                        if (response.request().header("Authorization") != null) {
                            //请求头中已经有授权，不用多次重试
                            return null; // Give up, we've already attempted to authenticate.
                        }

                        System.out.println("Authenticating for response: " + response);
                        System.out.println("Challenges: " + response.challenges());
                        String credential = Credentials.basic("jesse", "password1");
                        return response.request().newBuilder()
                                .header("Authorization", credential)
                                .build();
                    }
                })
                .build();
        Request request = new Request.Builder()
                .url("http://publicobject.com/secrets/hellosecret.txt")
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        }

    }

    /**
     * 个性化配置,每一个请求单独设置请求参数
     * mOkHttpClient.newBuilder()
     */
    public void testConfiguration() throws Exception {
        Request request = new Request.Builder()
                .url("http://httpbin.org/delay/1") // This URL is served with a 1 second delay.
                .build();

        // Copy to customize OkHttp for this request.
        OkHttpClient client1 = mOkHttpClient.newBuilder()
                .readTimeout(500, TimeUnit.MILLISECONDS)
                .build();
        try (Response response = client1.newCall(request).execute()) {
            System.out.println("Response 1 succeeded: " + response);
        } catch (IOException e) {
            System.out.println("Response 1 failed: " + e);
        }

        // Copy to customize OkHttp for this request.
        OkHttpClient client2 = mOkHttpClient.newBuilder()
                .readTimeout(3000, TimeUnit.MILLISECONDS)
                .build();
        try (Response response = client2.newCall(request).execute()) {
            System.out.println("Response 2 succeeded: " + response);
        } catch (IOException e) {
            System.out.println("Response 2 failed: " + e);
        }
    }

    /**
     * 链接超时
     * connectTimeout(10, TimeUnit.SECONDS)
     *
     * @throws Exception
     */
    public void testTimeout() throws Exception {
        Request request = new Request.Builder()
                .url("http://httpbin.org/delay/20") // This URL is served with a 2 second delay.
                .build();

        try (Response response = mOkHttpClient.newCall(request).execute()) {
            System.out.println("Response completed: " + response);
        }
    }

    /**
     * 使用cancel可以立即停止一个正在进行的调用。如果当前线程正在写请求或者正在
     * 读取一个相应，将会抛出IOException。同步或者异步都可以取消。
     */
    public void testCancelCall() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Request request = new Request.Builder()
                .url("http://httpbin.org/delay/2") // This URL is served with a 2 second delay.
                .build();

        final long startNanos = System.nanoTime();
        final Call call = mOkHttpClient.newCall(request);

        // Schedule a job to cancel the call in 1 second.
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.printf("%.2f Canceling call.%n",
                        (System.nanoTime() - startNanos) / 1e9f);
                call.cancel();
                System.out.printf("%.2f Canceled call.%n",
                        (System.nanoTime() - startNanos) / 1e9f);
            }
        }, 1, TimeUnit.SECONDS);

        System.out.printf("%.2f Executing call.%n", (System.nanoTime() - startNanos) / 1e9f);
        try (Response response = call.execute()) {
            System.out.printf("%.2f Call was expected to fail, but completed: %s%n",
                    (System.nanoTime() - startNanos) / 1e9f, response);
        } catch (IOException e) {
            System.out.printf("%.2f Call failed as expected: %s%n",
                    (System.nanoTime() - startNanos) / 1e9f, e);
        }

    }

    /**
     * 响应缓存  cacheControl() 控制缓存
     */
    public void testResponseCache() throws Exception {
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
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

    /**
     * 使用moshi解析json数据
     *
     * @throws Exception
     */
    public void parseJsonResponse() throws Exception {
        Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);

        Request request = new Request.Builder()
                .url("https://api.github.com/gists/c2a7c39532239ff261be")
                .build();
        try (Response response = mOkHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Gist gist = gistJsonAdapter.fromJson(response.body().source());

            for (Map.Entry<String, GistFile> entry : gist.files.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue().content);
            }
        }

    }

    static class Gist {
        Map<String, GistFile> files;
    }

    static class GistFile {
        String content;
    }


    /**
     * 表单提交
     * multipart/form-data
     * The imgur client ID for OkHttp recipes. If you're using imgur for anything other than
     * running
     * these examples, please request your own client ID! https://api.imgur.com/oauth2
     * <p>
     * POST / HTTP/1.1
     * Authorization: Client-ID ...
     * Content-Type: multipart/form-data; boundary=39df2305-8e0f-4093-964b-b9234cdebb76
     * Content-Length: 31004
     * Host: www.baidu.com
     * Connection: Keep-Alive
     * Accept-Encoding: gzip
     * User-Agent: okhttp/3.14.4
     * <p>
     * --39df2305-8e0f-4093-964b-b9234cdebb76
     * Content-Disposition: form-data; name="title"
     * Content-Length: 11
     * <p>
     * Square Logo
     * --39df2305-8e0f-4093-964b-b9234cdebb76
     * Content-Disposition: form-data; name="image"; filename="logo-square.png"
     * Content-Type: image/png
     * Content-Length: 30675
     */
    public void postMutilRequest() throws Exception {

        String IMGUR_CLIENT_ID = "...";
        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
        // Use the imgur image upload API as documented at https://api.imgur.com/endpoints/image
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        File file = new File(externalStorageDirectory, "/test/file/test.jpg");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "Square Logo")
                .addFormDataPart("image", "logo-square.png",
                        RequestBody.create(MEDIA_TYPE_PNG, file))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .url("https://www.baidu.com")
                .post(requestBody)
                .build();

        try (Response response = mOkHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        }
    }

    /**
     * 提交表单数据
     * application/x-www-form-urlencoded
     * 键值对形式 默认， key1=value1&key2=value2
     */
    public void postForm() throws Exception {

        RequestBody formBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();
        Request request = new Request.Builder()
                .url("https://www.baidu.com")
//                .url("https://en.wikipedia.org/w/index.php")
                .post(formBody)
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
     * post请求，输出流
     * 在这里，我们将请求主体作为流发布。此请求正文的内容正在写入时生成。
     * 此示例演示了将请求体内容先写入到Okio缓冲接收器中，
     * 然后在我们的程序中通过BufferedSink.Outputstream（）中获取该我们需要的输出流。
     */
    public void postStream() throws Exception {
        MediaType MEDIA_TYPE_MARKDOWN
                = MediaType.parse("text/x-markdown; charset=utf-8");

        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                System.out.println("writeTo");
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");
                for (int i = 2; i <= 997; i++) {
                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }

            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n) return factor(x) + " × " + i;
                }
                return Integer.toString(n);
            }
        };

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(requestBody)
                .build();

        try (Response response = mOkHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        }
    }

    /**
     * 使用HTTP POST将请求正文发送到服务
     * post 字符串
     */
    public void postString() throws Exception {
        MediaType MEDIA_TYPE_MARKDOWN
                = MediaType.parse("text/x-markdown; charset=utf-8");

        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        try (Response response = mOkHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        }
    }


    /**
     * 测试访问header头
     * addHeader使用add如果存在现有值，不删除原来的
     */
    public void testHeaders() throws Exception {
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();

        try (Response response = mOkHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println("Server: " + response.header("Server"));
            System.out.println("Date: " + response.header("Date"));
            System.out.println("Vary: " + response.headers("Vary"));
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

        //try() 1.7之后自动关闭流对象 try快执行完成后，会自动调用reponse.close方法
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
     * @throws Exception
     */
    public void runAsynchronousGet() throws Exception {
        Request request = new Request.Builder()
                .url(httpsGetHello)
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


    private void get() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GetExample example = new GetExample();
                    String response = example.run("https://raw.github" +
                            ".com/square/okhttp/master/README" +
                            ".md");
//                    String response = example.run("https://www.baidu.com");
                    System.out.println(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void post() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    PostExample postExample = new PostExample();
                    String json = postExample.bowlingJson("Jesse", "Jake");
                    String response = postExample.post("http://www.roundsapp.com/post", json);
                    System.out.println(response);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
