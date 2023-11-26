package com.enzofagundz.salamaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.enzofagundz.salamaker.DBHelper.SalaMakerDB

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // pegar os campos de login e senha
        // verificar se o usuário existe no banco de dados
        // se existir, verificar se a senha está correta
        // se estiver, abrir a tela principal
        // se não estiver, mostrar mensagem de erro
        // se não existir, mostrar mensagem de erro

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val txtLogin = findViewById<EditText>(R.id.txtLogin)
            val txtSenha = findViewById<EditText>(R.id.txtSenha)
            val login = txtLogin.text.toString()
            val senha = txtSenha.text.toString()
            val db = SalaMakerDB(this)
            val usuario = db.getUser(login, senha)

            // se o usuário existir e a senha estiver correta, o objeto usuario não será nulo
            if (usuario != null) {
                // abrir a tela principal
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            } else {
                // mostrar mensagem de erro
                Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_LONG).show()
            }
        }
    }
}