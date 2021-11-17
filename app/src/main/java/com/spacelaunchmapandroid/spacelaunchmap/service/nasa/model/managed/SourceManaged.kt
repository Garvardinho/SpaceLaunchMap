package com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.managed

import io.realm.RealmList
import io.realm.RealmObject

open class SourceManaged: RealmObject() {
    lateinit var title: String
    lateinit var nid: String
    lateinit var type: String
    lateinit var changed: String
    lateinit var uuid: String
    lateinit var name: String
    lateinit var uri: String
    lateinit var additionalLink: RealmList<AdditionalLinkManaged>
    lateinit var description: String
    var eventDate: RealmList<EventDateManaged>? = null
    var masterImage: RealmList<MasterImageManaged>? = null
    var customDateText: String? = null
    var isAllDay: Boolean? = null
    var dateFormat: RealmList<String>? = null
}
