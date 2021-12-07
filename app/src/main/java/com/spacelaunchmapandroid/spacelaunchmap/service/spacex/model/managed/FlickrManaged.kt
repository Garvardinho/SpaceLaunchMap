package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed

import io.realm.RealmList
import io.realm.RealmObject

open class FlickrManaged : RealmObject() {
    lateinit var small: RealmList<String>
    lateinit var original: RealmList<String>
}
