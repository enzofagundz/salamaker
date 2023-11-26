package com.enzofagundz.salamaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.enzofagundz.salamaker.DBHelper.SalaMakerDB
import com.enzofagundz.salamaker.model.User

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // pegar os campos:
        // username, firstName, lastName, email, password

        // criar um objeto User com os dados dos campos
        // inserir o objeto User no banco de dados

        // abrir a tela de login
        
        val btnCadastro = findViewById<Button>(R.id.btnActivityCadastro)

        btnCadastro.setOnClickListener {
            val txtUsername = findViewById<EditText>(R.id.username)
            val txtFirstName = findViewById<EditText>(R.id.firstname)
            val txtLastName = findViewById<EditText>(R.id.lastname)
            val txtEmail = findViewById<EditText>(R.id.email)
            val txtPassword = findViewById<EditText>(R.id.password)

            val username = txtUsername.text.toString()
            val firstName = txtFirstName.text.toString()
            val lastName = txtLastName.text.toString()
            val email = txtEmail.text.toString()
            val password = txtPassword.text.toString()

            val db = SalaMakerDB(this)
            val user = User(username, firstName, lastName, email, password)
            
            // verificar se o usuário já existe
            // se não existir, inserir no banco de dados

            var userJaCadastrado: Boolean = db.verifyUser(user)

            if (userJaCadastrado) {
                // mostrar mensagem de erro
                Toast.makeText(this, "Usuário inválido", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            
            db.insertUser(user)

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}