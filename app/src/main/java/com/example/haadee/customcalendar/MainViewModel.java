package com.example.haadee.customcalendar;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.haadee.customcalendar.pojo.NoOfEventsPerDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Haadee on 3/6/2018.
 */

public class MainViewModel extends ViewModel {
    public Calendar mCalendar;
    public MutableLiveData<Calendar> liveDataCalendar = new MutableLiveData<>();
    public List<NoOfEventsPerDate> mDatesWithEvents;

    public MainViewModel(){
        mCalendar = Calendar.getInstance();
    }

    void setDate(int year, int month, int day){
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, day);
    }

    public List<String> createMonthDates(int noOfDays) {
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfMonth = mCalendar.get(Calendar.DAY_OF_WEEK);
        int startPos = getMonthStartPos(firstDayOfMonth);
        List<String> list = new ArrayList<>();
        int count = 0;
        for (int i = 1-startPos; i <= 42-startPos; i++){

            if (i<=0){
                list.add("");
            }else if (i > noOfDays){
                list.add("");
            }else if (count >= 42){
                Log.e("Dates Filled>>",""+i);
            }else {
                list.add(i+"");
            }

            count++;

        }
        return list;
    }

    private int getMonthStartPos(int dayOfWeek){
        int positionVal;
        if (dayOfWeek == 1){
            positionVal = 6;
        }else{
            positionVal = dayOfWeek - 2;
        }

        return positionVal;
    }

}
