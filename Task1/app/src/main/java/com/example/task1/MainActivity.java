package com.example.task1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView name;
    TextView date;
    TextView place;

    Button but;

    String name_str;
    String date_str;
    String place_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        place = findViewById(R.id.place);

        but = findViewById(R.id.edit);
        but.setOnClickListener(this);

        if (savedInstanceState != null) {
            name_str = savedInstanceState.getString("name");
            date_str = savedInstanceState.getString("date");
            place_str = savedInstanceState.getString("place");
        } else {
            name_str = "Володя";
            date_str = "11.01.2002";
            place_str = "Симферополь";
        }

        name.setText(name_str);
        date.setText(date_str);
        place.setText(place_str);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ActivityEditor.class);
        intent.putExtra("name", name_str);
        intent.putExtra("date", date_str);
        intent.putExtra("place", place_str);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0 || data == null || data.getExtras() == null) {
            return;
        }
        name_str = data.getStringExtra("name");
        date_str = data.getStringExtra("date");
        place_str = data.getStringExtra("place");
        name.setText(name_str);
        date.setText(date_str);
        place.setText(place_str);
    }


    @Override
    public void  onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("deb", "save");
        savedInstanceState.putString("name", name_str);
        savedInstanceState.putString("date", date_str);
        savedInstanceState.putString("place", place_str);
    }
}