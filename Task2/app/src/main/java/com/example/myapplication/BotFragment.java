package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class BotFragment extends Fragment {

    private EditText editText;
    private Button button;

    public static BotFragment newInstance() {
        return new BotFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bot, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editTextBot);
        button = view.findViewById(R.id.botBut);
        button.setOnClickListener(clickedView -> {
            Intent intent = new Intent(getActivity(), MyService.class);
            intent.putExtra(MyService.TEXT_KEY, String.valueOf(editText.getText()));
            getActivity().startService(intent);
        });
    }
}