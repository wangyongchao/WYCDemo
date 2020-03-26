package com.weishop.test.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.Toast;

import com.weishop.test.R;
import com.weishop.test.util.TestUtils;

import java.io.File;
import java.util.Map;


public class WebViewActivity extends Activity implements View.OnClickListener {


    WebView mWebView;
    //    String mUrl = "http://bitkiller.duapp.com/jsobj.html";
    String mUrl1 = "file:///android_asset/html/test.html";
    String mUrl2 = "file:///android_asset/html/test2.html";
    String mUrl3 = "file:///android_asset/html/js3.html";
    Object mJsObj = new JSInterface();
    SoundPoolHelper soundPoolHelper;

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
        findViewById(R.id.stopbtan).setOnClickListener(this);
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
//        File externalFilesDir = this.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
//        System.out.println("externalFilesDir="+externalFilesDir);
//        File file = new File(externalFilesDir, "war_bg.mp3");
//        if (v.getId() == R.id.startbtan) {
//            soundPoolHelper.playMusic(file.getAbsolutePath(), 0.6f, true);
//        } else {
//            if (soundPoolHelper != null) {
//                soundPoolHelper.stopMusic(file.getAbsolutePath());
//            }
//        }

        File externalFilesDir = this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

        System.out.println("externalFilesDir=" + externalFilesDir);
        String mUrl1 = "file:///" + externalFilesDir.getAbsolutePath() + "/html/test.html";
        mWebView.loadUrl(mUrl1);

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
