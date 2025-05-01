package com.example.unit1projects.unit2.animations

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unit1projects.R

class AnimationsActivity : AppCompatActivity() {
    private lateinit var animationImage: ImageView
    private lateinit var blinkButton: Button
    private lateinit var rotateButton: Button
    private lateinit var fadeButton: Button
    private lateinit var moveButton: Button
    private lateinit var slideButton: Button
    private lateinit var zoomButton: Button
    private lateinit var stopButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_animations)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        animationImage = findViewById(R.id.animationsImage)

        blinkButton = findViewById(R.id.blinkButton)
        rotateButton = findViewById(R.id.rotateButton)
        fadeButton = findViewById(R.id.fadeButton)
        moveButton = findViewById(R.id.moveButton)
        slideButton = findViewById(R.id.slideButton)
        zoomButton = findViewById(R.id.zoomButton)

        stopButton  = findViewById(R.id.stopAnimationButton)

        blinkButton.setOnClickListener{
            val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.blink)
            animationImage.startAnimation(animation)
        }
        rotateButton.setOnClickListener{
            val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
            animationImage.startAnimation(animation)
        }
        fadeButton.setOnClickListener{
            val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.fade)
            animationImage.startAnimation(animation)
        }
        moveButton.setOnClickListener{
            val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.move)
            animationImage.startAnimation(animation)
        }
        slideButton.setOnClickListener{
            val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.slide)
            animationImage.startAnimation(animation)
        }
        zoomButton.setOnClickListener{
            val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.zoom)
            animationImage.startAnimation(animation)
        }
        stopButton.setOnClickListener{
            animationImage.clearAnimation()
        }
    }
}