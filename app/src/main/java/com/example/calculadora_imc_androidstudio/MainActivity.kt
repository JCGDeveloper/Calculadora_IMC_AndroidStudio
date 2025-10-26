package com.example.calculadora_imc_androidstudio

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var cache : SharedPreferences
    private lateinit var imgLogo : ImageView
    private lateinit var etUser : TextView
    private lateinit var etPassword : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarDatos()

        var buttonLogin = findViewById<Button>(R.id.buttonLogin)
        buttonLogin.setOnClickListener {
            Login()
        }
    }

    private fun inicializarDatos(){
        cache = getSharedPreferences("Datos Cache",MODE_PRIVATE)
        imgLogo = findViewById<ImageView>(R.id.logo)
        etUser = findViewById<EditText>(R.id.etUser)
        etPassword = findViewById<EditText>(R.id.etPassword)

        imgLogo.setImageResource(R.drawable.komorebi)

    }

    private fun Login(){
        val img = R.drawable.komorebi
        val user = etUser.text.toString()
        val password = etPassword.text.toString()

        if (user.isEmpty()){
            etUser.error = "Username incorrect"
        }
        if (password.isEmpty()){
            etPassword.error = "Password incorrect"
        }

        if (user == "Admin" && password == "Admin"){
            val enviarDatos = cache.edit()
            enviarDatos.putString("Username",user)
            enviarDatos.putString("Password",password)
            enviarDatos.putInt("Logo",img)
            enviarDatos.apply()

            Toast.makeText(this,"Iniciando Sesion", Toast.LENGTH_SHORT).show()

            val calculadora = Intent(this, Calculadora::class.java)
            startActivity(calculadora)
            Toast.makeText(this, "Sesion iniciada", Toast.LENGTH_SHORT).show()
        }
    }
}