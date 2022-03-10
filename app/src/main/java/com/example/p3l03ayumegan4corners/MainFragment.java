package com.example.p3l03ayumegan4corners;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainFragment extends Fragment {

    private ViewPager2 mViewPager2;
    Button mButton;
    int position;

    public static Fragment newInstance(ViewPager2 mViewPager2, int position) {
        MainFragment fragment = new MainFragment();
        fragment.mViewPager2 = mViewPager2;
        fragment.position = position;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // add to tablayout
        TabLayout tabLayout = getActivity().findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, mViewPager2,
                (tab, position) -> tab.setText("#" + (position+1))
        ).attach();

        //access the button
        Button mButton = view.findViewById(R.id.pressme);

        //set text to identify fragment position
        mButton.setText("Press " + position);
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(), "toasty"+position, Toast.LENGTH_LONG).show();
            }
        });
    }
}
