package com.android.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;

import org.springframework.http.HttpEntity;

import java.io.IOException;


class ServiceStatusUpdate extends Service {



    @SuppressLint({"MissingPermission", "HardwareIds"})
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String mPhoneNumber = "";
        TelephonyManager tMgr = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        if (tMgr != null) {
            mPhoneNumber = tMgr.getLine1Number();
        }
        while(true)
        {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                new DoBackgroundTask().execute(mPhoneNumber);
                e.printStackTrace();
            }
            return START_STICKY;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("StaticFieldLeak")
    private class DoBackgroundTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String response = "";
            String dataToSend = params[0];
            Log.i("DoBackgroundTask", "pullgroups");
            try {
                RestHandler.pullGroups(dataToSend);
            } catch (Exception e) {
                response = "{\"ERROR\":" + e.getMessage() + "}";
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            new Handler().postDelayed(new Runnable() {
                @SuppressLint("MissingPermission")
                public void run() {
                    String mPhoneNumber = "";
                    TelephonyManager tMgr = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
                    if (tMgr != null) {
                        mPhoneNumber = tMgr.getLine1Number();
                    }
                    RestHandler.pullGroups(mPhoneNumber);
                }
            }, 10000);
        }
    }
}
