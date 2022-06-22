package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed

import io.realm.RealmObject

open class LinksManaged(
    var reddit: RedditManaged? = null,
    var youtube_id: String? = null,
    var article: String? = null,
    var wikipedia: String? = null
) : RealmObject()