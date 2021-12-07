package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed

import io.realm.RealmObject

open class CoreManaged : RealmObject() {
    var core: String? = null
    var flight: Int? = null
    var gridfins: Boolean? = null
    var legs: Boolean? = null
    var reused: Boolean? = null
    var landing_attempt: Boolean? = null
    var landing_success: Boolean? = null
    var landing_type: String? = null
    var landpad: String? = null
}
