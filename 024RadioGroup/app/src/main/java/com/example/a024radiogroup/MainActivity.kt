package com.example.a024radiogroup

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {
    lateinit var radioGroup: RadioGroup

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        radioGroup = findViewById(R.id.pica)
        this.radioGroup.setOnCheckedChangeListener(this)

    }

    override fun onCheckedChanged(radioGroup: RadioGroup, i: Int) {
        Toast.makeText(this, "Has picado $i", Toast.LENGTH_LONG).show()
        val t = findViewById<TextView>(R.id.respuesta)
        when (i) {
            1 -> {
                findViewById<View>(R.id.radioButton)
                t.text = "El albacete mola"
            }

            2 -> {
                findViewById<View>(R.id.radioButton2)
                t.text = "El albacete mola"
            }

            3 -> {
                findViewById<View>(R.id.radioButton)
                t.text = "El albacete mola"
            }

            4 -> {
                findViewById<View>(R.id.radioButton)
                t.text = "El albacete mola"
            }
        }
    }
}