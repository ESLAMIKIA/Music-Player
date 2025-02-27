package com.example.musicplayer

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

class AnimatedGradientView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint = Paint()
    private var gradient: SweepGradient? = null
    private var gradientRotation = 0f

    private val colors = intArrayOf(
        Color.parseColor("#a72fcc"),
        Color.parseColor("#ed0dfd"),
        Color.parseColor("#ea0ffd"),
        Color.parseColor("#c332f7"),
        Color.parseColor("#9857f2"),
        Color.parseColor("#6087eb"),
        Color.parseColor("#43a0e7"),
        Color.parseColor("#41a3e6"),
        Color.parseColor("#2ab6e4"),
        Color.parseColor("#19c5e1"),
        Color.parseColor("#0dd0e0"),
        Color.parseColor("#0ecfe0"),
        Color.parseColor("#14c9e1"),
        Color.parseColor("#39a9e5"),
        Color.parseColor("#5294e9"),
        Color.parseColor("#8f5ff1"),
        Color.parseColor("#c72ef8"),
        Color.parseColor("#a72fcc"),


//        Color.parseColor("#4682B4"),
//        Color.parseColor("#8A2BE2"),
//        Color.parseColor("#FF1493"),
//        Color.parseColor("#FF4500"),
//        Color.parseColor("#FF69B4"),
//        Color.parseColor("#9932CC"),
//        Color.parseColor("#0000FF"),
//        Color.parseColor("#32CD32"),
//        Color.parseColor("#FF5733"),
//        Color.parseColor("#FF8C00"),
//        Color.parseColor("#FFD700"),
//        Color.parseColor("#ADFF2F"),
//        Color.parseColor("#00FA9A"),
//        Color.parseColor("#00CED1"),
//        Color.parseColor("#4682B4"),
//        Color.parseColor("#8A2BE2"),
//        Color.parseColor("#FF1493"),
//        Color.parseColor("#FF4500"),
//        Color.parseColor("#FF69B4"),
//        Color.parseColor("#9932CC"),
//        Color.parseColor("#0000FF"),
//        Color.parseColor("#FF3333"),
    )

    init {
        startAnimation()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2.5f

        gradient = SweepGradient(centerX, centerY, colors, null)
        val matrix = Matrix()
        matrix.setRotate(gradientRotation, centerX, centerY)
        gradient?.setLocalMatrix(matrix)

        paint.shader = gradient
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
    }

    private fun startAnimation() {
        val animator = ValueAnimator.ofFloat(0f, 360f)
        animator.duration = 10000 // 1o second of rotation
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener { animation ->
            gradientRotation = animation.animatedValue as Float
            invalidate()
        }
        animator.start()
    }
}
