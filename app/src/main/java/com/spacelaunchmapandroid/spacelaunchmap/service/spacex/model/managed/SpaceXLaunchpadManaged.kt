package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SpaceXLaunchpadManaged(
    @PrimaryKey
    var id: String? = null,
    var name: String? = null,
    var locality: String? = null,
    var region: String? = null,
    var latitude: Double = 0.0,
    var longitude: Double = 0.0
) : RealmObject()