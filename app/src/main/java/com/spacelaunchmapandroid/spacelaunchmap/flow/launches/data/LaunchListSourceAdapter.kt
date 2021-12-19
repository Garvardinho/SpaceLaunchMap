package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data

class LaunchListSourceAdapter : LaunchListSource {

    private val presenter: SLLaunchesControllerOutput = LaunchesPresenter()
    private val dataSource: ArrayList<Launch> = presenter.getLaunchesInfo()

    override fun getCardData(position: Int): Launch {
        return dataSource[position]
    }

    override fun getSize(): Int {
        return dataSource.size
    }
}
