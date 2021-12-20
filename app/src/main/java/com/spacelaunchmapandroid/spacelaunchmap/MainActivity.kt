package com.spacelaunchmapandroid.spacelaunchmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.spacelaunchmapandroid.spacelaunchmap.flow.launches.ui.LaunchesFragment
import com.spacelaunchmapandroid.spacelaunchmap.flow.map.MapFragment
import com.yandex.mapkit.MapKitFactory
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity : AppCompatActivity() {

    companion object {
        fun getRealmInstance(): Realm {
            return Realm.getDefaultInstance()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("ef270c17-6822-47e6-899a-58269d47526b")
        Realm.init(this)
        Realm.setDefaultConfiguration(
            RealmConfiguration.Builder().allowWritesOnUiThread(true).build()
        )
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment, MapFragment())
            .addToBackStack(null)
            .setTransition(TRANSIT_FRAGMENT_FADE)
            .commit()

        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        val bottomNavigationView: NavigationBarView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.map_screen -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, MapFragment())
                        .addToBackStack(null)
                        .setTransition(TRANSIT_FRAGMENT_FADE)
                        .commit()
                    return@setOnItemSelectedListener true
                }

                R.id.launches_screen -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment, LaunchesFragment())
                        .addToBackStack(null)
                        .setTransition(TRANSIT_FRAGMENT_FADE)
                        .commit()
                    return@setOnItemSelectedListener true
                }
            }

            false
        }
    }
}