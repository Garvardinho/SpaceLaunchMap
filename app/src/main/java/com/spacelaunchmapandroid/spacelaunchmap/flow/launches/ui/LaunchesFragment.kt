package com.spacelaunchmapandroid.spacelaunchmap.flow.launches.ui

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_launches, container, false)
        recyclerView = view.findViewById(R.id.launches)

        initList(null)
        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initList(stringToSort: String?) {
        val layoutManager = LinearLayoutManager(context)

        if (stringToSort != null) {
            data.sortBy(stringToSort)
        }

        val adapter = LaunchAdapter(data)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        if (stringToSort != null) {
            recyclerView.adapter?.notifyDataSetChanged()

        }

        adapter.setOnItemClickListener(object : LaunchAdapter.OnMoreInfoButtonClickListener {
            override fun onMoreInfoButtonClick(v: View, position: Int) {
                openDetailsFragment(data.getCardData(position).title)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.top_app_bar, menu)

        val searchItem: MenuItem? = menu.findItem(R.id.action_search)
        val searchManager = requireActivity()
            .getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = searchItem
            ?.actionView as SearchView

        searchView.setSearchableInfo(searchManager
            .getSearchableInfo(requireActivity().componentName))

        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(text: String?): Boolean {
                    initList(text)
                    return false
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    //initList(text)
                    return false
                }

            })
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    private fun openDetailsFragment(launchTitle: String) {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
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