package com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.spacex

import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.SpaceXSchedule
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServicesSpaceX {
    @GET("upcoming")
    fun getEventList(): Call<List<SpaceXSchedule>>
}