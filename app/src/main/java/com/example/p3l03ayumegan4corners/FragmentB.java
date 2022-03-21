package com.example.p3l03ayumegan4corners;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {
    View view;
    public static Fragment newInstance(int x, String s){
        FragmentB fragmentB = new FragmentB();
        Bundle args = new Bundle();
        args.putInt("MyAge", x);
        args.putString("MyName", s);
        fragmentB.setArguments(args);
        return fragmentB;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        int age = getArguments().getInt("MyAge", 10000000);
        String name = getArguments().getString("MyName", "Megan Thee Stallion");
        TextView textview = view.findViewById(R.id.b_textview);
        String greeting = getString(R.string.greeting_lab07, name);
        textview.setText(greeting);
        Button button = view.findViewById(R.id.b_button);
        String count = getString(R.string.click_me, age);
        button.setText(count);
    }
}
