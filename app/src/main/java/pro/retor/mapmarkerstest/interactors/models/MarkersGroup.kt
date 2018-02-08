package pro.retor.mapmarkerstest.interactors.models

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by retor on 05.02.2018.
 */
class MarkersGroup(val price: Long, val markers: List<SomeMarker>, var color: Float = -1f) : Parcelable {
    constructor(source: Parcel) : this(
            source.readLong(),
            source.createTypedArrayList(SomeMarker.CREATOR),
            source.readFloat()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(price)
        writeTypedList(markers)
        writeFloat(color)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MarkersGroup> = object : Parcelable.Creator<MarkersGroup> {
            override fun createFromParcel(source: Parcel): MarkersGroup = MarkersGroup(source)
            override fun newArray(size: Int): Array<MarkersGroup?> = arrayOfNulls(size)
        }
    }
}