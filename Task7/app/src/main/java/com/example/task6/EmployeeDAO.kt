package com.example.task6

import androidx.room.*

@Dao
interface EmployeeDAO {

    @Query("select * from employee")
    fun getAll(): List<Employee>

    @Query("delete from employee where id=:id")
    fun deleteById(id: Int): Unit

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employee: Employee)
}