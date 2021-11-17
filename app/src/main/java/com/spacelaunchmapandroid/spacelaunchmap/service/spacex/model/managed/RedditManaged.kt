package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed

import io.realm.RealmObject

open class RedditManaged : RealmObject() {
    var campaign: String? = null
    var launch: String? = null
    var media: String? = null
    lateinit var recovery: String
}
