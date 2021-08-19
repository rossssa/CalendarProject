package com.example.calendarproject;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView eventTime_tv, eventTitle_tv;
    private final EventAdapter.OnItemListener1 onItemListener1;
    public EventViewHolder(@NonNull View itemView, EventAdapter.OnItemListener1 onItemListener1) {
        super(itemView);
        eventTitle_tv = itemView.findViewById(R.id.eventTitle_tv);
        eventTime_tv = itemView.findViewById(R.id.eventTime_tv);
        this.onItemListener1 = onItemListener1;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener1.onItemClick1(getAdapterPosition(), (String) eventTitle_tv.getText());
    }
}
