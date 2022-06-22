package com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.spacex

import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.SpaceXLaunchpad
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServicesSpaceXLaunchpad {
    @GET("launchpads")
    fun getLaunchpadList(): Call<List<SpaceXLaunchpad>>
}