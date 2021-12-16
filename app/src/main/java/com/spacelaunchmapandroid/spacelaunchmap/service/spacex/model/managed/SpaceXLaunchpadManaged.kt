package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed

import io.realm.RealmObject

open class SpaceXLaunchpadManaged : RealmObject() {
    lateinit var name: String
    lateinit var locality: String
    lateinit var region: String
    var latitude: Double = 0.0
    var longitude: Double = 0.0
}