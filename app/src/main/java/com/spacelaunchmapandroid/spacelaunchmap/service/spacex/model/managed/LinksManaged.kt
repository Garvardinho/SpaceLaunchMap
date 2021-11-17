package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed

import io.realm.RealmObject

open class LinksManaged : RealmObject() {
    lateinit var patch: PatchManaged
    lateinit var reddit: RedditManaged
    lateinit var flickr: FlickrManaged
    var presskit: String? = null
    var webcast: String? = null
    var youtube_id: String? = null
    var article: String? = null
    var wikipedia: String? = null
}