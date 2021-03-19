package com.example.task3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Profile extends AppCompatActivity {

    private ImageView avatarIV;

    private TextView nameTV;
    private TextView phoneTV;
    private TextView emailTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        avatarIV = findViewById(R.id.profileAvatarTV);
        nameTV = findViewById(R.id.profileNameTV);
        phoneTV = findViewById(R.id.profilePhoneTV);
        emailTV = findViewById(R.id.profileEmailTV);

        Intent intent = getIntent();

        DataFormContainer data = (DataFormContainer)intent.getSerializableExtra(MainActivity.FORM_KEY);
        Glide.with(avatarIV.getContext())
                .load(data.getAvatarHref())
                .centerCrop ()
                .placeholder ( R.drawable.ic_launcher_foreground)
                .into(avatarIV);
        nameTV.setText(data.getName());
        phoneTV.setText(data.getPhone());
        emailTV.setText(data.getEmail());
    }
}