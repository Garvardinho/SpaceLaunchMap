package com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit

import com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.NasaSchedule
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("_search?size=100")
    fun getEventList(): Call<NasaSchedule>
}