package com.example.task1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView name;
    private TextView date;
    private TextView place;

    private Button but;

    private Form form;

    private final String FORM_REQUEST_KEY = "request";
    private final String FORM_RESULT_KEY = "result";
    private final String SAVED_FORM = "form";

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
            form = (Form) savedInstanceState.getSerializable(SAVED_FORM);
        } else {
            form = new Form();
            form.setName("Володя");
            form.setDate("11.01.2002");
            form.setPlace("Симферополь");
        }

        name.setText(form.getName());
        date.setText(form.getDate());
        place.setText(form.getPlace());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ActivityEditor.class);
        intent.putExtra(FORM_REQUEST_KEY, form);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            form = (Form) data.getSerializableExtra(FORM_RESULT_KEY);
            name.setText(form.getName());
            date.setText(form.getDate());
            place.setText(form.getPlace());
        }
    }


    @Override
    public void  onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(SAVED_FORM, form);
    }
}

