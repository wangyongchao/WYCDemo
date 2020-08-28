
package com.weishop.test.plugin;

import android.app.Activity;
import android.app.ActivityThread;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.weishop.test.R;

import java.io.File;

public class PluginActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);

        findViewById(R.id.plugin_test).setOnClickListener(this);

        String pluginPath = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/plugina.apk");
        File plugin = new File(pluginPath);
        try {
//            PluginManager.getInstance(this).loadPlugin(plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        ActivityThread activityThread = ActivityThread.currentActivityThread();
        String processName = activityThread.getProcessName();
        System.out.println(processName);


//        Intent intent = new Intent();
//        intent.setClassName("com.example.plugina", "com.example.plugina.MainActivity");
//        startActivity(intent);
    }
}
