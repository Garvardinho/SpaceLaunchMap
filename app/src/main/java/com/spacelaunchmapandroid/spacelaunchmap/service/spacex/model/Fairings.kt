package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model

data class Fairings(
    var reused: Boolean?,
    var recovery_attempt: Boolean?,
    var recovered: String?,
    var ships: MutableList<String>
)
