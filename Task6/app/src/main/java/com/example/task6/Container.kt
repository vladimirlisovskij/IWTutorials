package com.example.task6

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
class Container(val imageLink: String, val name: String, val phone: String, val email: String, val id: String) : Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: ""
    )

    companion object : Parceler<Container> {
        override fun Container.write(parcel: Parcel, flags: Int) {
            parcel.writeString(imageLink)
            parcel.writeString(name)
            parcel.writeString(phone)
            parcel.writeString(email)
            parcel.writeString(id)
        }

        override fun create(parcel: Parcel): Container = Container(parcel)
    }
}