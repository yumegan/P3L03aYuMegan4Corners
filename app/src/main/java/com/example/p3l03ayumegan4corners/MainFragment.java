package com.example.p3l03ayumegan4corners;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
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

    MediaPlayer mp; // unused?

    public static Fragment newInstance(ViewPager2 mViewPager2, int position) {
        MainFragment fragment = new MainFragment();
        //mViewPager2.setPageTransformer(new ZoomOutPageTransformer());
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

        System.out.println("position " + position);
        switch(position){
            case 0:
                mp = MediaPlayer.create(getActivity(), R.raw.sneeze);
                break;
            case 1:
                mp = MediaPlayer.create(getActivity(), R.raw.arcade);
                break;
            case 2:
                mp = MediaPlayer.create(getActivity(), R.raw.kiss);
                break;
            case 3:
                mp = MediaPlayer.create(getActivity(), R.raw.toilet);
                break;
            case 4:
                mp = MediaPlayer.create(getActivity(), R.raw.eating);
                break;
            default:
                mp = MediaPlayer.create(getActivity(), R.raw.sneeze);
        }
        mp.start();


        // add to tablayout
        TabLayout tabLayout = getActivity().findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, mViewPager2,
                (tab, position) -> tab.setText("#" + (position+1))
        ).attach();

        //access the button
        Button mButton = view.findViewById(R.id.pressme);

        //set text to identify fragment position
        mButton.setText("Press " + (position+1));
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(), "toasty #"+position+"!", Toast.LENGTH_LONG).show();
            }
        });
    }



//    public void noise(Context context){
//
//    }
}

//class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
//    private static final float MIN_SCALE = 0.85f;
//    private static final float MIN_ALPHA = 0.5f;
//    MediaPlayer mp; // unused?
//
//    public void transformPage(View view, float position) {
////        mp = MediaPlayer.create(getParentActivity(), R.raw.sneeze);
////        mp.start();
//
//        int pageWidth = view.getWidth();
//        int pageHeight = view.getHeight();
//
//        if (position < -1) { // [-Infinity,-1)
//            // This page is way off-screen to the left.
//            view.setAlpha(0f);
//
//        } else if (position <= 1) { // [-1,1]
//            // Modify the default slide transition to shrink the page as well
//            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
//            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
//            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
//            if (position < 0) {
//                view.setTranslationX(horzMargin - vertMargin / 2);
//            } else {
//                view.setTranslationX(-horzMargin + vertMargin / 2);
//            }
//
//            // Scale the page down (between MIN_SCALE and 1)
//            view.setScaleX(scaleFactor);
//            view.setScaleY(scaleFactor);
//
//            // Fade the page relative to its size.
//            view.setAlpha(MIN_ALPHA +
//                    (scaleFactor - MIN_SCALE) /
//                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));
//
//        } else { // (1,+Infinity]
//            // This page is way off-screen to the right.
//            view.setAlpha(0f);
//        }
//    }
//}
