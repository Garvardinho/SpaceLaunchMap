package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model

data class Failure(
    var time: Long,
    var altitude: Long,
    var reason: String
)
