package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model

data class SpaceXLaunchpad(
    var id: String,
    var name: String,
    var locality: String,
    var region: String,
    var latitude: Double,
    var longitude: Double
)