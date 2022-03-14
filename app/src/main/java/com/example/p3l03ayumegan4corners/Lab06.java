package com.example.p3l03ayumegan4corners;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.media.MediaPlayer;
import android.os.Bundle;

public class Lab06 extends AppCompatActivity {
    ViewPager2 mViewPager2;
    RecyclerView.Adapter mMyFragmentStateAdapter;
    int NUM_ITEMS = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab06);
        // assign the instance of ViewPager2
        mViewPager2 = findViewById(R.id.container);
        // create an adapter for ViewPager2
        mMyFragmentStateAdapter = new MyFragmentStateAdapter(this);
        // set the adapter for the ViewPager
        mViewPager2.setAdapter(mMyFragmentStateAdapter);

        MediaPlayer song = MediaPlayer.create(Lab06.this, R.raw.sneeze);
        song.start();
    }

    private class MyFragmentStateAdapter extends FragmentStateAdapter {

        public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return MainFragment.newInstance(mViewPager2, position);
        }

        @Override
        public int getItemCount() {
            return NUM_ITEMS;
        }
    }
}