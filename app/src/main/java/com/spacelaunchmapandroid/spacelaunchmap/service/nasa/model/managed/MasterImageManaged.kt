package com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.managed

import io.realm.RealmObject

open class MasterImageManaged: RealmObject() {
    lateinit var id: String
    lateinit var fid: String
    lateinit var uri: String
    lateinit var width: String
    lateinit var height: String
}