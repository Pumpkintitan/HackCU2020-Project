package org.hackcu.privacydestroyer

import android.Manifest
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import io.radar.sdk.Radar
import io.radar.sdk.RadarTrackingOptions

class MainActivity : AppCompatActivity() {
    var queue: RequestQueue? = null
    val rinst = Receiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        queue = Volley.newRequestQueue(this)
        setContentView(R.layout.activity_main)

        registerReceiver(rinst, IntentFilter("io.radar.sdk.RECEIVED"))
        rinst.setActivity(this)

        // Instantiate the cache
        val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        val network = BasicNetwork(HurlStack())

        // Instantiate the RequestQueue with the cache and network. Start the queue.
        queue = RequestQueue(cache, network)

        // request permissions
        if (Build.VERSION.SDK_INT >= 23) {
            val requestCode = 0
            if (Build.VERSION.SDK_INT >= 29) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                        Manifest.permission.INTERNET
                    ),
                    requestCode
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET),
                    requestCode
                )
            }
        }

        // initialize SDK (replace with your publishable API key)
        Radar.initialize("prj_live_pk_48791b61f1828d55a36fc366ad36e9bfd2f5bc32")

        // start tracking
        val trackingOptions: RadarTrackingOptions = RadarTrackingOptions.Builder()
            .priority(Radar.RadarTrackingPriority.RESPONSIVENESS)
            .offline(Radar.RadarTrackingOffline.REPLAY_STOPPED)
            .sync(Radar.RadarTrackingSync.ALL)
            .build()
        Radar.startTracking(trackingOptions)
        println("Wholly frick")
        queue?.start()
        Updater(this).start()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(rinst)
    }

}