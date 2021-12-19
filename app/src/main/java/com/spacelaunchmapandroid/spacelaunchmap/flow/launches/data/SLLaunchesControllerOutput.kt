package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data

import com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data.Launch

interface SLLaunchesControllerOutput {

    fun getLaunchesInfo(): ArrayList<Launch>
}