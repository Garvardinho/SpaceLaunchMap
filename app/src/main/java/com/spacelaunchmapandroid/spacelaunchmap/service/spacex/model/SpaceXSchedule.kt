package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model

data class SpaceXSchedule(
    var links: Links,
    var launchpad: String?,
    var name: String,
    var details: String?,
    var date_local: String,
)