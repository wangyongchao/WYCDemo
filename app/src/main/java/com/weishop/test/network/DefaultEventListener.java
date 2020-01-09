package com.weishop.test.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;

public class DefaultEventListener extends EventListener {
    @Override
    public void callStart(Call call) {
        System.out.println("callStart =" + call.request().url());
    }

    @Override
    public void callEnd(Call call) {
        System.out.println("callEnd =" + call.request().url());
    }

    @Override
    public void dnsStart(Call call, String domainName) {
        System.out.println("dnsStart =" + call.request().url() + "," +
                "domainName=" + domainName);
    }

    @Override
    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
        String address = "";
        if (inetAddressList != null) {
            for (int i = 0; i < inetAddressList.size(); i++) {
                InetAddress inetAddress = inetAddressList.get(i);
                address = address + inetAddress.toString() + ",";
            }

        }
        System.out.println("dnsEnd =" + call.request().url() + "," +
                "domainName=" + domainName + ",address=" + address);
    }

    @Override
    public void connectStart(Call call,
                             InetSocketAddress inetSocketAddress,
                             Proxy proxy) {
        String hostName = "";
        if (inetSocketAddress != null) {

            hostName = inetSocketAddress.toString();
        }
        System.out.println("connectStart =" + call.request().url() + "," +
                "hostName=" + hostName);
    }

    @Override
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress
            , Proxy proxy, Protocol protocol) {
        String hostName = "";
        if (inetSocketAddress != null) {

            hostName = inetSocketAddress.toString();
        }
        System.out.println("connectEnd =" + call.request().url() + "," +
                "hostName=" + hostName);
    }

    @Override
    public void connectionReleased(Call call, Connection connection) {
        System.out.println("connectionReleased =" + call.request().url() + "," +
                "connection=" + connection.toString());
    }

    @Override
    public void secureConnectStart(Call call) {
        System.out.println("secureConnectStart =" + call.request().url());
    }

    @Override
    public void secureConnectEnd(Call call, Handshake handshake) {
        System.out.println("secureConnectEnd =" + call.request().url() + ",handshake=" + handshake.toString());
    }

    @Override
    public void requestHeadersStart(Call call) {
        System.out.println("requestHeadersStart =" + call.request().url());
    }

    @Override
    public void requestBodyStart(Call call) {
        System.out.println("requestBodyStart =" + call.request().url());
    }

    @Override
    public void responseBodyStart(Call call) {
        System.out.println("responseBodyStart =" + call.request().url());
    }

    @Override
    public void callFailed(Call call, IOException ioe) {
        System.out.println("callFailed =" + call.request().url() + ",ioe=" + ioe);
    }


}