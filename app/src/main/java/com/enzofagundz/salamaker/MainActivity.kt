package com.enzofagundz.salamaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enzofagundz.salamaker.DBHelper.SalaMakerDB

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var db: SalaMakerDB = SalaMakerDB(this)
    }
}