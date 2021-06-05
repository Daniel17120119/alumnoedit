package com.example.appalumnoasunto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_nuevo_alumno.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoAlumnoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_alumno)

        var idAlumno: Int?= null

        if (intent.hasExtra("Alumno")){

            val  Alumno = intent.extras?.getSerializable("Alumno")as Alumno

            nombre_et.setText(Alumno.nombre)
            matricula_et.setText(Alumno.matricula.toString())
            asunto_et.setText(Alumno.asunto)
            idAlumno = Alumno.idAlumno
        }
        val database = AppDatabase.getDatabase(this)

        save_btn.setOnClickListener {
            val nombre = nombre_et.text.toString()
            val  matricula= matricula_et.text.toString().toInt()
            val  asunto = asunto_et.text.toString()

            val alumno = Alumno (nombre, matricula, asunto, R.drawable.ic_launcher_background)

            if (idAlumno !=null){
                CoroutineScope(Dispatchers.IO).launch {
                    alumno.idAlumno=idAlumno

                    database.Alumnos().update(alumno)

                    this@NuevoAlumnoActivity.finish()

                }

            }else {
                CoroutineScope(Dispatchers.IO).launch {
                    database.Alumnos().insertAll(alumno)

                    this@NuevoAlumnoActivity.finish()
                }

            }
        }
    }
}