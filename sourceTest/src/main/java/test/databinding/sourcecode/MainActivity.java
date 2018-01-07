package test.databinding.sourcecode;

import java.util.List;

import com.dn.inter.IBinderTest;
import com.dn_sindy_hongbao.R;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.getSystemService(Context.ACCESSIBILITY_SERVICE);
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(0);
        for(AccessibilityServiceInfo info:enabledAccessibilityServiceList){
        	Log.i("INFO", "des:"+info.getDescription());
        }
    }
    public void start(View view)
    {
    	view.callOnClick();
        Intent intent=new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
        InputMethodManager im = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        this.bindService(intent,new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				IBinderTest asInterface = IBinderTest.Stub.asInterface(service);
				asInterface.testBinder();
			}
		},Context.BIND_AUTO_CREATE);
    }



}
