package com.example.calendarproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private final ArrayList<Event> event_ArrayList;
    private final OnItemListener1 onItemListener1;

    public EventAdapter(ArrayList<Event> event_ArrayList, OnItemListener1 onItemListener1) {
        this.event_ArrayList = event_ArrayList;
        this.onItemListener1 = onItemListener1;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.event_cell, parent, false);

        return new EventViewHolder(view, onItemListener1);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        String time = event_ArrayList.get(position).getHour()+":"+event_ArrayList.get(position).getMin();
        holder.eventTime_tv.setText(time);
        holder.eventTitle_tv.setText(event_ArrayList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return (event_ArrayList != null ? event_ArrayList.size() : 0);
    }

    public interface OnItemListener1{

        void onItemClick1(int position, String event_title);

    }




}
