package com.example.task3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements MainRecyclerAdapter.OnItemClick{

    private RecyclerView mainRecycler;

    private MainRecyclerAdapter adapter;

    private final static int COLUMN_COUNT = 2;

    public final static String FORM_KEY = "formKey";

    private final static String PREF_KEY = "prefKey";
    private final static String JSON_KEY = "myJson";

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainRecycler = findViewById(R.id.mainRecycler);
        initAdapter();
        sharedPreferences = getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        if (sharedPreferences.contains(JSON_KEY)) {
            String rawData = sharedPreferences.getString(JSON_KEY, "");
            Gson gson = new Gson();
            DataFormContainer[] data = gson.fromJson(rawData, DataFormContainer[].class);
            adapter.setDataFormContainerList(new LinkedList<>(Arrays.asList(data)));
        } else {
            adapter.setDataFormContainerList(simulateData());
        }
    }

    private void initAdapter() {
        adapter = new MainRecyclerAdapter();
        adapter.setOnItemClick(this);
        mainRecycler.setLayoutManager(new GridLayoutManager(this, COLUMN_COUNT));
        mainRecycler.setAdapter(adapter);
    }

    private LinkedList<DataFormContainer> simulateData() {
        LinkedList<DataFormContainer> res = new LinkedList<>();
        for (int i = 0; i < 4; ++i) {
            res.add(new DataFormContainer("https://i.ibb.co/HTj14wR/cat1.jpg", "Володя", "+1234567890", "володя@мир.ру"));
            res.add(new DataFormContainer("", "name", "phone", "email"));
            res.add(new DataFormContainer("https://i.ibb.co/CtgyPtR/cat2.jpg", "вОлОдЯ", "+9876543212", "вОлОдЯ@мИр.Ру"));
            res.add(new DataFormContainer("https://i.ibb.co/D9dZTx4/cat3.jpg", "Volodya", "+987654345", "volodya@world.com"));
        }
        return res;
    }

    @Override
    public void itemClick(DataFormContainer dataFormContainer, int index) {
        FragmentManager manager = getSupportFragmentManager();
        Dialog myDialogFragment = new Dialog();
        myDialogFragment.setImgHref(dataFormContainer.getAvatarHref());
        myDialogFragment.setShowListener(v -> {
            Intent intent = new Intent(this, Profile.class);
            intent.putExtra(FORM_KEY, dataFormContainer);
            startActivity(intent);
            myDialogFragment.dismiss();
        });
        myDialogFragment.setDeleteListener(v -> {
            adapter.deleteItem(index);
            myDialogFragment.dismiss();
            List<DataFormContainer> dataFormContainerList = adapter.getDataFormContainerList();
            Gson gson = new Gson();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(JSON_KEY, gson.toJson(dataFormContainerList));
            editor.apply();
        });
        myDialogFragment.show(manager, "myDialog");
    }
}

