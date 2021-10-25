package com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.managed

import io.realm.RealmObject

class NasaScheduleManaged: RealmObject() {
    var hits = HitsManaged()
}