package com.example.haadee.customcalendar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalenderFragment extends Fragment {


    private static String ARG_POS = "position";

    public CalenderFragment() {
        // Required empty public constructor
    }

    public static CalenderFragment getInstance(int position){
        CalenderFragment calenderFragment = new CalenderFragment();
        Bundle bundleArgs = new Bundle();
        bundleArgs.putInt(ARG_POS, position);
        calenderFragment.setArguments(bundleArgs);
        return calenderFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calender, container, false);


        return v;
    }

}
