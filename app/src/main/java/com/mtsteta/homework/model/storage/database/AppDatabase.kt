package com.mtsteta.homework.model.storage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mtsteta.homework.model.storage.dao.FilmDAO
import com.mtsteta.homework.model.storage.dao.ProfileInformationDAO
import com.mtsteta.homework.model.storage.entity.Film
import com.mtsteta.homework.model.storage.entity.ProfileInformation

@Database(entities = [Film::class, ProfileInformation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDAO
    abstract fun profileInformationDao(): ProfileInformationDAO

    companion object {
        private const val DATABASE_NAME = "Films.db"

        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}
