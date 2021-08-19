package com.example.calendarproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.time.LocalDate;

import static com.example.calendarproject.MainActivity.arrayList;
import static com.example.calendarproject.MainActivity.selectedDate;


public class memochange_activity extends AppCompatActivity {

    private Toolbar myToolbar;
    EditText id_title, id_location, id_explain;
    private TextView date_tv, time_tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private int mYear, mMonth, mDayOfMonth, hour, min;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memochange_calender);

        Toolbar toolbar = findViewById(R.id.tb_id_change);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생성
        getSupportActionBar().setTitle("이벤트 수정");
        toolbar.setTitleTextColor(Color.BLACK);


        date_tv = findViewById(R.id.date_tv);
        time_tv = findViewById(R.id.time_tv);


        mYear = selectedDate.getYear();
        mMonth = selectedDate.getMonthValue();
        mDayOfMonth = selectedDate.getDayOfMonth();
        hour = 0;
        min = 0;

        id_title = (EditText)findViewById(R.id.id_title);
        id_location = (EditText)findViewById(R.id.id_location);
        id_explain = (EditText)findViewById(R.id.id_explain);


        date_tv.setText(mYear+"/"+mMonth+"/"+mDayOfMonth);
        date_tv.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(memochange_activity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                date_tv.setText(year+"/"+month+"/"+dayOfMonth);
                                mYear = year;
                                mMonth = month;
                                mDayOfMonth = dayOfMonth;
                            }
                        },
                        mYear,
                        mMonth,
                        mDayOfMonth);
                datePickerDialog.show();
            }
        });

        time_tv.setText(String.format("%02d:%02d",hour, min));
        time_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(memochange_activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time_tv.setText(String.format("%02d:%02d",hourOfDay, minute));
                        hour = hourOfDay;
                        min = minute;
                    }
                }, hour, min, false);
                timePickerDialog.show();
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_change, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_change: {
                    LocalDate targetDateTime = LocalDate.of(mYear, mMonth, mDayOfMonth);
                    String title = id_title.getText().toString();
                    String location = id_location.getText().toString();
                    String explain = id_explain.getText().toString();
                    String mhour = Integer.toString(hour);
                    String mMin = Integer.toString(min);
                    Event event = new Event(title, location, explain, targetDateTime, mhour, mMin);
                    arrayList.add(event);
                    finish();
                    break;
                }
            case R.id.menu_delete:{

            }
                case android.R.id.home: { //toolbar의 back키 눌렀을 때 동작
                    finish();
                    return true;
                }

            }
        return super.onOptionsItemSelected(item);
    }

}
