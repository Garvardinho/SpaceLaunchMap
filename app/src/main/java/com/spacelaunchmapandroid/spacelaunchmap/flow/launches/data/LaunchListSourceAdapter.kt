package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data

import com.spacelaunchmapandroid.spacelaunchmap.core.database.SLRealm
import kotlin.collections.ArrayList


class LaunchListSourceAdapter(launchesFragment: SLLaunchesFragment) : LaunchListSource {

    private val presenter: SLLaunchesControllerOutput = LaunchesPresenter(launchesFragment)
    private var dataSource: ArrayList<Launch> = presenter.getLaunchesInfo()

    override fun getCardData(position: Int): Launch {
        return dataSource[position]
    }

    override fun getSize(): Int {
        return dataSource.size
    }

    override fun sortBy(stringToSort: String) {
        val newDataSource: ArrayList<Launch> = ArrayList()

        for (launch in dataSource) {
            if (launch.title.lowercase().contains(stringToSort.lowercase()) ||
                SLRealm.findSpaceXLaunchpadByID(launch.launchpad)
                    .name?.lowercase()?.contains(stringToSort.lowercase()) == true)
                newDataSource.add(launch)
        }

        dataSource = newDataSource
    }
}
