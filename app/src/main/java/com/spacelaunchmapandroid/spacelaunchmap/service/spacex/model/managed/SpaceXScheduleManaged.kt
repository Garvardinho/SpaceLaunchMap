package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed

import io.realm.RealmList
import io.realm.RealmObject

open class SpaceXScheduleManaged : RealmObject() {
    lateinit var fairings: FairingsManaged
    lateinit var links: LinksManaged
    var static_fire_date_utc: String? = null
    var static_fire_date_unix: String? = null
    var net: Boolean = false
    var window: Long? = null
    var rocket: String? = null
    var success: Boolean? = null
    lateinit var failures: RealmList<FailureManaged>
    var details: String? = null
    lateinit var crew: RealmList<String>
    lateinit var ships: RealmList<String>
    lateinit var capsules: RealmList<String>
    lateinit var payloads: RealmList<String>
    var launchpad: String? = null
    var flight_number: Int = 0
    lateinit var name: String
    lateinit var date_utc: String
    var date_unix: Long = 0
    lateinit var date_local: String
    lateinit var date_precision: String
    var upcoming: Boolean = true
    lateinit var cores: RealmList<CoreManaged>
    var auto_update: Boolean = true
    var tbd: Boolean = false
    lateinit var launch_library_id: String
    lateinit var id: String
}