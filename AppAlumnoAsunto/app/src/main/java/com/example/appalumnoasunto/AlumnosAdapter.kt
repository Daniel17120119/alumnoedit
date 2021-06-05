package com.example.appalumnoasunto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_alumno.view.*

class AlumnosAdapter (private val mContext: Context, private val listaAlumnos: List<Alumno>) : ArrayAdapter<Alumno>(mContext, 0,listaAlumnos) {
    override fun getView(position:Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_alumno, parent, false)

        val Alumno= listaAlumnos[position]

        layout.nombre.text= Alumno.nombre
        layout.matricula.text= Alumno.matricula.toString()
        layout.imageView.setImageResource(Alumno.imagen)
        return layout

    }
}