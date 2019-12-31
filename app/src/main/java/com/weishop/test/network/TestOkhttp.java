package com.weishop.test.network;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;

/**
 * Created by wangyongchao on 2019/12/30 17:10
 */
public class TestOkhttp {

    private final int cacheSize = 10 * 1024 * 1024; // 10 MiB
    public static String redirect = "http://publicobject.com/helloworld.txt";//get请求永久重定向
    public static String httpsGetURl = "https://raw.github.com/square/okhttp/master/README.md";
    //get请求重定向
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

                        Cache cache = new Cache(mContext.getExternalCacheDir(), cacheSize);
                        HttpUtils.createSslFactory(builder,
                                new InputStream[]{mContext.getAssets().open("charlesCertificate" +
                                        ".pem")});
                        mOkHttpClient = builder
                                .cache(cache)
                                .hostnameVerifier(new HttpUtils.TrustAllHostnameVerifier())
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

    public void testAsync() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    testResponseCache();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 响应缓存
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
