package com.example.a034rutinewidget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView textView;

    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout=findViewById(R.id.layout);

        DatePicker datePicker= new DatePicker(this);
        datePicker.setId(View.generateViewId());
        constraintLayout.addView(datePicker);


        ConstraintSet constraintSet= new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.centerHorizontally(datePicker.getId(),ConstraintSet.PARENT_ID);
        constraintSet.centerHorizontally(datePicker.getId(),ConstraintSet.PARENT_ID);
        constraintSet.applyTo(constraintLayout);


        //ConstraintLayout.LayoutParams  layoutParams= (ConstraintLayout.LayoutParams) constraintLayout.getLayoutParams();
        //layoutParams.topToBottom = R.id.texto;
        //datePicker.setLayoutParams(layoutParams);


//    Check parametros del date picker antes
    //datePicker.setLayoutParams(new DatePicker.LayoutParams(DatePicker.
    //LayoutParams.WRAP_CONTENT,
    //DatePicker.LayoutParams.WRAP_CONTENT));
    }
}