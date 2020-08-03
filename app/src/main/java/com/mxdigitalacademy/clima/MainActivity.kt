package com.mxdigitalacademy.clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ubicacion = intent.getStringExtra("com.mxdigitalacademy.clima.ciudad.LUGAR").toString()

        if (Red.verficarConexionInternet(this)){
            Red.solicitudHTTPVolley(
                this,
                "https://api.openweathermap.org/data/2.5/weather?q=LUGAR,Arg&appid=b88ca6348bffab22427dff7f05986265&lang=es&units=metric",
                ubicacion)

            var ciudad:Ciudad? = Red.obtenerCiudad()
            ciudad?.mostrarDatos() //la 1ra vez llega el obj nulo

        }
        else
            Toast.makeText(this,"No hay conexion a Internet",Toast.LENGTH_SHORT).show()



    }


}