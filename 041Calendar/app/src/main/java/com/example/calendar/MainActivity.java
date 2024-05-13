package com.example.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements CalendarView.OnDateChangeListener{

    CalendarView calendarView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        button.setOnClickListener(v -> OnClickListener());
        calendarView=findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> onSelectedDayChange(view, year, month, dayOfMonth));
    }

    private void OnClickListener() {
        EditText editText;
        editText= findViewById(R.id.data);
        String data= editText.getText().toString();

        String []partes= data.split("/");

        int day = Integer.parseInt(partes[2]);
        int month= Integer.parseInt(partes[1]);
        int year = Integer.parseInt(partes[0]);

        Calendar calendar= Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.DAY_OF_MONTH,day);

        long militime= calendar.getTimeInMillis();
        calendarView.setDate(militime,true,true);



    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        TextView textView;
        textView=findViewById(R.id.textView2);
        String fecha = dayOfMonth +" - "+(month+1)+" - "+year;
        textView.setText(fecha);

    }
}