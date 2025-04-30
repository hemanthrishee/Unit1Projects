package com.example.unit1projects.unit2.particleanimation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class ParticleAnimationView(context: Context): View(context) {
    private val particles: MutableList<Particle> = mutableListOf()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        for (i in 0..10) {
            val particle = Particle(
                (0..width).random().toFloat(),
                (0..height).random().toFloat(),
                (10..20).random().toFloat(),
                (10..20).random().toFloat(),
                Paint().apply {
                    color = Color.argb(
                        255,
                        (0..255).random(),
                        (0..255).random(),
                        (0..255).random()
                    )
                    style = Paint.Style.FILL
                }
            )
            particles.add(particle)
        }
    }

    private val animation = object: Runnable {
        override fun run() {
            for (particle in particles) {
                particle.x += particle.velocityX
                particle.y += particle.velocityY

                if (particle.x + particle.radius >= width || particle.x - particle.radius <= 0) {
                    particle.velocityX *= -1
                }
                if (particle.y + particle.radius >= height || particle.y - particle.radius <= 0) {
                    particle.velocityY *= -1
                }
            }
            invalidate()
            postDelayed(this, 10)
        }
    }

    init {
        postDelayed(animation, 10)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.argb(40, 0, 0, 0))

        for (particle in particles) {
            canvas.drawCircle(particle.x, particle.y, particle.radius, particle.paintParticle)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var x = event?.x
        var y = event?.y

        if (x != null && y != null) {
            val particle = Particle(
                x,
                y,
                0f,
                0f,
                Paint().apply {
                    color = Color.argb(
                        255,
                        (0..255).random(),
                        (0..255).random(),
                        (0..255).random()
                    )
                    style = Paint.Style.FILL
                },
                50f
            )
            particles.add(particle)
            invalidate()
            postDelayed({
                particle.velocityX = (10..20).random().toFloat()
                particle.velocityY = (10..20).random().toFloat()
                particle.radius = 10f
            }, 500)
        }
        return super.onTouchEvent(event)
    }
}