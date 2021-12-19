package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data

import com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data.Launch

interface LaunchListSource {

    fun getCardData(position: Int): Launch

    fun getSize(): Int
}