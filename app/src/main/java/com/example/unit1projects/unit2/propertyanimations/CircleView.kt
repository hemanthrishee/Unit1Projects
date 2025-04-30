package com.example.unit1projects.unit2.propertyanimations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class CircleView(context: Context): View(context) {
    private val circlePaint: Paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2
        val centerY = height / 2
        val radius = Math.min(width, height) / 2
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius.toFloat(), circlePaint)
    }
}