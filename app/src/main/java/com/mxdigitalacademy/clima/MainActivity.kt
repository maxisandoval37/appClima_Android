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

    private fun solicitudHTTPVolley(contextActivity: AppCompatActivity, url: String, ubicacion: String) {
        val colaDeSolicitudes = Volley.newRequestQueue(contextActivity)
        val direccion = url.replace("LUGAR", ubicacion)

        val solicitud = StringRequest(Request.Method.GET, direccion, Response.Listener<String> { response ->
            datosJSON(response)

        }, Response.ErrorListener { error ->
            Toast.makeText(contextActivity, "$error (Posible ubicación Inexistente)", Toast.LENGTH_SHORT).show()
        })

        colaDeSolicitudes.add(solicitud)
    }

    private fun datosJSON(respuesta: String) {
        val jsonMapeado = JSONObject(respuesta)

        val nombre = jsonMapeado.getString("name")
        val temperatura = jsonMapeado.getJSONObject("main").getString("temp")
        val descripcion = jsonMapeado.getJSONArray("weather").getJSONObject(0).getString("description")

        this.objRet = Ciudad(nombre, temperatura, descripcion)
        setearInfoElementosVisuales(this.objRet?.getNombre(),this.objRet?.getTemp(),this.objRet?.getDescripcion())
        setearImagenDescripcion(this.objRet?.getDescripcion().toString())
    }

    private fun setearInfoElementosVisuales(textoNombre: String?, textoTemp: String?, textoDescrip: String?){
        tvUbicacion.text = textoNombre
        tvTemperatura.text = textoTemp+"º"
        tvEstadoClima.text = textoDescrip
    }

    private fun setearImagenDescripcion(descripcion:String) {
        when (descripcion) {
            "cielo limpio" -> imgDescripcion.setImageResource(R.drawable.soleado)
            "nubes" -> imgDescripcion.setImageResource(R.drawable.nublado)
            "pocas nubes" -> imgDescripcion.setImageResource(R.drawable.solmoderado)
            "tormenta, lluvia, aguacero" -> imgDescripcion.setImageResource(R.drawable.lluvias)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ubicacion = intent.getStringExtra("com.mxdigitalacademy.clima.ciudad.LUGAR").toString()

        if (Red.verficarConexionInternet(this)){

            solicitudHTTPVolley(
                this,
                "https://api.openweathermap.org/data/2.5/weather?q=LUGAR,Arg&appid=b88ca6348bffab22427dff7f05986265&lang=es&units=metric",
                ubicacion)
        }
        else
            Toast.makeText(this,"No hay conexion a Internet",Toast.LENGTH_SHORT).show()
    }

}