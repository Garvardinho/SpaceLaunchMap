package com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model

data class Links(
    var patch: Patch,
    var reddit: Reddit,
    var flickr: Flickr,
    var presskit: String?,
    var webcast: String?,
    var youtube_id: String?,
    var article: String?,
    var wikipedia: String?
)