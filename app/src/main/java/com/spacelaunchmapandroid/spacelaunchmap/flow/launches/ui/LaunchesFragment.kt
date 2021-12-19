package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spacelaunchmapandroid.spacelaunchmap.R
import com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data.LaunchListSource
import com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data.LaunchListSourceAdapter
import com.spacelaunchmapandroid.spacelaunchmap.flow.launches.data.SLLaunchesFragment

private const val LAUNCHPAD_TITLE = "LaunchesFragment.launchpadTitle"

class LaunchesFragment : Fragment(), SLLaunchesFragment {

    private var launchpadTitleToSort: String? = null
    private lateinit var recyclerView: RecyclerView
    val data: LaunchListSource = LaunchListSourceAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            launchpadTitleToSort = it.getString(LAUNCHPAD_TITLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_launches, container, false)
        recyclerView = view.findViewById(R.id.launches)
        return view
    }

    override fun initList() {
        val layoutManager = LinearLayoutManager(context)
        val adapter = LaunchAdapter(data)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : LaunchAdapter.OnItemClickListener {
            override fun onItemClick(v: View, position: Int) {
                TODO("Not yet implemented")
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(launchpadTitleToSort: String) =
            LaunchesFragment().apply {
                arguments = Bundle().apply {
                    putString(LAUNCHPAD_TITLE, launchpadTitleToSort)
                }
            }
    }
}