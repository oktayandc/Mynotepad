package com.example.mynotepad

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.R.id
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "notlar")
@Parcelize
data class DBase(
    @PrimaryKey(autoGenerate = true)
     val _id: Int=0,
     val baslik:String="baslik",
     val metin :String= "metin"
): Parcelable
