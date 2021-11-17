package com.spacelaunchmapandroid.spacelaunchmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.common.Common
import com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.NasaSchedule
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = Common.retrofitService
        service.getEventList().enqueue(object : retrofit2.Callback<NasaSchedule> {
            override fun onResponse(call: Call<NasaSchedule>, response: Response<NasaSchedule>) {
                var body = response.body()
            }

            override fun onFailure(call: Call<NasaSchedule>, t: Throwable) {

            }
        })
    }
}