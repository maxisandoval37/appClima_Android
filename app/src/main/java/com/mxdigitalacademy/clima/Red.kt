package com.mxdigitalacademy.clima

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Red {

    companion object {

        var ciudadRet: Ciudad? = null

        fun verficarConexionInternet(contextActivity: AppCompatActivity): Boolean {
            val connectivityManager =
                contextActivity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo

            return (networkInfo != null) && networkInfo.isConnected
        }

        fun solicitudHTTPVolley(contextActivity: AppCompatActivity, url: String, ubicacion: String) {
            val colaDeSolicitudes = Volley.newRequestQueue(contextActivity)
            val direccion = url.replace("LUGAR", ubicacion)

            val solicitud = StringRequest(Request.Method.GET, direccion, Response.Listener<String> { response ->

                    mapearJSON(response)

                }, Response.ErrorListener { error ->
                    Toast.makeText(contextActivity, error.toString(), Toast.LENGTH_SHORT).show()
                })

            colaDeSolicitudes.add(solicitud)

        }

        private fun mapearJSON(respuesta: String) {
            val jsonMapeado = JSONObject(respuesta)

            val nombre = jsonMapeado.getString("name")
            val temperatura = jsonMapeado.getJSONObject("main").getString("temp")
            val descripcion = jsonMapeado.getJSONArray("weather").getJSONObject(0).getString("description")

            this.ciudadRet = Ciudad(nombre, temperatura, descripcion)
        }

        fun obtenerCiudad(): Ciudad? {
            return ciudadRet
        }

    }

}