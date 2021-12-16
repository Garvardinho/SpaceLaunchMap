package com.spacelaunchmapandroid.spacelaunchmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import com.spacelaunchmapandroid.spacelaunchmap.flow.map.MapFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment, MapFragment())
            .addToBackStack(null)
            .setTransition(TRANSIT_FRAGMENT_FADE)
            .commit()
    }
}