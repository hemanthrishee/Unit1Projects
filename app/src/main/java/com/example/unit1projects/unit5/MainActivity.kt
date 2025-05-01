package com.example.unit1projects.unit5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unit1projects.R
import com.example.unit1projects.unit5.bluetooth.AvailableBluetoothDevicesActivity
import com.example.unit1projects.unit5.bluetooth.BluetoothActivity
import com.example.unit1projects.unit5.bluetooth.PairedBluetoothDevicesActivity
import com.example.unit1projects.unit5.wifi.WifiActivity
import com.example.unit1projects.unit5.wifi.WifiSpeedTestActivity

class MainActivity : AppCompatActivity() {
    private lateinit var bluetoothLink: Button
    private lateinit var bluetoothDevicesLink: Button
    private lateinit var pairedDevicesLink: Button
    private lateinit var wifiLink: Button
    private lateinit var wifiSpeedTestLink: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main6)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        bluetoothLink = findViewById(R.id.bluetoothLink)
        bluetoothLink.setOnClickListener {
            val intent = Intent(this, BluetoothActivity::class.java)
            startActivity(intent)
        }

        bluetoothDevicesLink = findViewById(R.id.bluetoothDevicesLink)
        bluetoothDevicesLink.setOnClickListener {
            val intent = Intent(this, AvailableBluetoothDevicesActivity::class.java)
            startActivity(intent)
        }

        pairedDevicesLink = findViewById(R.id.pairedDevicesLink)
        pairedDevicesLink.setOnClickListener {
            val intent = Intent(this, PairedBluetoothDevicesActivity::class.java)
            startActivity(intent)
        }

        wifiLink = findViewById(R.id.wifiLink)
        wifiLink.setOnClickListener {
            val intent = Intent(this, WifiActivity::class.java)
            startActivity(intent)
        }

        wifiSpeedTestLink = findViewById(R.id.wifiSpeedTestLink)
        wifiSpeedTestLink.setOnClickListener {
            val intent = Intent(this, WifiSpeedTestActivity::class.java)
            startActivity(intent)
        }
    }
}