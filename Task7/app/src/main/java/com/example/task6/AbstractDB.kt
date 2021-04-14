package com.example.task6

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 1)
abstract class AbstractDB : RoomDatabase() {
    abstract fun employeeDAO(): EmployeeDAO

    companion object {
        private const val DB_NAME: String = "task7DataBase"

        private var instance: AbstractDB? = null

        fun getDB(context: Context): AbstractDB{
            return instance ?: let {
                instance = Room.databaseBuilder(context.applicationContext, AbstractDB::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .build()
                instance!!
            }
        }
    }
}