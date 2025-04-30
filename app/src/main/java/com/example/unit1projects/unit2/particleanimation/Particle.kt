package com.example.unit1projects.unit2.particleanimation

import android.graphics.Paint

data class Particle(
    var x: Float,
    var y: Float,
    var velocityX: Float,
    var velocityY: Float,
    val paintParticle: Paint,
    var radius: Float = 10f
)
