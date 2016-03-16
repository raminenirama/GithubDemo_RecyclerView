package com.ramakrishna.githubdemo.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

public class ConnectionDetector
{
    public static boolean isConnectedToInternet(Context context)
    {
        if (context != null)
        {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null)
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    Network[] networks = connectivityManager.getAllNetworks();
                    NetworkInfo networkInfo;
                    for (Network mNetwork : networks)
                    {
                        networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                        if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED))
                        {
                            return true;
                        }
                    }
                } else
                {
                    NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                    if (info != null)
                    {
                        for (int i = 0; i < info.length; i++)
                        {
                            if (info[i].getState() == NetworkInfo.State.CONNECTED)
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }
}
