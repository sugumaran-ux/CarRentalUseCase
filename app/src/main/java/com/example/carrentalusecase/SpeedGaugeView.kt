package com.example.carrentalusecase

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

class SpeedGaugeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var currentSpeed = 0f
    private val maxSpeed = 180f

    private val arcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 20f
        color = Color.GRAY
    }

    private val needlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 8f
        color = Color.RED
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 64f
        color = Color.WHITE
        textAlign = Paint.Align.CENTER
    }

    fun setSpeed(speed: Float) {
        currentSpeed = speed.coerceIn(0f, maxSpeed)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val padding = 60f
        val radius = (width.coerceAtMost(height) / 2f) - padding
        val centerX = width / 2f
        val centerY = height.toFloat()

        // Draw semicircle (gauge)
        val oval = RectF(centerX - radius, centerY - 2 * radius, centerX + radius, centerY)
        canvas.drawArc(oval, 180f, 180f, false, arcPaint)

        // Draw needle
        val angle = 180f * (currentSpeed / maxSpeed) + 180f
        val needleX = (centerX + radius * cos(Math.toRadians(angle.toDouble()))).toFloat()
        val needleY = (centerY + radius * sin(Math.toRadians(angle.toDouble()))).toFloat()
        canvas.drawLine(centerX, centerY, needleX, needleY, needlePaint)

        // Draw speed text
        canvas.drawText("${currentSpeed.toInt()} km/h", centerX, centerY - radius - 20f, textPaint)
    }
}

