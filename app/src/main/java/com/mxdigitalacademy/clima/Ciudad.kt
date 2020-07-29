package com.mxdigitalacademy.clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Ciudad : AppCompatActivity() {

    fun accionBotonContinuar(){
        val boton = findViewById<Button>(R.id.btContinuar)

        boton.setOnClickListener(View.OnClickListener {
            val IntentMainClass = Intent(this,MainActivity::class.java) //a donde, nos redirigimos
            startActivity(IntentMainClass)

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuidad)

        accionBotonContinuar()
    }
}