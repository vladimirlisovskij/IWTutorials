package com.example.fragmentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TO_SECOND = "toSecond";
    public static final String TO_FIRST = "toFirst";

    public static final String TASK_KEY = "taskKey";
    public static final String BROADCAST_KEY = "com.fragmentExample.mainBroadCast";
    public static final String TASK_PARAM = "taskParam";

    BroadcastReceiver broadcastReceiver;

    LoginFragment loginFragment;
    ProfileFragment profileFragment;

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String task = intent.getStringExtra(TASK_KEY);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (task) {
                    case TO_SECOND:
                        fragmentTransaction.hide(loginFragment);
                        LoginForm loginForm = (LoginForm) intent.getSerializableExtra(TASK_PARAM);
                        fragmentTransaction.replace(R.id.profileFragment, ProfileFragment.newInstance(loginForm.getPhone(), loginForm.getEmail()));
                        fragmentTransaction.show(profileFragment);
                        break;
                    case TO_FIRST:
                        fragmentTransaction.hide(profileFragment);
                        fragmentTransaction.replace(R.id.loginFragment, LoginFragment.newInstance(" Телефон", "email"));
                        fragmentTransaction.show(loginFragment);
                        break;
                }
                fragmentTransaction.commit();
            }
        };
        IntentFilter intentFilter = new IntentFilter(BROADCAST_KEY);
        registerReceiver(broadcastReceiver, intentFilter);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.loginFragment, LoginFragment.newInstance("+1234567890", "володя@мир.ру"));
        fragmentTransaction.add(R.id.profileFragment, ProfileFragment.newInstance("", ""));
        loginFragment = (LoginFragment) fragmentManager.findFragmentById(R.id.loginFragment);
        profileFragment = (ProfileFragment) fragmentManager.findFragmentById(R.id.profileFragment);
        fragmentTransaction.hide(profileFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LoginFragment.SMS_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Toast.makeText(this, "SMS NOT OK", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

