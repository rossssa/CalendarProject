package com.example.calendarproject;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import static com.example.calendarproject.MainActivity.selectedDate;

public class day_activity extends AppCompatActivity {

    private TextView DayMonthYearTV;
    private String dayText;
    private Button btn_plus;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_calender);
        DayMonthYearTV = findViewById(R.id.DayMonthYearTV);

        btn_plus = (Button)findViewById(R.id.btn_plus);

        btn_plus.setOnClickListener(new View.OnClickListener() {
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
    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMMM");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setDayView(){
        DayMonthYearTV.setText(monthYearFromDate(selectedDate)+ " " + dayText);
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
}
