package com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.managed

import io.realm.RealmObject

open class AdditionalLinkManaged: RealmObject() {
    lateinit var url: String
    var title: String? = null
}