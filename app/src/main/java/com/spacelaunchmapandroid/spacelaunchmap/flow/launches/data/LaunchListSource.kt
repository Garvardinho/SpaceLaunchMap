package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data

interface LaunchListSource {

    fun getCardData(position: Int): Launch

    fun getSize(): Int
}