package com.example.sid.aravali_v_21;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sid on 20/11/16.
 */
public class Util {

    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager;
        connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo != null && activeNetworkInfo.isConnected());
    }

}
