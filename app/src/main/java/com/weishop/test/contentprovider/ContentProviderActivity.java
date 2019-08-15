
package com.weishop.test.contentprovider;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.UserDictionary;
import android.view.View;

import com.weishop.test.R;

public class ContentProviderActivity extends Activity implements View.OnClickListener {

    String[] mProjection = {
            UserDictionary.Words._ID,
            UserDictionary.Words.WORD,
            UserDictionary.Words.LOCALE};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contentprovider);

        findViewById(R.id.add_btn).setOnClickListener(this);
        findViewById(R.id.delete_btn).setOnClickListener(this);
        findViewById(R.id.update_btn).setOnClickListener(this);
        findViewById(R.id.select_btn).setOnClickListener(this);
        findViewById(R.id.start).setOnClickListener(this);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("ContentProviderActivity onSaveInstanceState");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        System.out.println("ContentProviderActivity onSaveInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.select_btn) {
            Cursor cursor = getContentResolver().
                    query(UserDictionary.Words.CONTENT_URI, mProjection, null, null, null);
            int count = cursor.getCount();
            System.out.println("count=" + count);
        } else if (v.getId() == R.id.add_btn) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(UserDictionary.Words.APP_ID, "example.user");
            contentValues.put(UserDictionary.Words.LOCALE, "en_US");
            contentValues.put(UserDictionary.Words.WORD, "insert");
            contentValues.put(UserDictionary.Words.FREQUENCY, "100");
            Uri uri = getContentResolver().insert(UserDictionary.Words.CONTENT_URI, contentValues);
            System.out.println("uri=" + uri);
        } else if (v.getId() == R.id.start) {
            startActivity(new Intent(this, PickContact.class));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("ContentProviderActivity onDestroy");
    }
}
