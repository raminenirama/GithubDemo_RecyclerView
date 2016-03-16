package com.ramakrishna.githubdemo.request;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

import org.json.JSONArray;

public class VolleyRequestQueue
{
    private static VolleyRequestQueue mInstance;
    private static Context mContext;
    private RequestQueue mRequestQueue;

    private VolleyRequestQueue(Context context)
    {
        mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized VolleyRequestQueue getInstance(Context context)
    {
        if (mInstance == null)
        {
            mInstance = new VolleyRequestQueue(context);
        }
        return mInstance;
    }

    public void requestDataOverNetwork(String tag, int method, String url, JSONArray jsonArray, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener)
    {
        RequestQueue requestQueue = mInstance.getRequestQueue();
        JSONArrayRequest mJsonArrayRequest = new JSONArrayRequest(method, url, jsonArray, listener, errorListener);
        mJsonArrayRequest.setTag(tag);
        requestQueue.add(mJsonArrayRequest);
    }

    public RequestQueue getRequestQueue()
    {
        if (mRequestQueue == null)
        {
            Cache cache = new DiskBasedCache(mContext.getCacheDir(), 10 * 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();
        }
        return mRequestQueue;
    }
}
