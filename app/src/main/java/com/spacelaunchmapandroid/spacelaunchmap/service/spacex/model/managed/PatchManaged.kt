package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed

import io.realm.RealmObject

open class PatchManaged : RealmObject() {
    var small: String? = null
    var large: String? = null
}
