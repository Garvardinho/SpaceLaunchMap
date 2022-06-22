package com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.nasa

import com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.NasaSchedule
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServicesNasa {
    @GET("_search?size=100")
    fun getEventList(): Call<NasaSchedule>
}