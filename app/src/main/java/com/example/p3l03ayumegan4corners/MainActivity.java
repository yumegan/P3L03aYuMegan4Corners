package com.example.p3l03ayumegan4corners;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    String TAG = "com.yumegan.lab03.sharedprefs";
    Button bLeft, bRight;
    TextView tLeft, tRight;
    SeekBar seekBar;
    TextView [] views;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ConstraintLayout layout;
    long startTime, clicks;
    float cps;

    TextView input_size_label;
    EditText input_size;
    Button submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tLeft = findViewById(R.id.topleft_textview);
        tRight = findViewById(R.id.topright_textview);
        bLeft = findViewById(R.id.bottomleft_button);
        bRight = findViewById(R.id.bottomright_button);
        seekBar = findViewById(R.id.seekbar);
        views = new TextView[]{tLeft, tRight, bLeft, bRight};
        layout = findViewById(R.id.activity_main_layout);

        input_size_label = findViewById(R.id.input_size_label);
        input_size = findViewById(R.id.input_size);
        submit_btn = findViewById(R.id.submit_btn);

        tLeft.setOnClickListener(this);
        tRight.setOnClickListener(this);
        bLeft.setOnClickListener(this);
        bRight.setOnClickListener(this);

        sharedPreferences = getSharedPreferences(TAG,MODE_PRIVATE);
        editor = sharedPreferences.edit();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int lastProgress;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                for (TextView x:views){
                    x.setTextSize(i); // int i is the progress
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { //record state
                lastProgress = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { //pop snackbar
                Snackbar snackbar = Snackbar.make(layout, "Font Size Changed To " + seekBar.getProgress() + "sp", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        seekBar.setProgress(lastProgress);
                        for (TextView x:views){
                            x.setTextSize(lastProgress);
                        }
                        Snackbar.make(layout, "Font Size Reverted Back To " + lastProgress + "sp", Snackbar.LENGTH_LONG);
                    }
                });
                snackbar.setActionTextColor(Color.BLUE);
                View snackBarView = snackbar.getView();
                TextView textView = snackBarView.findViewById(R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                snackbar.show();
            }
        });
        layout.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View view) {
                editor.clear().apply();
                setInitialValues();
                return false;
            }
        });
        setInitialValues();
        startTime = System.currentTimeMillis();
    }

    private void setInitialValues() {
        for (TextView x:views) {
            x.setText(sharedPreferences.getString(x.getTag().toString(), "0"));

            // reset - if something goes wrong
//            x.setText(sharedPreferences.getString("0", "0"));
        }
        seekBar.setProgress(30);
    }

    @Override
    public void onClick(View view) {
        TextView x = (TextView) view;
        x.setText(""+(Integer.parseInt((String) x.getText())+1));
        editor.putString(x.getTag().toString(), x.getText().toString());
        editor.apply();
        cps = ++clicks/((System.currentTimeMillis() - startTime)/1000f); //convert to seconds // f - float
        Toast.makeText(this, "" + cps, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setInitialValues();
    }

    public void submitSize(View view) {
        int new_size = 30;
        try {
            new_size = Integer.parseInt(String.valueOf(input_size.getText()));
        }
        catch (NumberFormatException e) {
            // It's OK to ignore "e" here because returning a default value is the documented behaviour on invalid input.
            input_size_label.setText("Choose a number between 5 and 100");
        }
//        System.out.println(new_size);
        if (new_size < 5 || new_size > 100) {
            input_size_label.setText("Choose a number between 5 and 100");
        }
        else{

            input_size_label.setText(getString(R.string.input_text_size));
            seekBar.setProgress(new_size);

        }
        // 5 to 100
    }
}