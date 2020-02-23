package org.hackcu.privacydestroyer

import android.os.HandlerThread
import io.radar.sdk.Radar

class Updater(activity: MainActivity) : HandlerThread("Updater") {
    private var activity: MainActivity? = null

    override fun run() {
        while (true) {
            var count = 0
            Radar.trackOnce { status, location, events, user ->
                user?.geofences?.forEach { geofence ->
                    count++
                    sendLocation(activity as MainActivity, geofence.tag)
                }
            }

            if (count == 0) {
                sendLocation(activity as MainActivity, "Nowhere!")
            }

            Thread.sleep(20 * 1000)
        }
    }

    init {
        this.activity = activity
    }
}