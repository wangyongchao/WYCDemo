package com.weishop.test.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;

import com.weishop.test.MyApplication;


public class AutoPlayHelper {

    private final static AutoPlayHelper instance = new AutoPlayHelper();

    private AutoPlayHelper() {
        registerNetWorkStatus();
    }


    public static final AutoPlayHelper getInstance() {
        return instance;
    }

    private class DefaultNetworkCallback extends ConnectivityManager.NetworkCallback {
        @Override
        public void onAvailable(Network network) {
            super.onAvailable(network);
            LogUtils.d("onAvailable");
        }

        @Override
        public void onLost(Network network) {
            super.onLost(network);
            LogUtils.d("onLost");
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            super.onCapabilitiesChanged(network, networkCapabilities);
            if(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)){
                if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                    LogUtils.d("wifi网络已连接");
                }else {
                    LogUtils.d("移动网络已连接");
                }
            }
        }
    }

    private void registerNetWorkStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        NetworkRequest request = builder.build();
        connectivityManager.registerNetworkCallback(request, new DefaultNetworkCallback());
    }


}
