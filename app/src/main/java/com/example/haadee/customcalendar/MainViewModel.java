package com.example.haadee.customcalendar;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.Calendar;

/**
 * Created by Haadee on 3/6/2018.
 */

public class MainViewModel extends ViewModel {
    public Calendar mCalendar;
    public MutableLiveData<Calendar> liveDataCalendar = new MutableLiveData<>();

    public MainViewModel(){
        mCalendar = Calendar.getInstance();
    }


    public void setDate(int year, int month, int day){
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, day);

    }

}
