package com.example.calculadora_imc_androidstudio

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Calculadora : AppCompatActivity() {
    private var imc = 0.0
    private lateinit var  kilograms : EditText
    private lateinit var measure : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculadora)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Inicializar variables
        inicializeVariable()

        var buttonSiguiente = findViewById<Button>(R.id.buttonNextWindow)
        buttonSiguiente.setOnClickListener {
            calculateImc()
        }
    }

    //Inicializar Variables
    private fun inicializeVariable(){
            kilograms = findViewById<EditText>(R.id.kilograms)
            measure = findViewById<EditText>(R.id.measure)
    }

    private fun calculateImc() {
        val pesoText = kilograms.text.toString()
        val medidaText = measure.text.toString()

        if (pesoText.isNotEmpty() && medidaText.isNotEmpty()) {
            val kg = pesoText.toDouble()
            val medidaCm = medidaText.toDouble()
            val alturaMetros = medidaCm / 100

            imc = kg / (alturaMetros * alturaMetros)

            val resultWindow = Intent(this, Result::class.java)
            resultWindow.putExtra("IMC", imc)
            Toast.makeText(this, "Viendo Resultados", Toast.LENGTH_SHORT).show()
            startActivity(resultWindow)
        } else {
            Toast.makeText(this, "Por favor rellena los datos", Toast.LENGTH_SHORT).show()
        }
    }


}