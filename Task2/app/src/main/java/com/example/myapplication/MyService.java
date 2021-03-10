package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    public static final String TEXT_KEY = "text_key";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent myIntent = new Intent(TopFragment.BROADCAST_KEY);
        myIntent.putExtra(TopFragment.PARAM_KEY, intent.getStringExtra(TEXT_KEY));
        myIntent.putExtra(TopFragment.TASK_KEY, TopFragment.MY_TASK);
        myIntent.putExtra(TopFragment.STATUS_KEY, TopFragment.TASK_COMPLETE);
        sendBroadcast(myIntent);
        stopSelf();
        return START_NOT_STICKY;
    }
}