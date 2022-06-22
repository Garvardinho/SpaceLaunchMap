package com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model

data class Source(
    var title: String,
    var nid: String,
    var type: String,
    var changed: String,
    var uuid: String,
    var name: String,
    var uri: String,
    var additionalLink: MutableList<AdditionalLink>,
    var description: String,
    var eventDate: MutableList<EventDate>?,
    var masterImage: MutableList<MasterImage>?,
    var customDateText: String?,
    var isAllDay: Boolean?,
    var dateFormat: MutableList<String>?
)