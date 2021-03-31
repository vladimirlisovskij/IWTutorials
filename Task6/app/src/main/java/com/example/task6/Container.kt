package com.example.task6

import android.os.Parcel
import android.os.Parcelable

class Container(imageLink: String, nameStr: String, phoneStr: String, emailStr: String, idStr: String) : Parcelable{
    var avatarHref: String = imageLink
    var name: String = nameStr
    var phone: String = phoneStr
    var email: String = emailStr
    var id: String = idStr

    constructor(parcel: Parcel) : this(
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: "",
            parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(avatarHref)
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(email)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Container> {
        override fun createFromParcel(parcel: Parcel): Container = Container(parcel)
        override fun newArray(size: Int): Array<Container?>  = arrayOfNulls(size)
    }
}