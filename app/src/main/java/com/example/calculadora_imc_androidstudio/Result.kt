package com.example.calculadora_imc_androidstudio

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Result : AppCompatActivity() {

    private lateinit var resultadoIMC: TextView
    private lateinit var categoriaIMC: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inicializarVariables()
        recuperarDatos()
    }

    private fun inicializarVariables() {
        resultadoIMC = findViewById(R.id.resultadoImc)
        categoriaIMC = findViewById(R.id.Categoria)
    }

    private fun recuperarDatos() {
        val imc = intent.getDoubleExtra("IMC", 0.0)
        resultadoIMC.text = String.format("%.2f", imc)
        mostrarCategoria(imc)
    }

    private fun mostrarCategoria(imc: Double) {
        val categoria = when {
            imc < 18.5 -> "Bajo peso"
            imc < 25 -> "Normal"
            imc < 30 -> "Sobrepeso"
            else -> "Obesidad"
        }
        categoriaIMC.text = categoria
    }
}
