package com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.managed

import io.realm.RealmObject

open class HitManaged: RealmObject() {
    lateinit var index: String
    lateinit var id: String
    lateinit var source: SourceManaged
}