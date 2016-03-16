package com.ramakrishna.githubdemo.request;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.ramakrishna.githubdemo.R;
import com.ramakrishna.githubdemo.utils.Utilities;

public class RequestError
{
    public static void onRequestError(Context context, VolleyError error)
    {
        if (error instanceof NoConnectionError)
        {
            Utilities.showToastMessage(context, context.getResources().getString(R.string.connection_error));
        } else if (error instanceof AuthFailureError)
        {
            Utilities.showToastMessage(context, context.getResources().getString(R.string.auth_failure_error));
        } else if (error instanceof ServerError)
        {
            Utilities.showToastMessage(context, context.getResources().getString(R.string.server_error));
        } else if (error instanceof NetworkError)
        {
            Utilities.showToastMessage(context, context.getResources().getString(R.string.network_error));
        } else if (error instanceof ParseError)
        {
            Utilities.showToastMessage(context, context.getResources().getString(R.string.parse_error));
        } else if (error instanceof TimeoutError)
        {
            Utilities.showToastMessage(context, context.getResources().getString(R.string.timeout_error));
        }
    }
}
