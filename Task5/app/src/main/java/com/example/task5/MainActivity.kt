package com.example.task5

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DrawView(this))
    }
}

class DrawView(context: Context) : View(context) {
    private var paint: Paint = Paint()
    private var smileBox: RectF = RectF()

    override fun onDraw(canvas: Canvas) {
        canvas.drawRGB(123, 345, 234)
        val hCenter: Float = 0.5f * height
        val wCenter: Float = 0.5f * width
        paint.strokeWidth = 3f
        paint.style = Paint.Style.FILL
        paint.color = Color.YELLOW
        canvas.drawCircle(wCenter, hCenter, 200f, paint)
        paint.color = Color.WHITE
        canvas.drawCircle(wCenter - 100, hCenter - 50, 50f, paint)
        canvas.drawCircle(wCenter + 100, hCenter - 50, 50f, paint)
        paint.color = Color.BLACK
        canvas.drawCircle(wCenter - 100, hCenter - 50, 20f, paint)
        canvas.drawCircle(wCenter + 100, hCenter - 50, 20f, paint)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        canvas.drawLine(wCenter - 120, hCenter - 125, wCenter - 40, hCenter - 65, paint)
        canvas.drawLine(wCenter + 120, hCenter - 125, wCenter + 40, hCenter - 65, paint)
        smileBox.left = wCenter - 80
        smileBox.right = wCenter + 80
        smileBox.top = hCenter + 20
        smileBox.bottom = hCenter + 40
        canvas.drawArc(smileBox, 0f, 180f, false, paint)
    }

}