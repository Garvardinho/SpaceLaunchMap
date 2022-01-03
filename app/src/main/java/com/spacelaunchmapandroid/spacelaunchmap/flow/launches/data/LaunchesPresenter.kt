package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data

import com.spacelaunchmapandroid.spacelaunchmap.core.database.SLRealm
import com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.common.Common
import com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.NasaSchedule
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.SpaceXSchedule
import retrofit2.Call
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class LaunchesPresenter(private val launchesFragment: SLLaunchesFragment) :
    SLLaunchesControllerOutput {

    private var dataNasa: NasaSchedule? = null
    private var dataSpaceX: List<SpaceXSchedule>? = null
    private var launches: ArrayList<Launch> = ArrayList()

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
                SLRealm.saveSpaceXLaunchesInRealm(dataSpaceX)
                initLaunchList(dataSpaceX)
                launchesFragment.initList()
            }

            override fun onFailure(call: Call<List<SpaceXSchedule>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return launches
    }

    private fun initLaunchList(dataSpaceX: List<SpaceXSchedule>?) {
        for (launchSpaceX in dataSpaceX!!) {
            if (isDataCorrect(launchSpaceX)) {
                val launchpad = SLRealm.findSpaceXLaunchpadByID(launchSpaceX.launchpad)
                launches.add(
                    Launch(
                        launchSpaceX.name,
                        launchSpaceX.date_local,
                        "${launchpad.locality}, ${launchpad.region}",
                        "SpaceX",
                        launchpad.id!!
                    )
                )
            }
        }
    }

    private fun isDataCorrect(launchSpaceX: SpaceXSchedule): Boolean {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        try {
            val parsed = dateFormat.parse(launchSpaceX.date_local)
            return parsed!!.after(Date())
        } catch (pe: ParseException) {
            pe.printStackTrace()
        }

        return false
    }
}