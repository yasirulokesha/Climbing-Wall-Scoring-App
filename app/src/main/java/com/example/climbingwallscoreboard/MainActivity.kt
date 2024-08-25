package com.example.climbingwallscoreboard

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle

class MainActivity : ComponentActivity() {

    private var score:Int = 0
    private var climbs:Int = 0

    private val savedState:String = ""

    private val resultArray:IntArray = intArrayOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.scoreboard)

        if (savedInstanceState != null) {
            val savedState = savedInstanceState.getIntArray(savedState)
            if (savedState != null && savedState.size == 2) {
                score = savedState[0]  // Restore the score value
                climbs = savedState[1] // Restore the climbs value
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
                resetBtn.visibility = View.INVISIBLE
                fallBtn.visibility = View.INVISIBLE
            }
            score > 0 -> {
                scoreTextView.text = score.toString()
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
        score = climb(score)
        when{
            climbs < 9 -> climbs += 1
            else -> climbs = 0
        }
        println("Score$score\n Climbs$climbs")
        updateDashboard()
    }

    fun onClickFall(view: View?){
        val resetBtn = findViewById<Button>(R.id.reset_btn)
        val fallBtn = findViewById<Button>(R.id.fall_btn)

        score = fall(score)

        println("Score$score\n Climbs$climbs")

        updateDashboard()

        score = 0
        climbs = 0

        resetBtn.visibility = View.INVISIBLE
        fallBtn.visibility = View.INVISIBLE

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
        outState.putIntArray(savedState, intArrayOf(score,climbs))
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