package com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit

import com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.NasaSchedule
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("calendar-event/")
    fun getEventList(): Call<NasaSchedule>
}