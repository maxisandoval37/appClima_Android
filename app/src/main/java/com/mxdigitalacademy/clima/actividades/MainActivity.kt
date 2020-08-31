package com.mxdigitalacademy.clima.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.mxdigitalacademy.clima.R
import com.mxdigitalacademy.clima.red.Red
import com.mxdigitalacademy.clima.objCiudad.Ciudad
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null

    private fun solicitudHTTPVolley(contextActivity: AppCompatActivity, url: String) {
        val colaDeSolicitudes = Volley.newRequestQueue(contextActivity)

        val solicitud = StringRequest(Request.Method.GET, url, Response.Listener { response ->
            cargaDatos(response)

        }, Response.ErrorListener { error ->
            when (error.toString()) {
                "com.android.volley.ClientError" -> Toast.makeText(contextActivity, "Posible ubicación Inexistente", Toast.LENGTH_SHORT).show()
            }

        })
        colaDeSolicitudes.add(solicitud)
    }

    private fun cargaDatos(respuesta: String) {
        val gson = Gson()
        val ciudad = gson.fromJson(respuesta, Ciudad::class.java)

        setearInfoElementosVisuales(ciudad.name,ciudad.main?.temp?.toInt().toString(),ciudad.weather?.get(0)?.description)
        setearImagenDescripcion(ciudad.weather?.get(0)?.icon)
    }

    private fun setearInfoElementosVisuales(textoNombre: String?, textoTemp: String?, textoDescrip: String?){
        tvUbicacion.text = textoNombre
        tvTemperatura.text = textoTemp+"º"
        tvEstadoClima.text = textoDescrip
    }

    private fun setearImagenDescripcion(descripcion:String?) {//no usamos las de la api, por cuestiones de diseño
        when (descripcion) {
            "01d","01n" -> imgDescripcion.setImageResource(R.drawable.soleado)
            "03d","04d","50d","03n","04n","50n" -> imgDescripcion.setImageResource(R.drawable.nublado)
            "02d","02n" -> imgDescripcion.setImageResource(R.drawable.solmoderado)
            "09d","10d","11d","13d","09n","10n","11n","13n" -> imgDescripcion.setImageResource(R.drawable.lluvias)
        }
    }

    private fun refrescarDatosAPI(urlApi:String){
        swipeRefrescar.setOnRefreshListener {
            solicitudHTTPVolley(this, urlApi)
            Toast.makeText(this,"Actualizado",Toast.LENGTH_SHORT).show()
            swipeRefrescar.isRefreshing = false
        }
    }


    //          ------   TOOLBAR   ------
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menutoolbar,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itemCreditos -> Toast.makeText(this,R.string.creditos, Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hablitraBotonVolverToolBar(){
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun iniciarToolbar(){
        toolbar = findViewById(R.id.toolbar)
        toolbar?.setTitle(R.string.app_name)
        setSupportActionBar(toolbar)
        hablitraBotonVolverToolBar()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarToolbar()

        val ubicacion = intent.getStringExtra("com.mxdigitalacademy.clima.ciudad.LUGAR").toString()
        val urlApi = "https://api.openweathermap.org/data/2.5/weather?q=$ubicacion&appid=b88ca6348bffab22427dff7f05986265&lang=es&units=metric"

        if (Red.verficarConexionInternet(this))
            solicitudHTTPVolley(this, urlApi)

        else
            Toast.makeText(this,"No hay conexion a Internet",Toast.LENGTH_SHORT).show()

        refrescarDatosAPI(urlApi)
    }

}