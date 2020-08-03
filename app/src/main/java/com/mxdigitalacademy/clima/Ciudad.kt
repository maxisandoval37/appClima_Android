package com.mxdigitalacademy.clima

class Ciudad (nombre:String, temp:String, descripcion:String) {

     var _nombre:String = ""
    private var _temp:String = ""
    private var _descripcion:String = ""

    init {
        this._nombre=nombre
        this._temp=temp
        this._descripcion=descripcion
    }

    fun mostrarDatos(){
        println("Nombre: $_nombre | Temperatura: $_temp | Estado: $_descripcion")
    }
}