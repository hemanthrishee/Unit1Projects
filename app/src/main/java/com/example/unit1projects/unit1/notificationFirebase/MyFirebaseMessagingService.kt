package com.example.unit1projects.unit1.notificationFirebase

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {
//    override fun onMessageReceived(message: RemoteMessage) {
//        super.onMessageReceived(message)
//
//        message.notification?.let {
//            showNotificationMessage(it.title?: "", it.body?: "")
//        }
//    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Toast.makeText(this, "Refreshed token: $token", Toast.LENGTH_SHORT).show()
    }

//    private fun showNotificationMessage(title: String, message: String) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
//            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//            return  // 🔒 Permission not granted — skip showing the notification
//        }
//        val builder = NotificationCompat.Builder(this)
//            .setContentTitle(title)
//            .setContentText(message)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setSmallIcon(android.R.drawable.ic_dialog_info)
//
//        val notificationManager = NotificationManagerCompat.from(this)
//        notificationManager.notify(1, builder.build())
//    }
}