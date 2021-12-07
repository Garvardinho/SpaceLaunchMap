package com.spacelaunchmapandroid.spacelaunchmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.common.Common
import com.spacelaunchmapandroid.spacelaunchmap.flow.map.MapFragment
import com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.NasaSchedule
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.SpaceXSchedule
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment, MapFragment.getInstance())
            .addToBackStack(null)
            .setTransition(TRANSIT_FRAGMENT_FADE)
            .commit()

        val serviceNasa = Common.retrofitServiceNasa
        serviceNasa.getEventList().enqueue(object : retrofit2.Callback<NasaSchedule> {
            override fun onResponse(call: Call<NasaSchedule>, response: Response<NasaSchedule>) {
                var body = response.body()
            }

            override fun onFailure(call: Call<NasaSchedule>, t: Throwable) {

            }
        })

        val serviceSpaceX = Common.retrofitServicesSpaceX
        serviceSpaceX.getEventList().enqueue(object : retrofit2.Callback<List<SpaceXSchedule>> {
            override fun onResponse(
                call: Call<List<SpaceXSchedule>>,
                response: Response<List<SpaceXSchedule>>
            ) {
                var body = response.body()
            }

            override fun onFailure(call: Call<List<SpaceXSchedule>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}