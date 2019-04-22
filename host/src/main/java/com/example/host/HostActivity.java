package com.example.host;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.didi.virtualapk.PluginManager;

import java.io.File;

public class HostActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        this.findViewById(R.id.test).setOnClickListener(this);
        String pluginPath = Environment.getExternalStorageDirectory().getAbsolutePath().concat("/plugina.apk");
        File plugin = new File(pluginPath);
        try {
            PluginManager.getInstance(this.getApplication()).loadPlugin(plugin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
        intent.setClassName("com.example.plugina", "com.example.plugina.MainActivity");
        startActivity(intent);
    }
}
