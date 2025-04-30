package com.example.unit1projects.unit2.canvasbasics

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class MyCanvasView(context: Context): View(context) {
    private val paintCircle = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    private val paintRect = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }

    private fun getRandomColorCircle() {
        paintCircle.color = Color.argb(255, (0..255).random(), (0..255).random(), (0..255).random())
    }

    private fun drawCircleGrid(startingX: Float, startingY: Float, radius: Float, canvas: Canvas) {
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val x = startingX + (radius * 2 * j)
                val y = startingY + (radius * 2 * i)
                getRandomColorCircle()
                canvas.drawCircle(x, y, radius, paintCircle)
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(200f, 500f, 100f, paintCircle)
        canvas.drawRect(400f, 400f, 600f, 600f, paintRect)

        drawCircleGrid(500f, 800f, 100f, canvas)
    }
}