package com.example.task8

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    companion object {
        const val CLUSTER_CENTER_X: Double = 0.0
        const val CLUSTER_CENTER_Y: Double = 0.0
        const val CLUSTERS: Int = 200
        const val SQUARE_SIDE = 10
        const val LOG_TAG: String = "myLog"
    }

    inner class MyItem(
        lat: Double,
        lng: Double,
        private val title: String,
        private val snippet: String
    ) : ClusterItem {

        private val position: LatLng = LatLng(lat, lng)

        override fun getPosition(): LatLng {
            return position
        }

        override fun getTitle(): String {
            return title
        }

        override fun getSnippet(): String {
            return snippet
        }

    }

    private lateinit var gMap: GoogleMap

    private lateinit var mClusterManager: ClusterManager<MyItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(LOG_TAG, "создаем активность")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val supportMapFragment: SupportMapFragment = (supportFragmentManager.findFragmentById(R.id.GMAP)) as SupportMapFragment
        supportMapFragment.getMapAsync(this)
        Log.d(LOG_TAG, "создаем активность...............ok")
    }

    override fun onMapReady(p0: GoogleMap) {
        Log.d(LOG_TAG, "карта готова")
        gMap = p0;
        mClusterManager = ClusterManager(applicationContext, gMap)
        addItems()
        gMap.setOnCameraIdleListener(mClusterManager)
        gMap.setOnMarkerClickListener(mClusterManager)
        Log.d(LOG_TAG, "карта готова...............ok")
    }

    private fun addItems() {
        Log.d(LOG_TAG, "генерируем")
        val half = SQUARE_SIDE / 2;
        for (i in (0..CLUSTERS)) {
            val x = (-half..half).random() + CLUSTER_CENTER_X
            val y = (-half..half).random() + CLUSTER_CENTER_Y
            mClusterManager.addItem(MyItem(x, y, "title", "snippet"))

        }
        Log.d(LOG_TAG, "генерируем...............ok")
    }
}