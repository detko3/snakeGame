package com.example.snake

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.CountDownTimer
import android.util.AttributeSet
import android.util.Log
import android.util.Size
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class Playground(internal var context: Context, attrs: AttributeSet)
    : View(context, attrs),
    View.OnKeyListener {

    var pwidth = 0
    var pheight = 0
    var size = 15
    var sizeOfRect = 0f
    var countHeight = 0
    var score = 0
    internal var p: Plocha? = null
    internal var s: Snake? = null
    internal var a: Apple? = null
    val act = context as MainActivity


    init { // inicializacia
        setBackgroundColor(Color.parseColor("#458057"))
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        pwidth = w
        pheight = h
        sizeOfRect = Math.min(w, h).toFloat() / size
        countHeight = (pheight / sizeOfRect).toInt()

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (p == null) {
            p = Plocha()
        }
        if (s == null) {
            s = Snake()
        }
        if (a == null) {
            a = Apple()
        }

        p?.onDraw(canvas, sizeOfRect, size - 1, countHeight - 1)
        if (a!!.taken) {
            score++
            act.setScore(score)
            a!!.taken = false
        }
        a?.onDraw(canvas, sizeOfRect)
       val gameO = s?.onDraw(canvas, sizeOfRect, a!!, size - 1, countHeight - 1)
        if(!gameO!!) {
            act.setGameO()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (event.x < pwidth / 2) touchLeft()
        else touchRight()

        return super.onTouchEvent(event)
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        when (keyCode) {
            else -> return false
        }
    }

    fun touchLeft() {
//        Log.d("LEFT", "left")
        s?.onLeft()
    }

    fun touchRight() {
//        Log.d("RIGHT", "right")
        s?.onRight()
    }

}