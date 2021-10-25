package com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.managed

import io.realm.RealmObject

open class NasaScheduleManaged: RealmObject() {
    var hits: HitsManaged? = HitsManaged()
}