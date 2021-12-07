package com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model

import java.util.*

data class EventDate(
    var value: Date,
    var value2: Date,
    var rrule: String?,
    var timezone: String,
    var timezoneDB: String,
)