package com.example.p3l03ayumegan4corners;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Lab07 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab07);
        // begin transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // add contents to container
        ft.add(R.id.fragment_container, FragmentB.newInstance(17, "Megan Thee Yu"), "FragmentB");
        // complete the changes added above
        ft.commit();
    }

    public void update_greeting(View view) {
        FragmentB fragmentB = (FragmentB)getSupportFragmentManager().findFragmentByTag("FragmentB");
        ((TextView)(fragmentB.view.findViewById(R.id.b_textview))).setText(R.string.greeting2_lab07);
    }
}