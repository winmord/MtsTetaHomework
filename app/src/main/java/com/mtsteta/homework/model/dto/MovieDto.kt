package com.mtsteta.homework.model.dto

import android.os.Parcel
import android.os.Parcelable

data class MovieDto(
    val title: String,
    val description: String,
    val rateScore: Int,
    val ageRestriction: Int,
    val imageUrl: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(rateScore)
        parcel.writeInt(ageRestriction)
        parcel.writeString(imageUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDto> {
        override fun createFromParcel(parcel: Parcel): MovieDto {
            return MovieDto(parcel)
        }

        override fun newArray(size: Int): Array<MovieDto?> {
            return arrayOfNulls(size)
        }
    }
}