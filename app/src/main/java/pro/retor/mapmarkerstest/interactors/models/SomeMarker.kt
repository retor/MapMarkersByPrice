package pro.retor.mapmarkerstest.interactors.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by retor on 01.02.2018.
 */
class SomeMarker(val lng: Double, val lat: Double, val title: String, val price: Long)
    : Parcelable {
    constructor(source: Parcel) : this(
            source.readDouble(),
            source.readDouble(),
            source.readString(),
            source.readLong()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeDouble(lng)
        writeDouble(lat)
        writeString(title)
        writeLong(price)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SomeMarker> = object : Parcelable.Creator<SomeMarker> {
            override fun createFromParcel(source: Parcel): SomeMarker = SomeMarker(source)
            override fun newArray(size: Int): Array<SomeMarker?> = arrayOfNulls(size)
        }
    }
}