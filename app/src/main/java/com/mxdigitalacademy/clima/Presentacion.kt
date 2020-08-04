package com.mxdigitalacademy.clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_presentacion.*

class Presentacion : AppCompatActivity() {

    fun accionBotonContinuar(){
        val boton = findViewById<Button>(R.id.btContinuar)
        val direccion = "com.mxdigitalacademy.clima.ciudad.LUGAR"

        boton.setOnClickListener(View.OnClickListener {
            val IntentMainClass = Intent(this,MainActivity::class.java) //a donde, nos redirigimos
            val etUbicacionTexto = findViewById<EditText>(R.id.etUbicacion).text.toString()

            IntentMainClass.putExtra(direccion,etUbicacionTexto)

            startActivity(IntentMainClass)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentacion)

        accionBotonContinuar()
    }
}