package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class ActivityEditor extends AppCompatActivity implements View.OnClickListener {

    EditText name;
    EditText date;
    EditText place;

    Button but;

    Intent intent;

    private final String FORM_REQUEST_KEY = "request";
    private final String FORM_RESULT_KEY = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        intent = getIntent();

        name = findViewById(R.id.name_editor);
        date = findViewById(R.id.date_editor);
        place = findViewById(R.id.place_editor);

        if (intent.getExtras() != null) {
            Form form = (Form) intent.getSerializableExtra(FORM_REQUEST_KEY);
            name.setText(form.getName());
            date.setText(form.getDate());
            place.setText(form.getPlace());
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
        Form form = new Form();
        form.setName(name.getText().toString());
        form.setDate(date.getText().toString());
        form.setPlace(place.getText().toString());
        intent.putExtra(FORM_RESULT_KEY, form);
        setResult(RESULT_OK, intent);
        finish();
    }
}