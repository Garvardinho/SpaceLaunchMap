package com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.common

import com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.RetrofitClient
import com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.RetrofitServices

object Common {
    private const val BASE_URL = "https://www.nasa.gov/api/2/"
    val retrofitService: RetrofitServices =
        RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}