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

    private EditText name;
    private EditText date;
    private EditText place;

    private Button but;

    private String FORM_REQUEST_KEY;
    private String FORM_RESULT_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        name = findViewById(R.id.name_editor);
        date = findViewById(R.id.date_editor);
        place = findViewById(R.id.place_editor);

        but = findViewById(R.id.submit);

        Intent intent = getIntent();

        FORM_RESULT_KEY = getResources().getString(R.string.formResultKey);
        FORM_REQUEST_KEY = getResources().getString(R.string.formRequestKey);

        if (intent.getExtras() != null) {
            Form form = (Form) intent.getSerializableExtra(FORM_REQUEST_KEY);
            name.setText(form.getName());
            date.setText(form.getDate());
            place.setText(form.getPlace());
        } else  {
            name.setText(intent.getStringExtra(getResources().getString(R.string.nameLab)));
            date.setText(intent.getStringExtra(getResources().getString(R.string.dateLab)));
            place.setText(intent.getStringExtra(getResources().getString(R.string.placeLab)));
        }

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