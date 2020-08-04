package com.mxdigitalacademy.clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

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

            val ciudad:Ciudad? = Red.obtenerCiudad()

            ciudad?.getterNombre()?.let { setearInfoElementosVisuales(it, ciudad.getterTemp(), ciudad.getterDescripcion()) }

        }
        else
            Toast.makeText(this,"No hay conexion a Internet",Toast.LENGTH_SHORT).show()

    }

    private fun setearInfoElementosVisuales(textoNombre:String,textoTemp:String,textoDescrip:String){
        tvUbicacion.text = textoNombre
        tvTemperatura.text = textoTemp
        tvEstadoClima.text = textoDescrip
    }

}