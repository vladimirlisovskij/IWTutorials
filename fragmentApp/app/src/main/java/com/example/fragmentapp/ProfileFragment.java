package com.example.fragmentapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ProfileFragment extends Fragment {

    public static final String PHONE_KEY = "phoneKey";
    public static final String EMAIL_KEY = "emailKey";

    TextView emailTV;
    TextView phoneTV;

    Button but;

    public static ProfileFragment newInstance(@NonNull String phone, @NonNull String email) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(PHONE_KEY, phone);
        args.putString(EMAIL_KEY, email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailTV = view.findViewById(R.id.emailTV);
        phoneTV = view.findViewById(R.id.phoneTV);
        but = view.findViewById(R.id.toFirst);

        if (getArguments() != null) {
            emailTV.setText(getArguments().getString(PHONE_KEY));
            phoneTV.setText(getArguments().getString(EMAIL_KEY));
        }

        but.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.BROADCAST_KEY);
            intent.putExtra(MainActivity.TASK_KEY, MainActivity.TO_FIRST);
            getActivity().sendBroadcast(intent);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}