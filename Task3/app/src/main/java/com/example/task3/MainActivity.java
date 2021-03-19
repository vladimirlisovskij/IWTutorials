package com.example.task3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements MainRecyclerAdapter.OnItemClick{

    private RecyclerView mainRecycler;

    private MainRecyclerAdapter adapter;

    private final static int ROW_COUNT = 2;

    public final static String FORM_KEY = "formKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainRecycler = findViewById(R.id.mainRecycler);
        initAdapter();
    }

    private void initAdapter() {
        adapter = new MainRecyclerAdapter();
        adapter.setOnItemClick(this);
        mainRecycler.setLayoutManager(new GridLayoutManager(this, ROW_COUNT));
        mainRecycler.setAdapter(adapter);
        adapter.setDataFormContainerList(simulateData());
    }

    private List<DataFormContainer> simulateData() {
        List<DataFormContainer> res = new ArrayList<>();
        for (int i = 0; i < 4; ++i) {
            res.add(new DataFormContainer("https://i.ibb.co/HTj14wR/cat1.jpg", "Володя", "+1234567890", "володя@мир.ру"));
            res.add(new DataFormContainer("", "name", "phone", "email"));
            res.add(new DataFormContainer("https://i.ibb.co/CtgyPtR/cat2.jpg", "вОлОдЯ", "+9876543212", "вОлОдЯ@мИр.Ру"));
            res.add(new DataFormContainer("https://i.ibb.co/D9dZTx4/cat3.jpg", "Volodya", "+987654345", "volodya@world.com"));
        }
        return res;
    }

    @Override
    public void itemClick(DataFormContainer dataFormContainer) {
        Intent intent = new Intent(this, Profile.class);
        intent.putExtra(FORM_KEY, dataFormContainer);
        startActivity(intent);
    }
}

