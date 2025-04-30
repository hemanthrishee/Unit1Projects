package com.example.unit1projects.unit2.propertyanimations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout

class PropertyAnimationView(context: Context): FrameLayout(context) {
    private val circle: Paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL
    }

    private val circleView: CircleView = CircleView(context).apply {
        layoutParams = FrameLayout.LayoutParams(100, 100)
    }

    init {
        this.addView(circleView)
    }

    private var x: Float = 0f
    private var y: Float = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        x = width / 2f
        y = height / 2f

        circleView.x = x
        circleView.y = y
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(x, y, 50f, circle)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val targetX = event?.x
        val targetY = event?.y

        ValueAnimator.ofFloat(x, targetX!!).apply {
            duration = 1000
            addUpdateListener { animator ->
                x = animator.animatedValue as Float
                invalidate()
            }
            start()
        }

        // Animate Y
        ValueAnimator.ofFloat(y, targetY!!).apply {
            duration = 1000
            addUpdateListener { animator ->
                y = animator.animatedValue as Float
                invalidate()
            }
            start()
        }

        return super.onTouchEvent(event)
    }
}