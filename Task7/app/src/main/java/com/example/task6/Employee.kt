package com.example.task6

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Employee (
        @PrimaryKey(autoGenerate = true) val id: Long,
        val image: String,
        val name: String,
        val phone: String,
        val email: String
)