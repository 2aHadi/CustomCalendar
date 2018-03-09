package com.example.haadee.customcalendar;

import android.app.DatePickerDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    @BindView(R.id.calenderPager)
    ViewPager mViewPager;
    @BindView(R.id.txtYearNMonth)
    TextView txtYearMonth;
    @BindView(R.id.btnArrowPrev)
    ImageButton btnArrowPrev;
    @BindView(R.id.btnArrowNext)
    ImageButton btnArrowNext;

    private MainViewModel mMainViewModel;
    private Calendar mCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mCalendar = mMainViewModel.mCalendar;
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));

        txtYearMonth.setOnClickListener(view -> datePickerDialog.show());
        mMainViewModel.liveDataCalendar.observe(this, calendar -> {
            mCalendar = calendar;
            mViewPager.getAdapter().notifyDataSetChanged();

        });

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
       mMainViewModel.setDate(i, i1, i2);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            int year = mCalendar.get(Calendar.YEAR);
            return CalendarFragment.newInstance(year, position);
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

}
