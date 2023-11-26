package com.enzofagundz.salamaker
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.enzofagundz.salamaker.DBHelper.SalaMakerDB

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var db: SalaMakerDB = SalaMakerDB(this)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnCadastro = findViewById<Button>(R.id.btnCadastro)

        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }

}