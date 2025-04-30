package com.example.unit1projects

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unit1projects.unit1.MainActivity

class MainActivity : AppCompatActivity() {
    private lateinit var unit1Button: Button
    private lateinit var unit2Button: Button
    private lateinit var unit3Button: Button
    private lateinit var unit4Button: Button
    private lateinit var unit5Button: Button
    private lateinit var unit6Button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        unit1Button = findViewById(R.id.unit1Link)
        unit1Button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        unit2Button = findViewById(R.id.unit2Link)
        unit2Button.setOnClickListener {
            val intent = Intent(this, com.example.unit1projects.unit2.MainActivity::class.java)
            startActivity(intent)
        }

        unit3Button = findViewById(R.id.unit3Link)
        unit3Button.setOnClickListener {
            val intent = Intent(this, com.example.unit1projects.unit3.MainActivity::class.java)
            startActivity(intent)
        }

        unit4Button = findViewById(R.id.unit4Link)
        unit4Button.setOnClickListener {
            val intent = Intent(this, com.example.unit1projects.unit4.MainActivity::class.java)
            startActivity(intent)
        }

        unit5Button = findViewById(R.id.unit5Link)
        unit5Button.setOnClickListener {
            val intent = Intent(this, com.example.unit1projects.unit5.MainActivity::class.java)
            startActivity(intent)
        }

        unit6Button = findViewById(R.id.unit6Link)
        unit6Button.setOnClickListener {
            val intent = Intent(this, com.example.unit1projects.unit6.MainActivity::class.java)
            startActivity(intent)
        }
    }
}