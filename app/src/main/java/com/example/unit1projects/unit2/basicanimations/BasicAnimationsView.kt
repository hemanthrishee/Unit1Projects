package com.example.unit1projects.unit2.basicanimations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import kotlin.math.abs

class BasicAnimationsView(context: Context): View(context) {
    private val circlePaint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    private var circleX = 0f
    private var circleY = 500f
    private var radius = 100f
    private var fps = 10L

    private var move = 10

    private val animation = object: Runnable {
        override fun run() {
            circleX += move
            if (circleX + radius >= width) {
                circlePaint.color = Color.argb(255, (0..255).random(), (0..255).random(), (0..255).random())
                move = -(abs(move).toInt() + 2)
            }
            if (circleX - radius <= 0) {
                circlePaint.color = Color.argb(255, (0..255).random(), (0..255).random(), (0..255).random())
                move = abs(move).toInt() + 2
            }
            invalidate()
            postDelayed(this, fps)
        }
    }

    init {
        postDelayed(animation, fps)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(circleX, circleY, radius, circlePaint)
    }
}