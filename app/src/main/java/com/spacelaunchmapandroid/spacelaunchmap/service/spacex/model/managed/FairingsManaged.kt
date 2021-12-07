package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed

import io.realm.RealmList
import io.realm.RealmObject

open class FairingsManaged : RealmObject() {
    var reused: Boolean? = null
    var recovery_attempt: Boolean? = null
    var recovered: String? = null
    lateinit var ships: RealmList<String>
}
