package com.spacelaunchmapandroid.spacelaunchmap.core.database

import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.SpaceXLaunchpad
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.SpaceXSchedule
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed.LinksManaged
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed.RedditManaged
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed.SpaceXLaunchpadManaged
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed.SpaceXScheduleManaged
import io.realm.Realm
import io.realm.kotlin.where

object SLRealm {

    val realm: Realm = Realm.getDefaultInstance()

    fun saveSpaceXLaunchpadsInRealm(dataSpaceXLaunchpad: List<SpaceXLaunchpad>?) {
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
                transaction.insertOrUpdate(spaceXLaunchpadManaged)
            }
        }
    }

    fun saveSpaceXLaunchesInRealm(dataSpaceX: List<SpaceXSchedule>?) {
        for (launch in dataSpaceX!!) {
            val spaceXScheduleManaged = SpaceXScheduleManaged(
                launch.name,
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
                launch.details,
                launch.date_local
            )

            realm.executeTransaction { transaction ->
                transaction.insertOrUpdate(spaceXScheduleManaged)
            }
        }
    }

    fun findSpaceXLaunchByName(name: String?): SpaceXScheduleManaged? {
        return Realm.getDefaultInstance()
            .where<SpaceXScheduleManaged>().equalTo("name", name).findFirst()
    }

    fun findSpaceXLaunchpadByID(id: String?): SpaceXLaunchpadManaged? {
        return Realm.getDefaultInstance()
            .where<SpaceXLaunchpadManaged>().equalTo("id", id).findFirst()
    }
}