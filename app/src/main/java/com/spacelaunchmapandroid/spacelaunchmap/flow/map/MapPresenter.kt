package com.spacelaunchmapandroid.spacelaunchmap.flow.map

import com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.common.Common
import com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.NasaSchedule
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.SpaceXLaunchpad
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.SpaceXSchedule
import com.yandex.mapkit.geometry.Point
import retrofit2.Call
import retrofit2.Response

class Presenter(private val mapView: SLMapView) : SLMapControllerOutput {

    private var dataNasa: NasaSchedule? = null
    private var dataSpaceX: List<SpaceXSchedule>? = null
    private var dataSpaceXLaunchpad: List<SpaceXLaunchpad>? = null
    private var coordinates: HashMap<Point, ArrayList<String>> = HashMap()

    init {
        getData()
    }

    override fun getLaunchpadCoordinates(): Map<Point, List<String>> {
        return coordinates
    }

    private fun getData() {
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
            }

            override fun onFailure(call: Call<List<SpaceXSchedule>>, t: Throwable) {
                t.printStackTrace()
            }
        })

        val serviceSpaceXLaunchpad = Common.retrofitServicesSpaceXLaunchpad
        serviceSpaceXLaunchpad.getLaunchpadList()
            .enqueue(object : retrofit2.Callback<List<SpaceXLaunchpad>> {
                override fun onResponse(
                    call: Call<List<SpaceXLaunchpad>>,
                    response: Response<List<SpaceXLaunchpad>>
                ) {
                    dataSpaceXLaunchpad = response.body()
                    notifyMapToShowPlacemarks(dataSpaceXLaunchpad)
                }

                override fun onFailure(call: Call<List<SpaceXLaunchpad>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun notifyMapToShowPlacemarks(dataSpaceXLaunchpad: List<SpaceXLaunchpad>?) {

        for (item in dataSpaceXLaunchpad!!) {
            var isNew = true
            for ((point, _) in coordinates) {
                if (item.latitude == point.latitude && item.longitude == point.longitude) {
                    coordinates[point]!!.add(item.name)
                    isNew = false
                }
            }

            if (isNew) {
                val point = Point(item.latitude, item.longitude)
                coordinates[point] = ArrayList()
                coordinates[point]!!.add(item.name)
            }
        }
        mapView.showPlacemarks()
    }
}
