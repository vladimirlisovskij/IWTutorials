package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TopFragment topFragment;
    private BotFragment botFragment;

    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.topFragment, new TopFragment());
        transaction.add(R.id.botFragment, new BotFragment());
        transaction.commit();

        topFragment = (TopFragment) manager.findFragmentById(R.id.topFragment);
        botFragment = (BotFragment) manager.findFragmentById(R.id.botFragment);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}