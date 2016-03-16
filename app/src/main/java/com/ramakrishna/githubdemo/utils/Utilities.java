package com.ramakrishna.githubdemo.utils;

import android.content.Context;
import android.widget.Toast;

public class Utilities
{
    public static void showToastMessage(Context context, String message)
    {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
