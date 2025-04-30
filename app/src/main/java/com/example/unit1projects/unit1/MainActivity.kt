package com.example.unit1projects.unit1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unit1projects.unit1.emailPasswordFirebase.LoginActivity
import com.example.unit1projects.unit1.googleSignInFirebase.GoogleSignInActivity
import com.example.unit1projects.unit1.realtimeDatabaseFirebase.DatabaseModifActivity
import com.example.unit1projects.R
import com.example.unit1projects.unit1.notificationFirebase.NotificationPermissionRequestActivity

class MainActivity : AppCompatActivity() {
    private lateinit var emailPasswordloginActivityButton: Button
    private lateinit var googleSignInButton: Button
    private lateinit var realtimeDatabase: Button
    private lateinit var notificationActivityLink: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        emailPasswordloginActivityButton = findViewById(R.id.emailPasswordLogin)
        emailPasswordloginActivityButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        googleSignInButton = findViewById(R.id.googleSignIn)
        googleSignInButton.setOnClickListener {
            val intent = Intent(this, GoogleSignInActivity::class.java)
            startActivity(intent)
        }

        realtimeDatabase = findViewById(R.id.realTimeDatabase)
        realtimeDatabase.setOnClickListener {
            val intent = Intent(this, DatabaseModifActivity::class.java)
            startActivity(intent)
        }

        notificationActivityLink = findViewById(R.id.notificationActivityLink)
        notificationActivityLink.setOnClickListener {
            val intent = Intent(this, NotificationPermissionRequestActivity::class.java)
            startActivity(intent)
        }
    }
}