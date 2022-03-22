package com.example.p3l03ayumegan4corners;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Lab07 extends AppCompatActivity {
    TextView change_frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lab07);

        change_frag = findViewById(R.id.change_frag);
        // begin transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // add contents to container
        ft.add(R.id.fragment_container, FragmentB.newInstance(17, "Megan Thee Yu"), "FragmentB");
        // complete the changes added above
        ft.commit();
    }

    public void update_greeting(View view) {
//        System.out.println("change_frag " + change_frag);
        String cur_msg = (String) change_frag.getText();
        System.out.println(cur_msg + getString(R.string.next_frag));

        FragmentB fragmentB = (FragmentB)getSupportFragmentManager().findFragmentByTag("FragmentB");

        if (cur_msg.equals(getString(R.string.next_frag))){
            ((TextView)(fragmentB.view.findViewById(R.id.b_textview))).setText(R.string.greeting2_lab07);

            change_frag.setText(R.string.hide_msg);
        }
        else{
            ((TextView)(fragmentB.view.findViewById(R.id.b_textview))).setText(getString(R.string.greeting_lab07, "Megan Thee Yu"));
            change_frag.setText(R.string.next_frag);

        }


    }
}