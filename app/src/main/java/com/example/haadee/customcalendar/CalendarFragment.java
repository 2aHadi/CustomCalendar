package com.example.haadee.customcalendar;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haadee.customcalendar.pojo.NoOfEventsPerDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {
    public static String ARG_MONTH = "month";
    public static String ARD_YEAR = "year";


    private MainViewModel mMainViewModel;
    private Calendar mCalendar;

    @BindView(R.id.rvCalendarDates)
    RecyclerView rvCalendarDates;

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mCalendar = Calendar.getInstance();

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
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);



        rvCalendarDates.setLayoutManager(new GridLayoutManager(getActivity(), 7));

        return v;
    }


    class RvCalendarGridAdapter extends RecyclerView.Adapter<RvCalendarGridAdapter.RvViewHolder>{

        private Context context;
        private List<String> dayList;
        private List<Date> mDateList = new ArrayList<>();

        RvCalendarGridAdapter(Context context, List<String> list){
            this.context = context;
            this.dayList = list;
        }

        @Override
        public RvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.recyclerview_calender_day, parent, false);
            return new RvViewHolder(v);
        }

        @Override
        public void onBindViewHolder(RvViewHolder holder, final int position) {
            final Calendar calendar = mCalendar;
            String dateStr = dayList.get(position);
            holder.txtDayDate.setText(dateStr);

            if (!dateStr.equals("")){
                calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dateStr));
            }
            mDateList.add(calendar.getTime());

            List<NoOfEventsPerDate> noOfEventsPerDateList;
            if (mMainViewModel.mDatesWithEvents != null) {
                noOfEventsPerDateList = mMainViewModel.mDatesWithEvents;

                for (NoOfEventsPerDate noOfEventsPerDate: noOfEventsPerDateList){

                    if (checkDateMatch(calendar, noOfEventsPerDate)){
                        holder.txtEventCountBadge.setVisibility(View.VISIBLE);
                        holder.txtEventCountBadge.setText(String.valueOf(noOfEventsPerDate.getNoOfEvents()));
                    }else {

                    }

                }
            }

            if (!dateStr.equals("")) {
                if (holder.txtEventCountBadge.getVisibility() == View.VISIBLE){
                    holder.rlDayInMonth.setOnClickListener(view -> {
                        Date date = mDateList.get(position);
                    });
                }else {
                    holder.rlDayInMonth.setOnClickListener(view -> {
                        Date date = mDateList.get(position);
                        Toast.makeText(context, date.toString(), Toast.LENGTH_SHORT).show();
                    });
                }
            }

        }

        private boolean checkDateMatch(Calendar calendar, NoOfEventsPerDate noOfEventsPerDate) {

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            String dateFromEventStr = format.format(noOfEventsPerDate.getDate());
            String dateFromCalendarStr = format.format(calendar.getTime());

            return dateFromCalendarStr.equals(dateFromEventStr);
        }

        @Override
        public int getItemCount() {
            return dayList.size();
        }

        class RvViewHolder extends RecyclerView.ViewHolder{

            @BindView(R.id.txtDayDate)
            TextView txtDayDate;
            @BindView(R.id.txtEventCountBadge)
            TextView txtEventCountBadge;
            @BindView(R.id.rlDayInMonth)
            RelativeLayout rlDayInMonth;

            RvViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }



}
