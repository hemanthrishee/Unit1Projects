package com.example.unit1projects.unit5.wifi

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.unit1projects.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import kotlin.system.measureTimeMillis

class WifiSpeedTestActivity : AppCompatActivity() {
    private lateinit var wifiTestButton: Button
    private lateinit var testResults: TextView
    private lateinit var wifiTestProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_wifi_speed_test)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        wifiTestButton = findViewById(R.id.wifiTest)
        testResults = findViewById(R.id.testResults)
        wifiTestProgressBar = findViewById(R.id.wifiTestProgressBar)

        wifiTestButton.setOnClickListener {
            wifiTestProgressBar.visibility = ProgressBar.VISIBLE
            testSpeed()
        }
    }

    private fun testSpeed() {
        lifecycleScope.launch(Dispatchers.IO) {
            val url = URL("https://nbg1-speed.hetzner.com/100MB.bin")
            val connection = url.openConnection() as HttpsURLConnection
            connection.connectTimeout = 10000
            connection.readTimeout = 10000

            var totalBytesRead = 0L
            val buffer = ByteArray(1024)
            var inputStream = connection.inputStream

            val timeTaken = measureTimeMillis {
                var bytesRead: Int
                while (inputStream.read(buffer).also {bytesRead = it} != -1 && totalBytesRead < 5 * 1024 * 1024) {
                    totalBytesRead += bytesRead
                }
            }

            val downloadSpeed = (totalBytesRead * 8) / (timeTaken / 1000) / (1024 * 1024)

            runOnUiThread {
                testResults.text = "Download speed: $downloadSpeed Mbps"
                wifiTestProgressBar.visibility = ProgressBar.INVISIBLE
            }
        }
    }
}