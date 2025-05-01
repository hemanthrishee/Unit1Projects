package com.example.unit1projects.unit5.bluetooth

import android.bluetooth.BluetoothAdapter
import android.content.pm.PackageManager
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

class PairedBluetoothDevicesActivity : AppCompatActivity() {
    private lateinit var pairedDevicesList: ListView
    private lateinit var showPairedDevicesButton: Button

    private lateinit var pairedDevicesAdapter: ArrayAdapter<String>

    private val pairedDevices = mutableListOf<String>()

    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            showPairedDevices()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_paired_bluetooth_devices)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showPairedDevicesButton = findViewById(R.id.showPairedDevices)
        pairedDevicesList = findViewById(R.id.pairedDevices)

        pairedDevicesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pairedDevices)
        pairedDevicesList.adapter = pairedDevicesAdapter

        showPairedDevicesButton.setOnClickListener {
            if (checkPermission()) {
                showPairedDevices()
            } else {
                requestPermission()
            }
        }
    }

    private fun requestPermission() {
        requestPermissionLauncher.launch(
            android.Manifest.permission.BLUETOOTH_CONNECT
        )
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED
    }

    private fun showPairedDevices() {
        pairedDevices.clear()
        pairedDevicesAdapter.notifyDataSetChanged()
        if (
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED
        ) {
            if (bluetoothAdapter != null) {
                val pd = bluetoothAdapter.bondedDevices
                for (device in pd) {
                    pairedDevices.add("${device.name} - ${device.address}")
                }
                pairedDevicesAdapter.notifyDataSetChanged()
            }
        }
    }
}