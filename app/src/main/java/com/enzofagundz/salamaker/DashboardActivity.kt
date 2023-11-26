package com.enzofagundz.salamaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val reservarSala = findViewById<CardView>(R.id.reservarSala)
        reservarSala.setOnClickListener {
            val intent = Intent(this, ReservarSalaActivity::class.java)
            startActivity(intent)
        }
    }
}