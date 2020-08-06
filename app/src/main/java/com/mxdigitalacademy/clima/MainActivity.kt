package com.mxdigitalacademy.clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private var objRet: Ciudad? = null

    private fun solicitudHTTPVolley(contextActivity: AppCompatActivity, url: String) {
        val colaDeSolicitudes = Volley.newRequestQueue(contextActivity)

        val solicitud = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
            datosJSON(response)

        }, Response.ErrorListener { error ->
            Toast.makeText(contextActivity, "Posible ubicación Inexistente", Toast.LENGTH_SHORT).show()
        })

        colaDeSolicitudes.add(solicitud)
    }

    private fun datosJSON(respuesta: String) {
        val jsonMapeado = JSONObject(respuesta)

        val nombre = jsonMapeado.getString("name")
        val temperatura = jsonMapeado.getJSONObject("main").getString("temp")
        val descripcion = jsonMapeado.getJSONArray("weather").getJSONObject(0).getString("description")

        val descripIcon = jsonMapeado.getJSONArray("weather").getJSONObject(0).getString("icon")

        this.objRet = Ciudad(nombre, temperatura, descripcion)
        setearInfoElementosVisuales(this.objRet?.getNombre(),this.objRet?.getTemp(),this.objRet?.getDescripcion())
        setearImagenDescripcion(descripIcon)
    }

    private fun setearInfoElementosVisuales(textoNombre: String?, textoTemp: String?, textoDescrip: String?){
        tvUbicacion.text = textoNombre
        tvTemperatura.text = textoTemp+"º"
        tvEstadoClima.text = textoDescrip
    }

    private fun setearImagenDescripcion(descripcion:String) {//no usamos las de la api, por cuestiones de diseño
        when (descripcion) {
            "01d","01n" -> imgDescripcion.setImageResource(R.drawable.soleado)
            "03d","04d","50d","03n","04n","50n" -> imgDescripcion.setImageResource(R.drawable.nublado)
            "02d","02n" -> imgDescripcion.setImageResource(R.drawable.solmoderado)
            "09d","10d","11d","13d","09n","10n","11n","13n" -> imgDescripcion.setImageResource(R.drawable.lluvias)
        }
    }
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ubicacion = intent.getStringExtra("com.mxdigitalacademy.clima.ciudad.LUGAR").toString()
        val urlApi = "https://api.openweathermap.org/data/2.5/weather?q=$ubicacion&appid=b88ca6348bffab22427dff7f05986265&lang=es&units=metric"
        println(urlApi)

        if (Red.verficarConexionInternet(this))
            solicitudHTTPVolley(this, urlApi)

        else
            Toast.makeText(this,"No hay conexion a Internet",Toast.LENGTH_SHORT).show()
    }

}