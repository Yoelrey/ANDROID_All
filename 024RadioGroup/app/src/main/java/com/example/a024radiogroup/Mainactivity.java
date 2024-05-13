package com.example.a024radiogroup;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Mainactivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    RadioGroup radioGroup;
    CheckBox checkBox;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        radioGroup =findViewById(R.id.pica);
        radioGroup.setOnCheckedChangeListener(((group, checkedId) -> onCheckedChanged(group,checkedId)));
        checkBox=findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(((buttonView, isChecked) -> onCheckedChanged(buttonView,isChecked)));
    }

    private void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        TextView t = findViewById(R.id.respuesta);
    if(isChecked)
        t.setText("\nTe gusta el furbo");
    else
        t.setText("\nNo te gusta el furbo");


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //Toast.makeText(this,"has picado"+checkedId,Toast.LENGTH_LONG).show();
        TextView text= findViewById(R.id.respuesta);
        if (R.id.radioButton==checkedId){
            text.setText("Viva cristo");
        }else if (R.id.radioButton==checkedId){
            text.setText("Viva cristo");

        } else if (R.id.radioButton==checkedId) {
            text.setText("Viva cristo");

        }else {
            text.setText("Viva cristo");

        }

    }
}
