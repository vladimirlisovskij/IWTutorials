package com.example.task6

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
class Container(val imageLink: String, val name: String, val phone: String, val email: String, val id: String)
    : Parcelable