package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data


class LaunchListSourceAdapter(launchesFragment: SLLaunchesFragment) : LaunchListSource {

    private val presenter: SLLaunchesControllerOutput = LaunchesPresenter(launchesFragment)
    private val dataSource: ArrayList<Launch> = presenter.getLaunchesInfo()

    override fun getCardData(position: Int): Launch {
        return dataSource[position]
    }

    override fun getSize(): Int {
        return dataSource.size
    }
}
