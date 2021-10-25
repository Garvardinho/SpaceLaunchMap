package com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.managed

import io.realm.RealmList
import io.realm.RealmObject

open class HitsManaged : RealmObject() {
    var total: Int = 0
    var hits: RealmList<HitManaged>? = null
}