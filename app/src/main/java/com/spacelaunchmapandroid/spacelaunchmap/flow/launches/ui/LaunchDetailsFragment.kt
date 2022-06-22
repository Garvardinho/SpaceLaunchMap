package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import com.spacelaunchmapandroid.spacelaunchmap.R
import com.spacelaunchmapandroid.spacelaunchmap.core.database.SLRealm
import com.spacelaunchmapandroid.spacelaunchmap.flow.map.MapFragment
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed.SpaceXLaunchpadManaged
import com.spacelaunchmapandroid.spacelaunchmap.service.spacex.model.managed.SpaceXScheduleManaged

private const val LAUNCH_TITLE = "LaunchDetailsFragment.launchTitle"

class LaunchDetailsFragment : Fragment() {

    private var launchTitle: String? = null
    private var launch: SpaceXScheduleManaged? = null

    companion object {
        @JvmStatic
        fun newInstance(launchTitle: String) =
            LaunchDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(LAUNCH_TITLE, launchTitle)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            launchTitle = it.getString(LAUNCH_TITLE)
        }
        launch = SLRealm.findSpaceXLaunchByName(launchTitle)
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
        val launchLaunchpad: TextView = view.findViewById(R.id.launch_launchpad)
        val launchLocation: TextView = view.findViewById(R.id.launch_location)
        val launchDetails: TextView = view.findViewById(R.id.launch_details)
        val goOnTheMapButton: MaterialButton = view.findViewById(R.id.go_on_the_map)
        val launchpad: SpaceXLaunchpadManaged = SLRealm.findSpaceXLaunchpadByID(launch?.launchpad)

        launchTitleTextView.text = launch?.name
        launchDate.text = getString(R.string.date, launch?.date_local?.subSequence(0, 10))
        launchCompany.text = getString(R.string.spacex)
        launchLocation.text = getString(R.string.location, launchpad.locality, launchpad.region)
        launchLaunchpad.text = getString(R.string.launchpad, launchpad.name)

        if (launch?.details != null)
            launchDetails.text = getString(R.string.details, launch?.details)
        else
            launchDetails.text = getString(R.string.details, "No details available yet.")

        goOnTheMapButton.setOnClickListener {
            val bottomNavigationView: BottomNavigationView =
                requireActivity().findViewById(R.id.bottom_navigation)
            bottomNavigationView.selectedItemId = R.id.map_screen

            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment, MapFragment.newInstance(launchpad.id!!))
                .addToBackStack(null)
                .setTransition(TRANSIT_FRAGMENT_FADE)
                .commit()
        }
    }
}
