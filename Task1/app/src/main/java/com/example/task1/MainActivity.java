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

    private String FORM_REQUEST_KEY;
    private String FORM_RESULT_KEY;
    private String SAVED_FORM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        place = findViewById(R.id.place);
        but = findViewById(R.id.edit);

        FORM_REQUEST_KEY = getResources().getString(R.string.formRequestKey);
        FORM_RESULT_KEY = getResources().getString(R.string.formResultKey);
        SAVED_FORM = getResources().getString(R.string.savedFormKey);

        but.setOnClickListener(this);

        if (savedInstanceState != null) {
            form = (Form) savedInstanceState.getSerializable(SAVED_FORM);
        } else {
            form = new Form();
            form.setName(getResources().getString(R.string.defaultName));
            form.setDate(getResources().getString(R.string.defaultDate));
            form.setPlace(getResources().getString(R.string.defaultPlace));
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

