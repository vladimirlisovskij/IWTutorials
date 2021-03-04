package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityEditor extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    EditText date;
    EditText place;

    Button but;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        intent = getIntent();

        name = findViewById(R.id.name_editor);
        date = findViewById(R.id.date_editor);
        place = findViewById(R.id.place_editor);

        if (intent.getExtras() != null) {
            name.setText(intent.getStringExtra("name"));
            date.setText(intent.getStringExtra("date"));
            place.setText(intent.getStringExtra("place"));
        } else  {
            name.setText(intent.getStringExtra("Имя"));
            date.setText(intent.getStringExtra("Дата рождения"));
            place.setText(intent.getStringExtra("Место рождения"));
        }

        but = findViewById(R.id.submit);
        but.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("date", date.getText().toString());
        intent.putExtra("place", place.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}