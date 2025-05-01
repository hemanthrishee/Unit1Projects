package com.example.unit1projects.unit5.wifi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unit1projects.R

class WifiActivity : AppCompatActivity() {
    private lateinit var showWifiButton: Button
    private lateinit var wifiList: ListView

    private lateinit var wifiAdapter: ArrayAdapter<String>
    private val wifis = mutableListOf<String>()

    private lateinit var wifiManager: WifiManager

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            startScanning()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private val receiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val action = intent?.action
            if (action == WifiManager.SCAN_RESULTS_AVAILABLE_ACTION) {
                if (
                    ContextCompat.checkSelfPermission(this@WifiActivity, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                ) {
                    val results = wifiManager.scanResults
                    wifis.clear()
                    for (result in results) {
                        wifis.add("${result.SSID} - ${result.level}")
                    }
                    wifiAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_wifi)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showWifiButton = findViewById(R.id.showWifi)
        wifiList = findViewById(R.id.wifiList)

        wifiAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, wifis)
        wifiList.adapter = wifiAdapter

        wifiManager = getSystemService(WIFI_SERVICE) as WifiManager

        val filter = IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        registerReceiver(receiver, filter)

        showWifiButton.setOnClickListener {
            if (checkPermissions()) {
                startScanning()
            } else {
                requestPermissions()
            }
        }
    }

    private fun requestPermissions() {
        permissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun startScanning() {
        wifiManager.startScan()
        wifis.clear()
        Toast.makeText(this, "Scanning...", Toast.LENGTH_SHORT).show()
    }

    private fun checkPermissions(): Boolean {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
}