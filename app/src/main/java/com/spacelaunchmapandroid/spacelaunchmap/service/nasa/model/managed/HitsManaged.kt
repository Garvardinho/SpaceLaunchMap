package com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.managed

import com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.Hit
import io.realm.RealmObject

class HitsManaged : RealmObject() {
    var total: Int = 0
    lateinit var hits: MutableList<Hit>
}