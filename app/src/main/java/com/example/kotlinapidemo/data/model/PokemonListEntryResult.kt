package com.example.kotlinapidemo.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PokemonListEntryResult(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PokemonListEntryResult

        if (name != other.name) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + url.hashCode()

        return result
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonListEntryResult> {
        override fun createFromParcel(parcel: Parcel): PokemonListEntryResult {
            return PokemonListEntryResult(parcel)
        }

        override fun newArray(size: Int): Array<PokemonListEntryResult?> {
            return arrayOfNulls(size)
        }
    }
}