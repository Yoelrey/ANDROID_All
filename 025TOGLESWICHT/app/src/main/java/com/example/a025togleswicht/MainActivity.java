package com.example.a025togleswicht;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements ToggleButton.OnCheckedChangeListener{


    ToggleButton toggleButton;
    Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toggleButton=findViewById(R.id.toggleButton);
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) ->onCheckedChanged(buttonView,isChecked));
        aSwitch= findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener((button,checked)-> manolito((Switch) button,checked));
    }

    public void manolito(Switch butSwitch,boolean checked){
        ImageView img= findViewById(R.id.imageView);

        if (checked)
            img.setImageResource(R.drawable.avatar_3);
        else
            img.setImageResource(R.drawable.avatar_4);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        ImageView img= findViewById(R.id.imageView);

        if (b)
            img.setImageResource(R.drawable.avatar_2);
                else
                    img.setImageResource(R.drawable.avatar_5);

    }
}