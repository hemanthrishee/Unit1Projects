package com.example.unit1projects.unit1.emailPasswordFirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unit1projects.R
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var emailField: EditText
    private lateinit var passwordField: EditText
    private lateinit var signUpButton: Button
    private lateinit var loginActivityLink: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        emailField = findViewById(R.id.signUpEmailField)
        passwordField = findViewById(R.id.signUpPasswordField)
        signUpButton = findViewById(R.id.emailPasswordSignupButton)
        loginActivityLink = findViewById(R.id.linkToLogin)

        auth = FirebaseAuth.getInstance()

        loginActivityLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        signUpButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(this, LoginActivity::class.java)
                    Toast.makeText(this, "Successfully signed up, proceed to login", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}