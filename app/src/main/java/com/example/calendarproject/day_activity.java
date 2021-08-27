package com.example.calendarproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static com.example.calendarproject.MainActivity.arrayList;
import static com.example.calendarproject.MainActivity.selectedDate;

public class day_activity extends AppCompatActivity implements EventAdapter.OnItemListener1
{

    private TextView DayMonthYearTV;
    private String dayText;
    private FloatingActionButton floatingActionButton;
    private RecyclerView eventRecyclerView;
    private ArrayList<Event> event_ArrayList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_calender);
        DayMonthYearTV = findViewById(R.id.DayMonthYearTV);
        eventRecyclerView = findViewById(R.id.eventRecyclerView);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(eventRecyclerView.
                getContext(), new LinearLayoutManager(this).getOrientation());
        eventRecyclerView.addItemDecoration(dividerItemDecoration);

        Toolbar toolbar = findViewById(R.id.tb_id_day);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생성
        getSupportActionBar().setTitle("캘린더");
        toolbar.setTitleTextColor(Color.BLACK);

        floatingActionButton = findViewById(R.id.fbn_day);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gomemo = new Intent(v.getContext(), memo_activity.class);
                startActivity(gomemo);
            }
        });

        Intent intent = getIntent();
        dayText = intent.getStringExtra("dayText");

        setDayView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onRestart() {
        super.onRestart();
        setDayView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMMM");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setDayView(){
        DayMonthYearTV.setText(monthYearFromDate(selectedDate)+ " " + dayText);
        setEventView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setEventView(){

        event_ArrayList.clear();
        for (int i=0; i < arrayList.size(); i++){
            LocalDate event_date = arrayList.get(i).getTargetDateTime();
            String date = Integer.toString(event_date.getDayOfMonth());
            if (monthYearFromDate(event_date).equals(monthYearFromDate(selectedDate))&&dayText.equals(date)){
                event_ArrayList.add(arrayList.get(i));
            }
        }

        if (event_ArrayList.isEmpty()){
            eventRecyclerView.setVisibility(View.INVISIBLE);
        }else{
            eventRecyclerView.setVisibility(View.VISIBLE);
        }

        EventAdapter eventAdapter = new EventAdapter(event_ArrayList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        eventRecyclerView.setLayoutManager(layoutManager);
        eventRecyclerView.setAdapter(eventAdapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousDayAction(View view) {
        int dT = Integer.parseInt(dayText)-1;
        if (dT < 1) {
            selectedDate = selectedDate.minusMonths(1);
            YearMonth yearMonth = YearMonth.from(selectedDate);
            int daysInMonth = yearMonth.lengthOfMonth();
            dT = daysInMonth;
        }
        dayText = Integer.toString(dT);
        setDayView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextDayAction(View view) {
        int dT = Integer.parseInt(dayText)+1;
        YearMonth yearMonth = YearMonth.from(selectedDate);
        int daysInMonth = yearMonth.lengthOfMonth();
        if (dT > daysInMonth) {
            selectedDate = selectedDate.plusMonths(1);
            dT = 1;
        }
        dayText = Integer.toString(dT);
        setDayView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick1(int position, String event_title) {
        Intent intent = new Intent(this, memochange_activity.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }
}




