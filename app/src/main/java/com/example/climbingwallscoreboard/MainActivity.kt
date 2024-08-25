package com.example.climbingwallscoreboard

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {

    private var score:Int = 0
    private var climbs:Int = 0

    private var isFailed: Boolean = false

    private val savedState:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.scoreboard)

        val climbBtn = findViewById<Button>(R.id.climb_btn)
        val resetBtn = findViewById<Button>(R.id.reset_btn)
        val fallBtn = findViewById<Button>(R.id.fall_btn)

        if (savedInstanceState != null) {
            val savedState = savedInstanceState.getIntArray(savedState)
            if (savedState != null && savedState.size == 3) {
                score = savedState[0]
                climbs = savedState[1]
                if (savedState[2] == 0){
                    climbBtn.text = getString(R.string.try_again)
                    isFailed = true
                    resetBtn.visibility = View.INVISIBLE
                    fallBtn.visibility = View.INVISIBLE
                }
            }
        }

        updateDashboard()
    }

    private fun updateDashboard(){
        val scoreTextView = findViewById<TextView>(R.id.score)
        val climbsCount = findViewById<TextView>(R.id.climbsCount)

        val resetBtn = findViewById<Button>(R.id.reset_btn)
        val fallBtn = findViewById<Button>(R.id.fall_btn)

        when {
            score == 0 -> {
                scoreTextView.text = getString(R.string.total_score)
            }
            score > 0 -> {
                scoreTextView.text = score.toString()
            }
        }

        when {
            isFailed -> {
                resetBtn.visibility = View.INVISIBLE
                fallBtn.visibility = View.INVISIBLE
            } else -> {
                resetBtn.visibility = View.VISIBLE
                fallBtn.visibility = View.VISIBLE
            }
        }

        climbsCount.text = climbs.toString()

        when {
            climbs <= 3 -> scoreTextView.setTextColor(ContextCompat.getColor(this, R.color.blue))
            climbs <= 6 -> scoreTextView.setTextColor(ContextCompat.getColor(this, R.color.green))
            climbs <= 9 -> scoreTextView.setTextColor(ContextCompat.getColor(this, R.color.red))
            else -> scoreTextView.setTextColor(ContextCompat.getColor(this, R.color.default_blue))
        }

    }

    fun onClickClimb(view: View?){
        val climbBtn = findViewById<Button>(R.id.climb_btn)

        when{
            isFailed -> {
                climbBtn.text = getString(R.string.climb_btn_text)
                score = 0
                climbs = 0
                isFailed = false
            }
            else -> {
                score = climb(score)
                when{
                    climbs < 9 -> climbs += 1
                    else -> climbs = 0
                }
            }
        }

        println("Score$score\n Climbs$climbs")
        updateDashboard()
    }

    fun onClickFall(view: View?){
        val resetBtn = findViewById<Button>(R.id.reset_btn)
        val fallBtn = findViewById<Button>(R.id.fall_btn)
        val climbBtn = findViewById<Button>(R.id.climb_btn)

        score = fall(score)

        println("Score$score\n Climbs$climbs")

        updateDashboard()

        resetBtn.visibility = View.INVISIBLE
        fallBtn.visibility = View.INVISIBLE
        climbBtn.text = getString(R.string.try_again)
        isFailed = true

    }

    fun onClickReset(view: View?){
        score = 0
        climbs = 0
        println("Score$score\n Climbs$climbs")
        updateDashboard()
    }

    override fun onPause() {
        super.onPause()
        Log.i("onSaveInstate: ", "saved")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray(savedState, intArrayOf(score,climbs,isFailed.compareTo(true)))
    }

}

fun climb(score:Int): Int {
    var total: Int = 0
    if (score<3){
        total = score+1
    }else if ( score < 9){
        total = score+2
    }else if (score<18) {
        total = score + 3
    }
    return total
}

fun fall(score:Int): Int {
    return if (score-3 < 0){
        0
    } else {
        score - 3
    }
}