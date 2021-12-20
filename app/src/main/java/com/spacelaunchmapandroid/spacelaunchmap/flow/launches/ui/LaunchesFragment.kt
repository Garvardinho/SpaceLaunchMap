package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spacelaunchmapandroid.spacelaunchmap.R
import com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data.LaunchListSource
import com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data.LaunchListSourceAdapter
import com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data.SLLaunchesFragment

class LaunchesFragment : Fragment(), SLLaunchesFragment {

    private lateinit var recyclerView: RecyclerView
    val data: LaunchListSource = LaunchListSourceAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_launches, container, false)
        recyclerView = view.findViewById(R.id.launches)

        initList()
        return view
    }

    override fun initList() {
        val layoutManager = LinearLayoutManager(context)
        val adapter = LaunchAdapter(data)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : LaunchAdapter.OnMoreInfoButtonClickListener {
            override fun onMoreInfoButtonClick(v: View, position: Int) {
                openDetailsFragment(data.getCardData(position).title)
            }
        })
    }

    private fun openDetailsFragment(launchTitle: String) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.main_fragment,
                LaunchDetailsFragment.newInstance(launchTitle))
            .addToBackStack(null)
            .setTransition(TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}