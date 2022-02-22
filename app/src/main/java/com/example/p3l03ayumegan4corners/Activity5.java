package com.example.p3l03ayumegan4corners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Activity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
    }

    public void toLayout6(View view) {
        Intent intent = new Intent(this, Activity6.class);
        startActivity(intent);
    }
}