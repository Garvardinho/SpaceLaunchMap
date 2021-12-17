package com.spacelaunchmapandroid.spacelaunchmap.flow.map

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.spacelaunchmapandroid.spacelaunchmap.R
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.*
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.ui_view.ViewProvider

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
        val launchpadInfoPanel: CardView =
            View.inflate(requireContext(), R.layout.launchpad_info_panel, null) as CardView

        for (point in launchpadCoordinates) {
            val launchpadTitle = launchpadInfoPanel.findViewById<TextView>(R.id.launchpad_title)
            launchpadTitle.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            launchpadTitle.text = point.key

            val placemark = mapView.map.mapObjects.addPlacemark(point.value, ImageProvider.
                fromResource(requireContext(), R.drawable.placemark))
            val infopanelMapObject = mapView.map.mapObjects.addPlacemark(point.value,
                ViewProvider(launchpadInfoPanel))

            infopanelMapObject.isVisible = false
            placemark.addTapListener { _, _ ->
                placemark.isVisible = false
                infopanelMapObject.isVisible = true
                false
            }

            infopanelMapObject.addTapListener { _, _ ->
                placemark.isVisible = true
                infopanelMapObject.isVisible = false
                false
            }
        }
    }
}
