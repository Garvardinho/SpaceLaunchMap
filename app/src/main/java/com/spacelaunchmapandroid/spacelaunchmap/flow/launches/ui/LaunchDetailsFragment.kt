package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.spacelaunchmapandroid.spacelaunchmap.MainActivity
import com.spacelaunchmapandroid.spacelaunchmap.R
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed.SpaceXLaunchpadManaged
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed.SpaceXScheduleManaged
import io.realm.kotlin.where

private const val LAUNCH_TITLE = "LaunchDetailsFragment.launchTitle"

class LaunchDetailsFragment : Fragment() {

    private var launchTitle: String? = null
    private var launch: SpaceXScheduleManaged? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            launchTitle = it.getString(LAUNCH_TITLE)
        }
        launch = MainActivity.getRealmInstance()
            .where<SpaceXScheduleManaged>().equalTo("name", launchTitle).findFirst()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.launch_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val launchTitleTextView: TextView = view.findViewById(R.id.launch_title)
        val launchDate: TextView = view.findViewById(R.id.launch_date)
        val launchCompany: TextView = view.findViewById(R.id.launch_company)
        val launchLocation: TextView = view.findViewById(R.id.launch_location)
        val launchDetails: TextView = view.findViewById(R.id.launch_details)

        launchTitleTextView.text = launch?.name
        launchDate.text = getString(R.string.date, launch?.date_local?.subSequence(0, 10))
        launchCompany.text = getString(R.string.spacex)
        val launchpad = MainActivity.getRealmInstance()
            .where<SpaceXLaunchpadManaged>().equalTo("id", launch?.launchpad).findFirst()
        launchLocation.text = getString(R.string.location, launchpad?.locality, launchpad?.locality)

        if (launch?.details != null)
            launchDetails.text = getString(R.string.details, launch?.details)
        else
            launchDetails.text = getString(R.string.details, "none")

    }

    companion object {
        @JvmStatic
        fun newInstance(launchTitle: String) =
            LaunchDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(LAUNCH_TITLE, launchTitle)
                }
            }
    }
}
