package com.example.unit1projects.unit4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unit1projects.R

class MainActivity : AppCompatActivity() {
    private lateinit var accelerometerLink: Button
    private lateinit var gyroscopeLink: Button
    private lateinit var magnetometerLink: Button
    private lateinit var lightSensorLink: Button
    private lateinit var proximitySensorLink: Button
    private lateinit var ambientLightSensorLink: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        accelerometerLink = findViewById(R.id.accelerometerLink)
        accelerometerLink.setOnClickListener {
            val intent = Intent(this, AccelerometerActivity::class.java)
            startActivity(intent)
        }

        gyroscopeLink = findViewById(R.id.gyroscopeLink)
        gyroscopeLink.setOnClickListener {
            val intent = Intent(this, GyroscopeActivity::class.java)
            startActivity(intent)
        }

        magnetometerLink = findViewById(R.id.magnetometerLink)
        magnetometerLink.setOnClickListener {
            val intent = Intent(this, MagnetometerActivity::class.java)
            startActivity(intent)
        }

        proximitySensorLink = findViewById(R.id.proximitySensorLink)
        proximitySensorLink.setOnClickListener {
            val intent = Intent(this, ProximitySensorActivity::class.java)
            startActivity(intent)
        }
    }
}