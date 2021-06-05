package com.example.appalumnoasunto

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Alumnos")
class Alumno (
    val nombre:String,
    val matricula:Int,
    val asunto:String,
    val imagen:Int,

    @PrimaryKey(autoGenerate = true)
    var idAlumno: Int=0
): Serializable