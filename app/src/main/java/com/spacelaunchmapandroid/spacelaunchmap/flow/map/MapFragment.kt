package com.spacelaunchmapandroid.spacelaunchmap.flow.map

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.spacelaunchmapandroid.spacelaunchmap.R
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.*
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.ui_view.ViewProvider
import org.w3c.dom.Text

class MapFragment : Fragment(), SLMapView {

    private lateinit var mapView: MapView
    private lateinit var presenter: SLMapControllerOutput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("ef270c17-6822-47e6-899a-58269d47526b")
        MapKitFactory.initialize(requireContext())
        presenter = Presenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = view.findViewById(R.id.map_view)
        mapView.map.move(
            CameraPosition(Point(28.396837, -80.605659), 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0f),
            null
        )
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun showPlaceMarks() {
        val launchpadCoordinates = presenter.getLaunchpadCoordinates()
        for (point in launchpadCoordinates) {
            mapView.map.mapObjects.addPlacemark(point).setIcon(ImageProvider.
            fromResource(requireContext(), R.drawable.placemark))
        }
    }
}
