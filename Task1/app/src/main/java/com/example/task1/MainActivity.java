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

    private String formRequestKey;
    private String formResultKey;
    private String savedFormKey;

    private final int FORM_REQUEST = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        place = findViewById(R.id.place);
        but = findViewById(R.id.edit);

        formRequestKey = getString(R.string.formRequestKey);
        formResultKey = getString(R.string.formResultKey);
        savedFormKey = getString(R.string.savedFormKey);

        but.setOnClickListener(this);

        if (savedInstanceState != null) {
            form = (Form) savedInstanceState.getSerializable(savedFormKey);
        } else {
            form = new Form();
            form.setName(getString(R.string.defaultName));
            form.setDate(getString(R.string.defaultDate));
            form.setPlace(getString(R.string.defaultPlace));
        }

        name.setText(form.getName());
        date.setText(form.getDate());
        place.setText(form.getPlace());
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ActivityEditor.class);
        intent.putExtra(formRequestKey, form);
        startActivityForResult(intent, FORM_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FORM_REQUEST && resultCode == RESULT_OK) {
            form = (Form) data.getSerializableExtra(formResultKey);
            name.setText(form.getName());
            date.setText(form.getDate());
            place.setText(form.getPlace());
        }
    }


    @Override
    public void  onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(savedFormKey, form);
    }
}

