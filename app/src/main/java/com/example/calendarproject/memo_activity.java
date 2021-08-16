package com.example.calendarproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class memo_activity extends AppCompatActivity {

    private Toolbar myToolbar;
    EditText id_title, id_location, id_explain;
//    String shared = "file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_calender);

        Toolbar toolbar = findViewById(R.id.tb_id_memo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼, 디폴트로 true만 해도 백버튼이 생성
        getSupportActionBar().setTitle("새 이벤트");
        toolbar.setTitleTextColor(Color.BLACK);



        id_title = (EditText)findViewById(R.id.id_title);
        id_location = (EditText)findViewById(R.id.id_location);
        id_explain = (EditText)findViewById(R.id.id_explain);

//        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
//        String value = sharedPreferences.getString("title", "");
//        id_title.setText(value);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_memo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_save:{

                break;
            }
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        String value = id_title.getText().toString();
//        editor.putString("title", value);
//        editor.commit();
//    }
}


