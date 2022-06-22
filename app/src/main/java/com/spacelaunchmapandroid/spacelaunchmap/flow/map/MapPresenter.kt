package com.spacelaunchmapandroid.spacelaunchmap.flow.map

import com.spacelaunchmapandroid.spacelaunchmap.core.database.SLRealm
import com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.common.Common
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.SpaceXLaunchpad
import com.yandex.mapkit.geometry.Point
import retrofit2.Call
import retrofit2.Response

class MapPresenter(private val mapView: SLMapView) : SLMapControllerOutput {

    private var dataSpaceXLaunchpad: List<SpaceXLaunchpad>? = null
    private var coordinates: HashMap<String, Point> = HashMap()

    init {
        getData()
    }

    override fun getLaunchpadCoordinates(): Map<String, Point> {
        return coordinates
    }

    private fun getData() {
        val serviceSpaceXLaunchpad = Common.retrofitServicesSpaceXLaunchpad
        serviceSpaceXLaunchpad.getLaunchpadList()
            .enqueue(object : retrofit2.Callback<List<SpaceXLaunchpad>> {
                override fun onResponse(
                    call: Call<List<SpaceXLaunchpad>>,
                    response: Response<List<SpaceXLaunchpad>>
                ) {
                    dataSpaceXLaunchpad = response.body()
                    SLRealm.saveSpaceXLaunchpadsInRealm(dataSpaceXLaunchpad)
                    notifyMapToShowPlacemarks(dataSpaceXLaunchpad)
                }

                override fun onFailure(call: Call<List<SpaceXLaunchpad>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun notifyMapToShowPlacemarks(dataSpaceXLaunchpad: List<SpaceXLaunchpad>?) {
        for (item in dataSpaceXLaunchpad!!) {
            coordinates[item.name] = Point(item.latitude, item.longitude)
        }
        mapView.showPlacemarks()
    }
}
