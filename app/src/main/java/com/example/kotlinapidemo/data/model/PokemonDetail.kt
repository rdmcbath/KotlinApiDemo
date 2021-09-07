package com.example.kotlinapidemo.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    @SerializedName("name") val name: String?,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(weight)
        parcel.writeInt(height)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonDetail> {
        override fun createFromParcel(parcel: Parcel): PokemonDetail {
            return PokemonDetail(parcel)
        }

        override fun newArray(size: Int): Array<PokemonDetail?> {
            return arrayOfNulls(size)
        }
    }
}