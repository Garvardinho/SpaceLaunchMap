package com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.common

import com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.RetrofitClient
import com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.nasa.RetrofitServicesNasa
import com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.spacex.RetrofitServicesSpaceX

object Common {
    private const val BASE_URL_NASA = "https://www.nasa.gov/api/2/calendar-event/"
    private const val BASE_URL_SPACEX = "https://api.spacexdata.com/v4/launches/"

    val retrofitServiceNasa: RetrofitServicesNasa =
        RetrofitClient.getClient(BASE_URL_NASA).create(RetrofitServicesNasa::class.java)

    val retrofitServicesSpaceX: RetrofitServicesSpaceX =
        RetrofitClient.getClient(BASE_URL_SPACEX).create(RetrofitServicesSpaceX::class.java)
}