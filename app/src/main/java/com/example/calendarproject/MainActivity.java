package com.example.calendarproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.time.LocalDate.now;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    public static LocalDate selectedDate;
    private Toolbar myToolbar;
    private FloatingActionButton floatingActionButton;
    public static ArrayList<Event> arrayList = new ArrayList<>();
    public ArrayList<LocalDate> dayArrayList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        arrayList = ReadEventsData(this);
        selectedDate = now();
        setMonthView();
        myToolbar = (Toolbar) findViewById(R.id.tb_id_main);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("캘린더");

        floatingActionButton = findViewById(R.id.fbn_main);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gomemo = new Intent(v.getContext(), memo_activity.class);
                startActivity(gomemo);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        setMonthView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SaveEventData(this, arrayList);
    }

    //main에 보이는 RecyclerView 셋팅하는 메소드
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        ArrayList<CalendarEvent> calendarArrayList = new ArrayList<>();
        ArrayList<String> titleArrayList = new ArrayList<>();
        dayArrayList.clear();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);


        //RecyclerView에 들어갈 ArrayList 생성
        for (int i=0; i<42; i++){
            if (!arrayList.isEmpty()&&dayArrayList.get(i)!=null){
                for (int j=0; j<arrayList.size();j++){
                    LocalDate date = arrayList.get(j).getTargetDateTime();
                    if (dayfromDate(date).equals(dayfromDate(dayArrayList.get(i)))){
                        titleArrayList.add(arrayList.get(j).getTitle());
                    }
                }
            }
            titleArrayList.add("");
            titleArrayList.add("");
            titleArrayList.add("");
            CalendarEvent calendarEvent = new CalendarEvent(daysInMonth.get(i), titleArrayList);
            calendarArrayList.add(calendarEvent);
            titleArrayList.clear();
        }

        CalendarAdapter calendarAdapter = new CalendarAdapter(calendarArrayList, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    //날짜 ArrayList 생성
    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date){
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i = 1; i <=42; i++){
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthArray.add("");
                dayArrayList.add(null);
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
                dayArrayList.add(LocalDate.of(selectedDate.getYear(),selectedDate.getMonthValue(),(i-dayOfWeek)));
            }
        }
        return daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MMMM");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String dayfromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy M d");
        return date.format(formatter);
    }

    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }


    //이전달로 넘기는 메소드
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    //다음달로 넘기는 메소드
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    //RecyclerView item 클릭시 발생하는 이벤트
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, String dayText) {
        if (!dayText.equals("")) {
            Intent intent = new Intent(this, day_activity.class);
            intent.putExtra("dayText", dayText);
            startActivity(intent);
        } else { return; }
    }

    //Toolbar 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Toolbar item 클릭시 이벤트
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_search:
                break;
            case R.id.menu_information:
                Intent toinfo = new Intent(this, information_activity.class);
                startActivity(toinfo);
                break;
            case R.id.menu_reset:
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                ad.setMessage("정말 초기화 하시겠습니까?");
                ad.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrayList.clear();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        dialog.dismiss(); }
                });
                ad.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                break; }
        return super.onOptionsItemSelected(item);
    }

    private void SaveEventData(Context context, ArrayList<Event> events){
        SharedPreferences preferences = context.getSharedPreferences("file",0);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(events);
        editor.putString("Events", json);
        editor.commit();
    }

    private ArrayList<Event> ReadEventsData(Context context){
        ArrayList<Event> events = new ArrayList<Event>();
        SharedPreferences sharedPreferences = context.getSharedPreferences("file" , 0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("Events","");
        if (!json.isEmpty()){
            Type type = new TypeToken<ArrayList<Event>>(){
            }.getType();
            events = gson.fromJson(json, type);
        }
        return events;
    }
}