package com.example.roomapp.ModelClass

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")

data class User(@PrimaryKey( autoGenerate = true)
                val id:Int,
                val firstName:String,
                val lastName:String,
                val age:Int,

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
    ) {
    }

    override fun describeContents(): Int {
   return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(firstName)
        dest.writeString(lastName)
        dest.writeInt(age)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}

data class Address(val streetName:String,val streetNumber:Int)
