package com.example.appalumnoasunto

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_alumno.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlumnoActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var Alumno: Alumno
    private lateinit var AlumnoLiveData: LiveData<Alumno>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alumno)

        database = AppDatabase.getDatabase(this)
        val idAlumno =intent.getIntExtra("id", 0)
        AlumnoLiveData = database.Alumnos().get(idAlumno)

        AlumnoLiveData.observe(this, Observer {
            Alumno = it

            nombre_Alumno.text=Alumno.nombre
            matricula_Alumno.text= Alumno.matricula.toString()
            asunto_Alumno.text= Alumno.asunto
            imagen.setImageResource(Alumno.imagen)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?):Boolean {
        menuInflater.inflate(R.menu.alumno_manu,menu)



        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        when (item.itemId){
            R.id.edit_item ->{
                val intent = Intent(this,NuevoAlumnoActivity::class.java)
                intent.putExtra("Alumno",Alumno)
                startActivity(intent)

            }
            R.id.delete_item ->{
                AlumnoLiveData.removeObservers(this)

                CoroutineScope(Dispatchers.IO).launch {
                    database.Alumnos().delete(Alumno)
                    this@AlumnoActivity.finish()
                }
            }

        }
        return super.onOptionsItemSelected(item)
    }
}