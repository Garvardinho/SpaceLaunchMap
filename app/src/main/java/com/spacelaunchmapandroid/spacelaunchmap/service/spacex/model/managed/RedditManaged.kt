package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed

import io.realm.RealmObject

open class RedditManaged(
    var campaign: String? = null,
    var launch: String? = null,
    var media: String? = null,
    var recovery: String? = null
) : RealmObject()
