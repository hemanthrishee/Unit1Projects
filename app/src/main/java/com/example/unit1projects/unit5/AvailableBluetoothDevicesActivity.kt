package com.example.unit1projects.unit5

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
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

class AvailableBluetoothDevicesActivity : AppCompatActivity() {
    private val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private lateinit var scanButton: Button
    private lateinit var devicesList: ListView

    private lateinit var devicesAdapter: ArrayAdapter<String>

    private val discoveredDevices = mutableListOf<String>()

    val mockDevices = listOf("device 1", "device 2")

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions.all { it.value }) {
            startDiscovery()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startDiscovery() {
        discoveredDevices.clear()
        devicesAdapter.notifyDataSetChanged()
        if (
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED
        ) {
            if (bluetoothAdapter.isDiscovering) {
                bluetoothAdapter.cancelDiscovery()
            }
            bluetoothAdapter.startDiscovery()
            Toast.makeText(this, "Scanning...", Toast.LENGTH_SHORT).show()
        }
    }

    private val receiver = object: BroadcastReceiver() {
        override fun onReceive(context: android.content.Context?, intent: Intent?) {
            if (
                ContextCompat.checkSelfPermission(context!!, android.Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED
            ) {
                val action = intent?.action
                if (action == BluetoothDevice.ACTION_FOUND) {
                    val device =
                        intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                    val deviceName = device?.name ?: "Unknown device"
                    val deviceAddress = device?.address ?: "Unknown address"
                    val deviceInfo = "$deviceName - $deviceAddress"
                    if (!discoveredDevices.contains(deviceInfo)) {
                        discoveredDevices.add(deviceInfo)
                        devicesAdapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_available_bluetooth_devices)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        scanButton = findViewById(R.id.scanButton)
        devicesList = findViewById(R.id.devicesList)
        devicesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, discoveredDevices)

        devicesList.adapter = devicesAdapter

        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        registerReceiver(receiver, filter)

        scanButton.setOnClickListener {
            mockDevices.forEach {
                discoveredDevices.add(it)
                devicesAdapter.notifyDataSetChanged()
            }
//            if (checkPermissions()) {
//                startDiscovery()
//            } else {
//                requestPermissions()
//            }
        }
    }

    private fun requestPermissions() {
        permissionLauncher.launch(
            arrayOf(
                android.Manifest.permission.BLUETOOTH_CONNECT,
                android.Manifest.permission.BLUETOOTH_SCAN,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
    }

    private fun checkPermissions(): Boolean {
        return listOf(
            android.Manifest.permission.BLUETOOTH_CONNECT,
            android.Manifest.permission.BLUETOOTH_SCAN,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ).all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}