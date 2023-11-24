package com.example.eventlistener;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private final List<DataModel> listItem = new ArrayList<>();
    private EventsAdapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {

            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            Button button = findViewById(R.id.btn);
            Button buttonNext = findViewById(R.id.btnNext);
            Button btnGetData = findViewById(R.id.btnGetData);

            insertData("onCreate", "Screen Created in First screen");

            buttonNext.setOnClickListener(view -> {
                insertData("buttonNext", "buttonNext clicked in First screen");
                Intent intent = new Intent(MainActivity.this, SecondScreen.class);
                startActivity(intent);
            });

            button.setOnClickListener(view -> insertData("button click", "button clicked in First screen"));

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,
                    LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, linearLayoutManager.getOrientation()));
            adapter = new EventsAdapter(MainActivity.this, listItem);
            recyclerView.setAdapter(adapter);


            btnGetData.setOnClickListener(view -> {

                listItem.clear();
                UserActions userActions = new UserActions(getApplicationContext());
                try {
                    listItem.addAll(userActions.getAllUsers());
                    adapter.notifyDataSetChanged();
                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertData(String event, String desc) {
        UserActions userActions = new UserActions(getApplicationContext());
        userActions.insertTask(event, desc, DateTimeFormatter.ofPattern("EEE dd-MMM-yyyy HH:mm:ss a").format(LocalDateTime.now()));
    }
}