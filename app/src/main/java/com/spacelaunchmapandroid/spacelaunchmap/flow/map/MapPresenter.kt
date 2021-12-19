package com.spacelaunchmapandroid.spacelaunchmap.flow.map

import com.spacelaunchmapandroid.spacelaunchmap.MainActivity
import com.spacelaunchmapandroid.spacelaunchmap.core.network.retrofit.common.Common
import com.spacelaunchmapandroid.spacelaunchmap.service.nasa.model.NasaSchedule
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.SpaceXLaunchpad
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.SpaceXSchedule
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed.SpaceXLaunchpadManaged
import com.yandex.mapkit.geometry.Point
import io.realm.Realm
import retrofit2.Call
import retrofit2.Response

class MapPresenter(private val mapView: SLMapView) : SLMapControllerOutput {

    private var dataSpaceXLaunchpad: List<SpaceXLaunchpad>? = null
    private var coordinates: HashMap<Point, ArrayList<String>> = HashMap()
    private var realm: Realm = MainActivity.getRealmInstance()

    init {
        getData()
    }

    override fun getLaunchpadCoordinates(): Map<Point, List<String>> {
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
                    saveIntoRealm(dataSpaceXLaunchpad)
                    notifyMapToShowPlacemarks(dataSpaceXLaunchpad)
                }

                override fun onFailure(call: Call<List<SpaceXLaunchpad>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    private fun saveIntoRealm(dataSpaceXLaunchpad: List<SpaceXLaunchpad>?) {
        for (launchpad in dataSpaceXLaunchpad!!) {
            val spaceXLaunchpadManaged = SpaceXLaunchpadManaged(
                launchpad.id,
                launchpad.name,
                launchpad.locality,
                launchpad.region,
                launchpad.latitude,
                launchpad.longitude
            )

            realm.executeTransaction { transaction ->
                transaction.insert(spaceXLaunchpadManaged)
            }
        }
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
