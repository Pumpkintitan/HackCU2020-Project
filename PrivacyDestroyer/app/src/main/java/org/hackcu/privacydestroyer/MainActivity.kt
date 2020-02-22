package org.hackcu.privacydestroyer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.Manifest
import android.os.Build
import androidx.core.app.ActivityCompat
import io.radar.sdk.Radar
import io.radar.sdk.RadarTrackingOptions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(Radar.layout.activity_main)

        // request permissions
        if (Build.VERSION.SDK_INT >= 23) {
            val requestCode = 0
            if (Build.VERSION.SDK_INT >= 29) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION), requestCode)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), requestCode)
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
    }

}