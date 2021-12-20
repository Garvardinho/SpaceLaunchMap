package com.spacelaunchmapandroid.spacelaunchmap.flow.map

import com.yandex.mapkit.geometry.Point

interface SLMapControllerOutput {

    fun getLaunchpadCoordinates(): Map<String, Point>
}