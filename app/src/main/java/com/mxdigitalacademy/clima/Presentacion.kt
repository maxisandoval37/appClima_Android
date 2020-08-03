package com.mxdigitalacademy.clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Presentacion : AppCompatActivity() {

    fun accionBotonContinuar(){
        val boton = findViewById<Button>(R.id.btContinuar)
        val direccion = "com.mxdigitalacademy.clima.ciudad.LUGAR"

        boton.setOnClickListener(View.OnClickListener {
            val IntentMainClass = Intent(this,MainActivity::class.java) //a donde, nos redirigimos

            IntentMainClass.putExtra(direccion,"Buenos Aires")

            startActivity(IntentMainClass)

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentacion)

        accionBotonContinuar()
    }
}