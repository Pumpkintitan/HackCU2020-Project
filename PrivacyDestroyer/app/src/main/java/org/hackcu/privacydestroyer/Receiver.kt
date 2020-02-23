package org.hackcu.privacydestroyer

import android.content.Context
<<<<<<< HEAD
import android.widget.TextView
=======
import android.util.Log
import com.android.volley.Request
>>>>>>> 8f7f13b52424fdf2ae151825004d080d6be5dca5
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import io.radar.sdk.Radar
import io.radar.sdk.RadarReceiver
import io.radar.sdk.model.RadarEvent
import io.radar.sdk.model.RadarUser

fun sendLocation(activity: MainActivity, message: String?) {
<<<<<<< HEAD
    val url = "http://159.69.156.248:5002/" + message
    println(url)

    val textView = activity.findViewById<TextView>(R.id.text) ?: return
=======
    val url = "http://159.69.156.248:5002/" + URLEncoder.encode(message, "UTF-8")
    //val textView = activity.findViewById<TextView>(R.id.text) ?: return
>>>>>>> 8f7f13b52424fdf2ae151825004d080d6be5dca5

    // Request a string response from the provided URL.
    val stringRequest = StringRequest(
        0, url,
        Response.Listener<String> { response ->
            // Display the first 500 characters of the response string.
            println("We made it!")
            textView.text = "Response is: ${response.substring(0, 500)}"
        },
<<<<<<< HEAD
        Response.ErrorListener { textView.text = "That didn't work!" })
=======
        Response.ErrorListener { error ->
            Log.e("Volley Error", error.toString())
            //textView.text = "That didn't work!"
        })
>>>>>>> 8f7f13b52424fdf2ae151825004d080d6be5dca5


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