package com.example.unit1projects.unit2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unit1projects.R
import com.example.unit1projects.unit2.animations.AnimationsActivity
import com.example.unit1projects.unit2.basicanimations.BasicAnimationsActivity
import com.example.unit1projects.unit2.canvasbasics.CanvasBasics
import com.example.unit1projects.unit2.particleanimation.ParticleAnimationActivity
import com.example.unit1projects.unit2.propertyanimations.PropertyAnimationActivity
import com.example.unit1projects.unit2.transitions.TransitionStartActivity

class MainActivity : AppCompatActivity() {
    private lateinit var canvasBasicsLink: Button
    private lateinit var basicAnimationsLink: Button
    private lateinit var particleAnimationsLink: Button
    private lateinit var propertyAnimationsLink: Button
    private lateinit var animationsLink: Button
    private lateinit var transitionsLink: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        canvasBasicsLink = findViewById(R.id.canvasBasicsLink)
        canvasBasicsLink.setOnClickListener {
            val intent = Intent(this, CanvasBasics::class.java)
            startActivity(intent)
        }

        basicAnimationsLink = findViewById(R.id.basicAnimationsLink)
        basicAnimationsLink.setOnClickListener {
            val intent = Intent(this, BasicAnimationsActivity::class.java)
            startActivity(intent)
        }

        particleAnimationsLink = findViewById(R.id.particleAnimationsLink)
        particleAnimationsLink.setOnClickListener {
            val intent = Intent(this, ParticleAnimationActivity::class.java)
            startActivity(intent)
        }

        propertyAnimationsLink = findViewById(R.id.propertyAnimationLink)
        propertyAnimationsLink.setOnClickListener {
            val intent = Intent(this, PropertyAnimationActivity::class.java)
            startActivity(intent)
        }

        animationsLink = findViewById(R.id.animationsLink)
        animationsLink.setOnClickListener {
            val intent = Intent(this, AnimationsActivity::class.java)
            startActivity(intent)
        }

        transitionsLink = findViewById(R.id.transitionsLink)
        transitionsLink.setOnClickListener {
            val intent = Intent(this, TransitionStartActivity::class.java)
            startActivity(intent)
        }
    }
}