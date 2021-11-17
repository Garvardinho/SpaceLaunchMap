package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed

import io.realm.RealmObject

open class FailureManaged : RealmObject() {
    var time: Long = 0
    var altitude: Long = 0
    lateinit var reason: String
}
