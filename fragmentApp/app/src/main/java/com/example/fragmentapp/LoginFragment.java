package com.example.fragmentapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    EditText phone;
    EditText email;

    Button but;

    public final static int SMS_CODE = 123;

    public static final String PHONE_KEY = "phoneKey";
    public static final String EMAIL_KEY = "emailKey";

    public static LoginFragment newInstance(@NonNull String phone,@NonNull String email) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(PHONE_KEY, phone);
        args.putString(EMAIL_KEY, email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    boolean isOk(){
        return phone.getText().length() == 11 && email.getText().length() > 6;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        phone = view.findViewById(R.id.phone);
        email = view.findViewById(R.id.email);
        but = view.findViewById(R.id.toSecond);
        but.setEnabled(false);
        if (getArguments() != null) {
            String phoneText = getArguments().getString(PHONE_KEY);
            String emailText = getArguments().getString(EMAIL_KEY);
            phone.setText(phoneText);
            email.setText(emailText);
            but.setEnabled(isOk());

        }

        TextWatcher validator = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                but.setEnabled(isOk());
            }
        };

        phone.addTextChangedListener(validator);
        email.addTextChangedListener(validator);

        but.setOnClickListener(v -> {
            int permStat = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_SMS);
            if (permStat == PackageManager.PERMISSION_GRANTED) {
                LoginForm loginForm = new LoginForm();
                loginForm.setEmail(String.valueOf(email.getText()));
                loginForm.setPhone(String.valueOf(phone.getText()));
                Intent intent = new Intent(MainActivity.BROADCAST_KEY);
                intent.putExtra(MainActivity.TASK_KEY, MainActivity.TO_SECOND);
                intent.putExtra(MainActivity.TASK_PARAM, loginForm);
                getActivity().sendBroadcast(intent);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.READ_SMS}, SMS_CODE);
            }
        });
    }
}