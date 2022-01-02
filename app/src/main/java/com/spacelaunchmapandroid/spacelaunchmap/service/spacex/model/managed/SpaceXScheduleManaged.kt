package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SpaceXScheduleManaged(
    @PrimaryKey
    var name: String? = null,
    var links: LinksManaged? = null,
    var launchpad: String? = null,
    var details: String? = null,
    var date_local: String? = null,
) : RealmObject()