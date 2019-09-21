package com.tani.app.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class NetworkHelper {

    public static boolean isNetworkAvailable(Context ctx) {
        NetworkInfo.State network = null;
        try {
            ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null) return false;
            network = info.getState();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }

    public static boolean isNetworkSocketAvailable(String ip, int port, int timeout) {
        boolean b = true;
        try {
            InetSocketAddress sa = new InetSocketAddress(ip, port);
            Socket ss = new Socket();
            ss.connect(sa, timeout);
            ss.close();
        } catch (SocketTimeoutException exception) {
            Log.e("SocketTimeoutException", ip + ":" + port + ". " + exception.getMessage());
            b = false;
        } catch (IOException exception) {
            Log.e("IOException","Unable to connect to " + ip + ":" + port + ". " + exception.getMessage());
            b = false;
        } catch (Exception exception) {
            exception.printStackTrace();
            Log.e("Exception", "Unable to connect to " + ip + ":" + port + ". " + exception.getMessage());
            b = false;
        }
        return b;
    }
}
