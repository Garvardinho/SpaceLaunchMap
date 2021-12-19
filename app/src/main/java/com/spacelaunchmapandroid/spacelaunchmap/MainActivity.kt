package com.spacelaunchmapandroid.spacelaunchmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import com.spacelaunchmapandroid.spacelaunchmap.flow.map.MapFragment
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
    }
}