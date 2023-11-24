package com.example.eventlistener;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SecondScreen extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        insertData("onCreate", "onCreate called in Second screen");

        Button btn = findViewById(R.id.btnSecond);

        btn.setOnClickListener(view -> insertData("button click", "button clicked in Second screen"));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();
        insertData("onStart", "onStart called in Second screen");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        insertData("onResume", "onResume called in Second screen");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        insertData("onDestroy", "onDestroy called in Second screen");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStop() {
        super.onStop();
        insertData("onStop", "onStop called in Second screen");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPause() {
        super.onPause();
        insertData("onPause", "onPause called in Second screen");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        insertData("onBackPressed", "onBackPressed called in Second screen");
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertData(String event, String desc) {
        UserActions userActions = new UserActions(getApplicationContext());
        userActions.insertTask(event, desc, DateTimeFormatter.ofPattern("EEE dd-MMM-yyyy HH:mm:ss a").format(LocalDateTime.now()));
    }
}
