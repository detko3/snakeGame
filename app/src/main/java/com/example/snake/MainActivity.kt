package com.example.snake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.snake.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var paused = false
    var gameOver = false
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.gameButton.setOnClickListener {
            paused = !paused
            if (paused) {
                binding.gameButton.text = resources.getString(R.string.cont)
                timer.cancel()
            }
            else {
                binding.gameButton.text = resources.getString(R.string.pause)
                timer.start()
            }
        }

        binding.newG.setOnClickListener {
            binding.apply {
                playground.score = 0
                playground.s = null
                playground.a = null
                playground.p = null
                gameOver.visibility = View.GONE
                score.text = "Score: 0"
            }
            gameOver = false
            timer.start()
        }
    }

     val timer = object : CountDownTimer(1000000000, 500) {
        override fun onFinish() {

//            Toast.makeText(this@MainActivity, "Game over", Toast.LENGTH_LONG).show()
//            finish()
            start()
        }

        override fun onTick(p0: Long) {
//            Log.d("TICK", "${p0}")
            if (!gameOver) {
                runOnUiThread {
                    binding.playground.invalidate()

                }
            } else {
                binding.gameOver.visibility = View.VISIBLE
                cancel()
            }
        }
    }.start()

    fun setGameO() {
        gameOver = true

    }

    fun setScore(score: Int) {
        binding.score.text = "Score: ${score}"
    }
}