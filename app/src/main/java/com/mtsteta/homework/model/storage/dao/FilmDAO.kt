package com.mtsteta.homework.model.storage.dao

import androidx.room.*
import com.mtsteta.homework.model.storage.entity.Film

@Dao
interface FilmDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(film: Film)

    @Update
    fun updateFilm(film: Film)

    @Delete
    fun deleteFilm(film: Film)

    @Query("SELECT * FROM Film WHERE title == :title")
    fun getFilmByTitle(title: String): List<Film>

    @Query("SELECT * FROM Film")
    fun getFilms(): List<Film>
}