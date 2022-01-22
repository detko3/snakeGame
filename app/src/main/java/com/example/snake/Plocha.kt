package com.example.snake

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log

class Plocha() {

    fun onDraw(canvas: Canvas, sizeOfRect: Float, maxX: Int, maxY: Int) {
        val mPaint = Paint()
        mPaint.color = Color.parseColor("#906090")

        for (j in 0..maxY) {
            for (i in 0..maxX) {
                if ((i + j) % 2 == 0) mPaint.color = Color.parseColor("#177a29") else mPaint.color = Color.parseColor("#18a332")
//                Log.d("PLOCHA ", "${i + j % 2}")
                canvas.drawRect(i * sizeOfRect, j * sizeOfRect,
                    (i + 1) * sizeOfRect, (j + 1) * sizeOfRect, mPaint)
            }
        }
    }
}