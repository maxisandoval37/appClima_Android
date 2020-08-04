package com.mxdigitalacademy.clima

class Ciudad (nombre:String, temp:String, descripcion:String) {

    private var _nombre:String = ""
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

    fun getterNombre():String{
        return this._nombre
    }

    fun getterTemp():String{
        return this._temp
    }

    fun getterDescripcion():String{
        return this._descripcion
    }

}