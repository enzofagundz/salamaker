package com.enzofagundz.salamaker.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.enzofagundz.salamaker.model.Reservation
import com.enzofagundz.salamaker.model.User

class SalaMakerDB(context: Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {
    override fun onCreate(db: SQLiteDatabase?) {
        // criar as tabelas do banco de dados user e reservation
        val createUserTable = "CREATE TABLE user (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email VARCHAR(50)," +
                "password VARCHAR(50)," +
                "firstName VARCHAR(50)," +
                "lastName VARCHAR(50)," +
                "username VARCHAR(50)" +
                ");"
        
        val createReservationTable = "CREATE TABLE reservation (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date DATE," +
                "startTime VARCHAR(50)," +
                "endTime VARCHAR(50)," +
                "user_id INTEGER," +
                "FOREIGN KEY(user_id) REFERENCES user(id)" +
                ");"

        // executar as queries
        db?.execSQL(createUserTable)
        db?.execSQL(createReservationTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropUserTable = "DROP TABLE IF EXISTS user"
        val dropReservationTable = "DROP TABLE IF EXISTS reservation"
        db?.execSQL(dropUserTable)
        db?.execSQL(dropReservationTable)
        onCreate(db)
    }

    companion object {
        private val dbVersion = 1
        private val dbName = "SalaMakerDB"
    }

    fun insertUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("email", user.email)
        values.put("password", user.password)
        values.put("firstName", user.firstName)
        values.put("lastName", user.lastName)
        values.put("username", user.username)
        db.insert("user", null, values)
        db.close()
    }

    // método verifyUser que recebe um objeto User e verifica se ele já existe no banco de dados
    fun verifyUser(user: User): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM user WHERE email = '${user.email}' AND password = '${user.password}'"
        val cursor = db.rawQuery(query, null)
        // se existir, retornar true, se não existir, retornar false
        if (cursor.moveToFirst()) {
            return true
        }
        return false
    }

    // método getUser que recebe um login e uma senha e retorna um objeto User
    fun getUser(login: String, senha: String): User? {
        val db = this.readableDatabase
        val query = "SELECT * FROM user WHERE email = '$login' AND password = '$senha'"
        val cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val email = cursor.getString(cursor.getColumnIndex("email"))
            val password = cursor.getString(cursor.getColumnIndex("password"))
            val firstName = cursor.getString(cursor.getColumnIndex("firstName"))
            val lastName = cursor.getString(cursor.getColumnIndex("lastName"))
            val username = cursor.getString(cursor.getColumnIndex("username"))
            val user = User(id, email, password, firstName, lastName, username)
            return user
        }
        return null
    }

    // método insertReservation que recebe um objeto Reservation e insere no banco de dados
    fun insertReservation(reservation: Reservation) {
        val db = this.writableDatabase
        val values = ContentValues()

        // converter a data para o formato yyyy-MM-dd
        val date = reservation.date.split("/")
        val newDate = "${date[2]}-${date[1]}-${date[0]}"
        values.put("date", newDate)
        
        // converter a hora de início para o formato HH:mm
        val startTime = reservation.startTime.split(":")
        val newStartTime = "${startTime[0]}:${startTime[1]}"
        values.put("startTime", newStartTime)

        // converter a hora de término para o formato HH:mm
        val endTime = reservation.endTime.split(":")
        val newEndTime = "${endTime[0]}:${endTime[1]}"
        values.put("endTime", newEndTime)
        values.put("user_id", reservation.userId)

        db.insert("reservation", null, values)
        db.close()
    }
}