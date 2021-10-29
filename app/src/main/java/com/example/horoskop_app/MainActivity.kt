package com.example.horoskop_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.get
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    //Lager en global variabel for request-kø


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val horoskop_knapp = findViewById<Button>(R.id.horoskop_knapp)


        val spinnerSign: Spinner = findViewById(R.id.spinner_sign)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.signs_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerSign.adapter = adapter
        }

        val spinnerDay: Spinner = findViewById(R.id.spinner_day)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.day_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerDay.adapter = adapter
        }
        horoskop_knapp.setOnClickListener {
            val sign = spinnerSign.getSelectedItem().toString()
            val day = spinnerDay.getSelectedItem().toString()
            val intent = Intent(this, horoskopActivity::class.java)
            intent.putExtra("url",createURL(sign,day))
            startActivity(intent)
        }

    }
    private fun createURL(sign : String, date: String) : String {
        val url = "https://aztro.sameerkumar.website/?sign=$sign&day=$date"
        return url
    }


}





