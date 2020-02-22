import io.radar.sdk.Radar
import io.radar.sdk.RadarReceiver
import io.radar.sdk.model.RadarEvent
import io.radar.sdk.model.RadarUser
import volley;

class MyRadarReceiver : RadarReceiver() {

    override fun onEventsReceived(context: Context, events: Array<RadarEvent>, user: RadarUser) {
        events.forEach { event ->
            if (event.type == RadarEventType.USER_ENTERED_GEOFENCE) {
                sendLocation(event.geofence?.externalid)
            }
        }
    }

    fun sendLocation(message: String) {
        val url = URL("http://34.67.200.122:5000/" + message)

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "GET"  // optional default is GET

            println("\nSent location to URL : $url; Response Code : $responseCode")

            inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                    println(line)
                }
            }
        }
    }
}