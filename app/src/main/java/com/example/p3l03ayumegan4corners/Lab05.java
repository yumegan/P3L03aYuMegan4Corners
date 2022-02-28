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
        // get current enclosing method name
        String currentEnclosingMethod = new Throwable()
                .getStackTrace()[0]
                .getMethodName();
        updateCount(currentEnclosingMethod);
    }

    private void displayData() {
        // display data on TextViews
        lifeTimeTV.setText(lifeTime.toString());
        currentRunTV.setText(currentRun.toString());
    }

    // convert lifetime to String and store in SharedPref
    public void storeData(){
        editor.putString("lifetime", lifeTime.toJSON()).apply();
    }

    public void updateCount(String currentEnclosingMethod){
        // pass name to LifecycleData to update count
        currentRun.updateEvent(currentEnclosingMethod);
        lifeTime.updateEvent(currentEnclosingMethod);
        displayData();
        storeData();
    }






}