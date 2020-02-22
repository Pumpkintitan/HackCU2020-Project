import io.radar.sdk.Radar
import io.radar.sdk.RadarReceiver
import io.radar.sdk.model.RadarEvent
import io.radar.sdk.model.RadarUser

class MyRadarReceiver : RadarReceiver() {

    override fun onEventsReceived(context: Context, events: Array<RadarEvent>, user: RadarUser) {
        events.forEach { event ->
            if (event.type == RadarEventType.USER_ENTERED_GEOFENCE && event.geofence?.tag == "building" && event.geofence?.externalId == "library") {
                // user entered library, show notification
            }
        }
    }

}