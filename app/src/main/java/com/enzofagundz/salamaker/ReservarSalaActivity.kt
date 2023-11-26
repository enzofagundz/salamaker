package com.enzofagundz.salamaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.enzofagundz.salamaker.DBHelper.SalaMakerDB
import com.enzofagundz.salamaker.model.Reservation

class ReservarSalaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservar_sala)

        val btnReservar = findViewById<Button>(R.id.btn_cadastrar_sala)
        btnReservar.setOnClickListener {
            val dataReserva = findViewById<EditText>(R.id.data_reserva_edit_text)
            val horaInicio = findViewById<EditText>(R.id.hora_inicio_edit_text)
            val horaFim = findViewById<EditText>(R.id.hora_termino_edit_text)

            val reserva = Reservation()
            reserva.date = dataReserva.text.toString()
            reserva.startTime = horaInicio.text.toString()
            reserva.endTime = horaFim.text.toString()
            reserva.userId = 2

            if (reserva.date == "" || reserva.startTime == "" || reserva.endTime == "") {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val db = SalaMakerDB(this)
            db.insertReservation(reserva)

            Toast.makeText(this, "Reserva cadastrada com sucesso", Toast.LENGTH_LONG).show()

            // limpar os campos
            dataReserva.setText("")
            horaInicio.setText("")
            horaFim.setText("")

            // abrir a tela principal
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }


}