import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import io.radar.sdk.Radar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        Radar.trackOnce { status, location, events, user ->
            user?.geofences?.forEach { geofence ->
                if (geofence.tag == "cucampus") {
                    // user is on campus, unlock feature
                } else if (geofence.tag == "cueast") {

                } else if (geofence.tag == "cubiotech") {

                }
            }
        }
    }

    fun sendLocation() {
        val url = URL("http://34.67.200.122:5000/")

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "POST"  // optional default is GET

            println("\nSent location to URL : $url; Response Code : $responseCode")

            inputStream.bufferedReader().use {
                it.lines().forEach { line ->
                    println(line)
                }
            }
        }
    }

}