package com.example.appalumnoasunto

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlumnosDao {

    @Query("SELECT * FROM Alumnos")
    fun getAll(): LiveData<List<Alumno>>

    @Query("SELECT * FROM Alumnos WHERE idAlumno = :id")
    fun get(id:Int): LiveData<Alumno>


    @Insert
    fun insertAll(vararg Alumnos:Alumno)

    @Update
    fun update (alumno: Alumno)

    /*  falta @delate*/
    @Delete
    fun delete(alumno : Alumno)



}