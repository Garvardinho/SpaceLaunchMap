package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data

import com.spacelaunchmapandroid.spacelaunchmap.MainActivity
import com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.common.Common
import com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.NasaSchedule
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.SpaceXSchedule
import io.realm.Realm
import retrofit2.Call
import retrofit2.Response

class LaunchesPresenter : SLLaunchesControllerOutput {

    private var dataNasa: NasaSchedule? = null
    private var dataSpaceX: List<SpaceXSchedule>? = null
    private var launches: ArrayList<Launch> = ArrayList()
    private var realm: Realm = MainActivity.getRealmInstance()

    override fun getLaunchesInfo(): ArrayList<Launch> {
        val serviceNasa = Common.retrofitServiceNasa
        serviceNasa.getEventList().enqueue(object : retrofit2.Callback<NasaSchedule> {
            override fun onResponse(
                call: Call<NasaSchedule>,
                response: Response<NasaSchedule>
            ) {
                dataNasa = response.body()
            }

            override fun onFailure(call: Call<NasaSchedule>, t: Throwable) {
                t.printStackTrace()
            }
        })

        val serviceSpaceX = Common.retrofitServicesSpaceX
        serviceSpaceX.getEventList().enqueue(object : retrofit2.Callback<List<SpaceXSchedule>> {
            override fun onResponse(
                call: Call<List<SpaceXSchedule>>,
                response: Response<List<SpaceXSchedule>>
            ) {
                dataSpaceX = response.body()
                for (launchSpaceX in dataSpaceX!!) {
                    launches.add(
                        Launch(
                        launchSpaceX.name,
                        launchSpaceX.date_local,
                        "SpaceX"
                    )
                    )
                }
            }

            override fun onFailure(call: Call<List<SpaceXSchedule>>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return launches
    }
}