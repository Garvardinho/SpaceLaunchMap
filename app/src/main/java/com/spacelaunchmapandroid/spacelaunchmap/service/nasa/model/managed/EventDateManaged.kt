package com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.managed

import io.realm.RealmObject
import java.util.*

open class EventDateManaged: RealmObject() {
    lateinit var value: Date
    lateinit var value2: Date
    var rrule: String? = null
    lateinit var timezone: String
    lateinit var timezoneDB: String
}