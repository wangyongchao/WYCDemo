
package com.weishop.test.service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.weishop.test.R;
import com.weishop.test.performance.memory.LeakActivity;


public class ServiceActivity extends Activity implements View.OnClickListener {
    private static int count = 0;

    MyServiceConnection myServiceConnection = new MyServiceConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ServiceActivity onCreate");

        setContentView(R.layout.activity_service);
        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
        findViewById(R.id.bind).setOnClickListener(this);
        findViewById(R.id.unbind).setOnClickListener(this);
        findViewById(R.id.another).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent bindIntent = new Intent(this, LocalService.class);
        switch (v.getId()) {
            case R.id.start:
                Intent intent = new Intent(this, LocalService.class);
                intent.putExtra("name", "dafds" + (++count));
                startService(intent);

//                startForegroundService(intent);


                break;

            case R.id.stop:
                stopService(new Intent(this, LocalService.class));

                break;
            case R.id.bind:

//                bindIntent.setAction("aaaa");
                bindService(bindIntent, myServiceConnection, BIND_AUTO_CREATE);

                break;
            case R.id.unbind:
                unbindService(myServiceConnection);
                break;
            case R.id.another:
                startActivity(new Intent(this, LeakActivity.class));
//                bindIntent.setAction("bbbb");
//                bindService(bindIntent, new ServiceConnection() {
//                    @Override
//                    public void onServiceConnected(ComponentName name, IBinder service) {
//                        System.out.println("onServiceConnected another=" + service);
//                    }
//
//                    @Override
//                    public void onServiceDisconnected(ComponentName name) {
//
//                    }
//                }, BIND_AUTO_CREATE);
                break;
        }

    }

    class MyServiceConnection implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("onServiceConnected service=" + service);
            LocalService.LocalBinder binder = (LocalService.LocalBinder) service;
            binder.startDownload();
//            try {
//                MyAIDLService myAIDLService = MyAIDLService.Stub.asInterface(service);
//                int plus = myAIDLService.plus(3, 4);
//                System.out.println("plus=" + plus);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("onServiceDisconnected name=" + name.getClassName());
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("ServiceActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("ServiceActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("ServiceActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("ServiceActivity onDestroy");
    }
}
