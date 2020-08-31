package com.arjun.androidapprentice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var gameScoreTextView: TextView
    lateinit var timeLeftTextView: TextView
    lateinit var tapmeButton: Button
    private var score = 0
    var gameStarted = false
    lateinit var countDownTimer: CountDownTimer
    var initialCountDown: Long = 60000
    var countDownInterval: Long = 1000
    var timeLeft = 60

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameScoreTextView = findViewById(R.id.game_score_text_view)
        timeLeftTextView = findViewById(R.id.time_left_text_view)
        tapmeButton = findViewById(R.id.tap_me_button)

        tapmeButton.setOnClickListener {
            incrementScore()
        }

        resetGame()
    }

    private fun incrementScore() {
        if (!gameStarted) {
            startGame()
        }
        score++
        val newScore = "Your Score:$score"
        gameScoreTextView.text = newScore

    }


    private fun resetGame() {
        score = 0;
        val initialScore = getString(R.string.your_score, score)
        gameScoreTextView.text = initialScore


        val initialTimeLeft = getString(R.string.time_left_1_d, 60)
        timeLeftTextView.text = initialTimeLeft


        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onFinish() {
                endGame()
            }

            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished.toInt() / 1000
                val timeLeftString = getString(R.string.time_left_1_d, timeLeft)
                timeLeftTextView.text = timeLeftString
            }

        }
        gameStarted = false
    }

    private fun startGame() {
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame() {
        Toast.makeText(this, getString(R.string.game_over_message, score), Toast.LENGTH_LONG).show()
        resetGame()
    }
}