package org.hackcu.privacydestroyer

import android.content.Context
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import io.radar.sdk.Radar
import io.radar.sdk.RadarReceiver
import io.radar.sdk.model.RadarEvent
import io.radar.sdk.model.RadarUser

fun sendLocation(activity: MainActivity, message: String?) {
    val url = "http://159.69.156.248:5002/" + message
    //val textView = activity.findViewById<TextView>(R.id.text) ?: return
    println(url)

    // Request a string response from the provided URL.
    val stringRequest = StringRequest(
        Request.Method.GET, url,
        Response.Listener<String> { response ->
            // Display the first 500 characters of the response string.
            println(response.toString())
            //textView.text = "Response is: ${response.substring(0, 500)}"
        },
        Response.ErrorListener {
            println("Error!")
            //textView.text = "That didn't work!"
        })


    (activity.queue as RequestQueue).add(stringRequest)
    activity.queue?.start()
}


class Receiver : RadarReceiver() {

    private var activity: MainActivity? = null

    fun setActivity(activity: MainActivity) {
        this.activity = activity
    }

    override fun onEventsReceived(context: Context, events: Array<RadarEvent>, user: RadarUser) {
        events.forEach { event ->
            if (event.type == RadarEvent.RadarEventType.USER_ENTERED_GEOFENCE) {
                if (event.geofence?.tag != null && activity != null)
                    sendLocation(activity as MainActivity, event.geofence?.tag)
            }
        }
    }

    override fun onError(context: Context, status: Radar.RadarStatus) {
        TODO("not implemented")
    }

}