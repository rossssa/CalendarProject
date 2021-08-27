package com.example.calendarproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {

    private final ArrayList<CalendarEvent> daysOfMonth;
    private final OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<CalendarEvent> daysOfMonth, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calender_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight()*0.154);
        return new CalendarViewHolder(view, onItemListener);

    }
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        holder.dayOfMonth.setText(daysOfMonth.get(position).getDay());
        String title1 = daysOfMonth.get(position).getTitleArray().get(0);
        String title2 = daysOfMonth.get(position).getTitleArray().get(1);
        String title3 = daysOfMonth.get(position).getTitleArray().get(2);
        if (!title1.equals("")){
            holder.event_tv1.setVisibility(View.VISIBLE);
            holder.event_tv1.setText(title1);
        }else{
            holder.event_tv1.setVisibility(View.INVISIBLE);
        }
        if (!title2.equals("")){
            holder.event_tv2.setVisibility(View.VISIBLE);
            holder.event_tv2.setText(title2);
        }else{
            holder.event_tv2.setVisibility(View.INVISIBLE);
        }
        if (!title3.equals("")){
            holder.event_tv3.setVisibility(View.VISIBLE);
            holder.event_tv3.setText(title3);
        }else{
            holder.event_tv3.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnItemListener{

        void onItemClick(int position, String dayText);

    }
}
