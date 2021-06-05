package com.example.appalumnoasunto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var listaAlumnos = emptyList<Alumno>()

        val database = AppDatabase.getDatabase( this)

        database.Alumnos().getAll().observe( this, Observer{
            listaAlumnos= it

            val adapter = AlumnosAdapter(this,listaAlumnos)

            lista.adapter = adapter
        })
        lista.setOnItemClickListener{ parent,view,position,id ->
            val intent = Intent(this,AlumnoActivity::class.java)
            intent.putExtra("id",listaAlumnos[position].idAlumno)
            startActivity(intent)

        }
        floatingActionButton.setOnClickListener{
            val  intent = Intent(this,NuevoAlumnoActivity::class.java)
            startActivity(intent)
        }
    }
}