package com.example.snake

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.random.Random

class Apple {

    var poz: Pair<Int, Int> = Pair(1,1)
    var taken = false

    fun onDraw(canvas: Canvas, sizeOfRect: Float) {
        val mPaint = Paint()
        mPaint.color = Color.RED
        canvas.drawCircle(((poz.first*sizeOfRect) + sizeOfRect/2), ((poz.second*sizeOfRect) + sizeOfRect/2), (sizeOfRect/4), mPaint)
    }

    fun generateApple(snake: MutableList<Pair<Int, Int>>, maxX:Int, maxY: Int) {
        val myList = mutableListOf<Pair<Int, Int>>()
        for(j in 0..maxY) {
            for (i in 0..maxX) {
                if (!snake.contains(Pair(i, j))) {
                    myList.add(Pair(i, j))
                }
            }
        }

        taken = true
        poz = myList.random()
    }
}