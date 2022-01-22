package com.example.snake

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Snake {

    var body = mutableListOf<Pair<Int, Int>>(Pair(7,7))
    var smer = 1 // 0 - vlavo 1 - hore 2 - vpravo 3 - dole

    fun check() {

    }

    fun onDraw(canvas: Canvas, sizeOfRect: Float, apple: Apple, maxX: Int, maxY: Int): Boolean {
        //choose a color
        val mPaint = Paint()
        mPaint.color = Color.BLACK

        //generate next header
        var tmp = body[0]
        val head = generateNext(body[0])

        //checkCrash
        if (onCheckCrash(head, maxX, maxY)) {
            canvas.drawCircle((body[0].first * sizeOfRect) + sizeOfRect/2, (body[0].second * sizeOfRect) + sizeOfRect/2, sizeOfRect/2, mPaint)
            for (i in 1..body.size - 1) {
                canvas.drawRect(body[i].first * sizeOfRect, body[i].second * sizeOfRect,
                    (body[i].first + 1) * sizeOfRect, (body[i].second + 1) * sizeOfRect, mPaint)
            }
            return false
        }

        //generate new apple if
        var new = false
        if (onCheckApple(head, apple.poz)) {
            new = true
            apple.generateApple(body, maxX, maxY)
        }

        //redraw snake
        body[0] = head
        canvas.drawCircle((body[0].first * sizeOfRect) + sizeOfRect/2, (body[0].second * sizeOfRect) + sizeOfRect/2, sizeOfRect/2, mPaint)

        for (i in 1..body.size - 1) {
            val tmp1 = body[i]
            body[i] = tmp
            tmp = tmp1
            canvas.drawRect(body[i].first * sizeOfRect, body[i].second * sizeOfRect,
                (body[i].first + 1) * sizeOfRect, (body[i].second + 1) * sizeOfRect, mPaint)
        }
        if (new) {
            body.add(tmp)
            val size = body.size - 1
            canvas.drawRect(body[size].first * sizeOfRect, body[size].second * sizeOfRect,
                (body[size].first + 1) * sizeOfRect, (body[size].second + 1) * sizeOfRect, mPaint)
        }

        return true
    }

    fun onLeft() {
        if (smer == 0) smer = 3 else smer -= 1
    }


    fun onRight() {
        smer = (smer + 1) % 4
    }

    fun onCheckApple(head: Pair<Int, Int>, applePoz: Pair<Int, Int>): Boolean {
        if (head.first == applePoz.first && head.second == applePoz.second) {
            return true
        }
        return false
    }

    fun generateNext(head: Pair<Int, Int>): Pair<Int, Int> {
        when (smer) {
            0 -> return Pair(head.first - 1, head.second)
            1 -> return  Pair(head.first, head.second - 1)
            2 -> return Pair(head.first + 1, head.second)
            3 -> return Pair(head.first, head.second + 1)

            //ak sa sem dostanem musela nastat chyba
            else -> return Pair(0, 0)
        }

    }

    fun onCheckCrash(head: Pair<Int, Int>, maxX: Int, maxY: Int): Boolean {
        if (head.first < 0 || head.first > maxX || head.second < 0 || head.second > maxY) return true
        //pridat crash na hada
        if (body.contains(head)) return true
        return false
    }
}