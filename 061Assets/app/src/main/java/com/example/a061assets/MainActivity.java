package com.example.a061assets;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    EditText editTextText;
    Button button;

    Button button2;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(view -> openAsset(view));
        button2 = findViewById(R.id.imagen);
        image = findViewById(R.id.imagen);
        button2.setOnClickListener(view -> {
            try {
                loadImageFromAsset(view);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private String getText(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        try {;
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String newLine = null;
                while ((newLine = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(newLine+"\n");
                }
                inputStream.close();
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    public void openAsset(View view){

        editTextText= findViewById(R.id.editTextText);
        try{
            editTextText.setText(getText(this.getAssets().open("mitexto.txt")));
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public void loadImageFromAsset(View view) throws IOException {
        InputStream inputStream = this.getAssets().open("29032008022.jpg");

        Drawable d= Drawable.createFromStream(inputStream, null);

        image.setImageDrawable(d);
    }
}