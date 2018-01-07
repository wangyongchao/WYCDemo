package com.weishop.test.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.weishop.test.R;


public class WebViewActivity extends Activity {

    WebView mWebView;
    //    String mUrl = "http://bitkiller.duapp.com/jsobj.html";
    String mUrl1 = "file:///android_asset/html/test.html";
    String mUrl2 = "file:///android_asset/html/test2.html";
    String mUrl3 = "file:///android_asset/html/js3.html";
    Object mJsObj = new JSInterface();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JSInterface(), "jsInterface");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    class JSInterface {
        @JavascriptInterface
        public String onButtonClick(String text) {
            System.out.println("onButtonClick text=" + text + ",thread=" + Thread.currentThread().getName());
            final String str = text;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "onButtonClick: text = " + str, Toast.LENGTH_LONG).show();
                }
            });

            return "This text is returned from Java layer.  js text = " + text;
        }

        @JavascriptInterface
        public void onImageClick(String url, int width, int height) {
            final String str = "onImageClick: text = " + url + "  width = " + width + "  height = " + height;
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
