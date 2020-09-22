package com.weishop.test.webview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

import java.util.Map;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


public class WebViewActivity extends Activity implements View.OnClickListener {


    WebView mWebView;
        String mUrl = "https://leetcode-cn.com/problems/binary-tree-inorder-traversal/";
    String mUrl1 = "file:///android_asset/html/test.html";
    String mUrl2 = "file:///android_asset/html/test2.html";
    String mUrl3 = "file:///android_asset/html/js3.html";
    Object mJsObj = new JSInterface();
    SoundPoolHelper soundPoolHelper;
    private ImageView imageView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        ResourcesHelper.replaceContextRes(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebChromeClient(new TestWebChromeClient());
        mWebView.setWebViewClient(new TestWebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JSInterface(), "jsInterface");
        soundPoolHelper = new SoundPoolHelper(this, 5, AudioManager.STREAM_MUSIC);
        findViewById(R.id.startbtan).setOnClickListener(this);
        imageView=findViewById(R.id.imageview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    class TestWebChromeClient extends WebChromeClient {
        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            return super.onConsoleMessage(consoleMessage);
        }
    }


    class TestWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d(TestUtils.TAG, "onPageStarted url=" + url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d(TestUtils.TAG, "onPageFinished url=" + url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            Log.d(TestUtils.TAG, "onLoadResource url=" + url);
        }

        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view,
                                                          WebResourceRequest request) {
            String method = request.getMethod();
            Map<String, String> requestHeaders = request.getRequestHeaders();
            Uri url = request.getUrl();
            Log.d(TestUtils.TAG, "shouldInterceptRequest method=" + method + ",url=" + url);

            return super.shouldInterceptRequest(view, request);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_load:
                mWebView.loadUrl(mUrl1);
                break;

            case R.id.action_refresh:
                mWebView.reload();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

//        mWebView.loadUrl(mUrl);


        Glide.with(this).
                load(R.drawable.app_to_customer).
                apply(RequestOptions.centerCropTransform())
                .transition(withCrossFade())
                .into(imageView);
    }

    class JSInterface {
        @JavascriptInterface
        public String onButtonClick(String text) {
            System.out.println("onButtonClick text=" + text + ",thread=" + Thread.currentThread().getName());
            final String str = text;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "onButtonClick: text = " + str,
                            Toast.LENGTH_LONG).show();
                }
            });

            return "This text is returned from Java layer.  js text = " + text;
        }

        @JavascriptInterface
        public void onImageClick(String url, int width, int height) {
            final String str = "onImageClick: text = " + url + "  width = " + width + "  height =" +
                    " " + height;
            System.out.println("onImageClick text=" + str + ",thread=" + Thread.currentThread().getName());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
