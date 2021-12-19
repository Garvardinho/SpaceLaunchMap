package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data

import android.os.Parcel
import android.os.Parcelable

class Launch() : Parcelable {

    lateinit var title: String
    lateinit var date: String
    lateinit var company: String

    constructor(
        title: String,
        date: String,
        location: String
    ) : this() {
        this.title = title
        this.date = date
        this.company = location
    }

    constructor(parcel: Parcel) : this() {
        title = parcel.readString()!!
        date = parcel.readString()!!
        company = parcel.readString()!!
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(title)
        parcel?.writeString(date)
        parcel?.writeString(company)
    }

    companion object CREATOR : Parcelable.Creator<Launch> {
        override fun createFromParcel(parcel: Parcel): Launch {
            return Launch(parcel)
        }

        override fun newArray(size: Int): Array<Launch?> {
            return arrayOfNulls(size)
        }
    }

}