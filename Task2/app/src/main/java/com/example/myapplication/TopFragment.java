package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TopFragment extends Fragment {

    private TextView text;

    private BroadcastReceiver broadcastReceiver;

    public final static String MY_TASK = "send_text";


    public final static String TASK_KEY = "task_key";
    public final static String STATUS_KEY = "status_key";
    public final static String PARAM_KEY = "param_key";
    public final static String BROADCAST_KEY = "com.task2.myBroadcast";

    public final static int TASK_COMPLETE = 1234;

    public static TopFragment newInstance() {
        return new TopFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String task = intent.getStringExtra(TASK_KEY);
                int status = intent.getIntExtra(STATUS_KEY, 0);
                if (MY_TASK.equals(task)) {
                    if (status == TASK_COMPLETE) {
                        text.setText(String.format("Привет, %s", intent.getStringExtra(PARAM_KEY)));
                    }
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(BROADCAST_KEY);
        getContext().registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        text = view.findViewById(R.id.textTop);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getContext().unregisterReceiver(broadcastReceiver);
    }
}