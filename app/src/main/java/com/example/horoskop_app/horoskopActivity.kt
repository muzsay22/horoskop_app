package com.example.horoskop_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class horoskopActivity : AppCompatActivity() {
    var requestQueue: RequestQueue? = null
    var date : TextView? = null
    var todayDate : TextView? = null
    var description : TextView? = null
    var compability : TextView? = null
    var number : TextView? = null
    var time : TextView? = null
    var color : TextView? = null
    var dateRange : TextView? = null
    var mood : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horoskop)
        date = findViewById(R.id.date)
        todayDate = findViewById(R.id.todayDate)
        description = findViewById(R.id.description)
        compability = findViewById(R.id.compability)
        number = findViewById(R.id.number)
        time = findViewById(R.id.time)
        color = findViewById(R.id.color)
        dateRange = findViewById(R.id.dateRange)
        mood = findViewById(R.id.mood)


        val textView = findViewById<TextView>(R.id.desc)
        APIkall(textView)

    }
    private fun APIkall(view: TextView) {
        requestQueue = Volley.newRequestQueue(this)
        val url = intent.getStringExtra("url")
        //Lager en request
        val request = StringRequest(
            Request.Method.POST, url,
            { response ->
                //Gjør noe med svaret
                formatResponse(response)
            },
            { error ->
                //Håndterer eventuelle feil
                view.text = "Kunne ikke laste horoskop!!"
            }
        )

        request.retryPolicy =
            DefaultRetryPolicy( 10*100, 0 , DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
        //Gir requesten en tag
        request.tag = "horoskop"
        //Legger til requesten i køen
        requestQueue?.add(request)
    }



    override fun onStop() {
        super.onStop()
        requestQueue?.cancelAll("horoskop")
    }

    private fun formatResponse(resp: String) {
        resp.drop(0)
        resp.dropLast(1)
        val liste = resp.split("\"")
        dateRange!!.text = liste[3]
        todayDate!!.text = liste[7]
        description!!.text = liste[11]
        compability!!.text = liste[15]
        number!!.text = liste[19]
        time!!.text = liste[23]
        color!!.text = liste[27]
        mood!!.text = liste[31]

    }
}