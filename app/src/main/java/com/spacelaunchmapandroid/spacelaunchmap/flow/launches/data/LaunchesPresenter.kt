package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data

import com.spacelaunchmapandroid.spacelaunchmap.MainActivity
import com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.common.Common
import com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.NasaSchedule
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.SpaceXSchedule
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed.LinksManaged
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed.RedditManaged
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed.SpaceXLaunchpadManaged
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed.SpaceXScheduleManaged
import io.realm.Realm
import io.realm.kotlin.where
import retrofit2.Call
import retrofit2.Response

class LaunchesPresenter(private val launchesFragment: SLLaunchesFragment)
    : SLLaunchesControllerOutput {

    private var dataNasa: NasaSchedule? = null
    private var dataSpaceX: List<SpaceXSchedule>? = null
    private var launches: ArrayList<Launch> = ArrayList()
    private val realm: Realm = MainActivity.getRealmInstance()

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
                saveIntoRealm(dataSpaceX)
                for (launchSpaceX in dataSpaceX!!) {
                    val launchpad = realm.where<SpaceXLaunchpadManaged>()
                        .equalTo("id", launchSpaceX.launchpad).findFirst()
                    launches.add(
                        Launch(
                            launchSpaceX.name,
                            launchSpaceX.date_local,
                            "${launchpad?.locality}, ${launchpad?.region}",
                            "SpaceX"
                        )
                    )
                }
                launchesFragment.initList()
            }

            override fun onFailure(call: Call<List<SpaceXSchedule>>, t: Throwable) {
                t.printStackTrace()
            }
        })

        return launches
    }

    private fun saveIntoRealm(dataSpaceX: List<SpaceXSchedule>?) {
        for (launch in dataSpaceX!!) {
            val spaceXScheduleManaged = SpaceXScheduleManaged(
                LinksManaged(
                    RedditManaged(
                        launch.links.reddit.campaign,
                        launch.links.reddit.launch,
                        launch.links.reddit.media,
                        launch.links.reddit.recovery
                    ),
                    launch.links.youtube_id,
                    launch.links.article,
                    launch.links.wikipedia
                ),
                launch.launchpad,
                launch.name,
                launch.details,
                launch.date_local
            )

            realm.executeTransaction { transaction ->
                transaction.insert(spaceXScheduleManaged)
            }
        }
    }
}