package com.example.haadee.customcalendar;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {
    public static String ARG_MONTH = "month";
    public static String ARD_YEAR = "year";


    private MainViewModel mMainViewModel;
    private Calendar mClendar;


    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mClendar = Calendar.getInstance();

    }

    public static CalendarFragment newInstance(int year, int month){
        CalendarFragment calendarFragment = new CalendarFragment();
        Bundle args = new Bundle();
        args.putInt(ARD_YEAR, year);
        args.putInt(ARG_MONTH, month);
        calendarFragment.setArguments(args);
        return calendarFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calender, container, false);
        int year = getArguments().getInt(ARD_YEAR);
        int month = getArguments().getInt(ARG_MONTH);
        mClendar.set(Calendar.YEAR, year);
        mClendar.set(Calendar.MONTH, month);


        return v;
    }



}
