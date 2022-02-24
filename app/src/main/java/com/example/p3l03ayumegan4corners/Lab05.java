package com.example.p3l03ayumegan4corners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class Lab05 extends AppCompatActivity {
    String TAG = "com.example.lab05.sharedpreferences";
    LifecycleData currentRun, lifeTime;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView currentRunTV, lifeTimeTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab05);

        // load sharedpreferences
        sharedPreferences = getSharedPreferences(TAG, MODE_PRIVATE);
        // instantiate editor
        editor = sharedPreferences.edit();

        currentRun = new LifecycleData();
        currentRun.duration = "current run";

        // get lifecycledata from SharedPreferences as String
        String lifecycleDataAsString = sharedPreferences.getString("lifetime", "");
        // instantiate new Lifecycledata is string empty
            // else convert the JSON to LifecycleData obj
        if (lifecycleDataAsString.equals("")){
            lifeTime = new LifecycleData();
            lifeTime.duration = "lifetime";
        } else {
            lifeTime = LifecycleData.parseJSON(lifecycleDataAsString);
        }
        // instantiate TextViews
        lifeTimeTV = findViewById(R.id.lifetime);
        currentRunTV = findViewById(R.id.current);
        // display data on TextViews
        lifeTimeTV.setText(lifeTime.toString());
        currentRunTV.setText(currentRun.toString());
    }
}