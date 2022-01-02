package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed

import io.realm.RealmList
import io.realm.RealmObject

open class SpaceXScheduleManaged(
    var links: LinksManaged? = null,
    var launchpad: String? = null,
    var name: String? = null,
    var details: String? = null,
    var date_local: String? = null,
) : RealmObject()