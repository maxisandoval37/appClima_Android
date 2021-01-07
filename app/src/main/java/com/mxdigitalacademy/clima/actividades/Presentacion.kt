package com.mxdigitalacademy.clima.actividades

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.mxdigitalacademy.clima.R

class Presentacion : AppCompatActivity() {

    fun accionBotonContinuar(){
        val boton = findViewById<Button>(R.id.btContinuar)
        val direccion = "com.mxdigitalacademy.clima.ciudad.LUGAR"

        boton.setOnClickListener(View.OnClickListener {
            val intentMainClass = Intent(this, MainActivity::class.java)
            val etUbicacionTexto = findViewById<EditText>(R.id.etUbicacion).text.toString()

            if (etUbicacionTexto.isEmpty())
                Toast.makeText(this,"Ingrese una ubicación para continuar",Toast.LENGTH_SHORT).show()
            else{
                intentMainClass.putExtra(direccion,etUbicacionTexto)
                startActivity(intentMainClass)
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentacion)

        accionBotonContinuar()
    }
}