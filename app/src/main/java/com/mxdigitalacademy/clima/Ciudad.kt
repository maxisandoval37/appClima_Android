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

    fun getNombre():String{
        return this._nombre
    }

    fun getTemp():String{
        return this._temp
    }

    fun getDescripcion():String{
        return this._descripcion
    }

}