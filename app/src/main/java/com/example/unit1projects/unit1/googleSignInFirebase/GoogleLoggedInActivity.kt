package com.example.unit1projects.unit1.googleSignInFirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unit1projects.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class GoogleLoggedInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    private lateinit var welcomeText: TextView

    private lateinit var signOutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_google_logged_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!

        welcomeText = findViewById(R.id.googleSignInWelcomeText)
        welcomeText.text = user.displayName

        signOutButton = findViewById(R.id.googleSignoutButton)
        signOutButton.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, GoogleSignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}