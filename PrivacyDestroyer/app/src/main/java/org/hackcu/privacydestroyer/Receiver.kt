package org.hackcu.privacydestroyer

import android.content.Context
import io.radar.sdk.Radar
import io.radar.sdk.RadarReceiver
import io.radar.sdk.model.RadarEvent
import io.radar.sdk.model.RadarUser
import java.net.HttpURLConnection
import java.net.URL

class Receiver : RadarReceiver() {

    override fun onEventsReceived(context: Context, events: Array<RadarEvent>, user: RadarUser) {
        events.forEach { event ->
            if (event.type == RadarEvent.RadarEventType.USER_ENTERED_GEOFENCE) {
                if (event.geofence?.tag != null)
                    sendLocation(event.geofence?.tag)
            }
        }
    }

    fun sendLocation(message: String?) {
        val url = URL("http://34.67.200.122:5000/" + message)

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"  // optional default is GET

            println("\nSent location to URL : $url; Response Code : $responseCode")

            inputStream.bufferedReader().use {

            }
        }
    }

    override fun onError(context: Context, status: Radar.RadarStatus) {
        TODO("not implemented")
    }
}