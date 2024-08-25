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

    private val scoreInstance:String = ""
    private val climbsInstance:String = ""

    private val resetBtn: Button = findViewById<Button>(R.id.reset_btn)
    private val fallBtn: Button = findViewById<Button>(R.id.fall_btn)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.scoreboard)

        if (savedInstanceState != null) {
            score = savedInstanceState.getInt(
                scoreInstance,
                0
            ) // Default to 0 if key not found
            climbs = savedInstanceState.getInt(
                climbsInstance,
                0
            ) // Default to 0 if key not found

            Log.i("savedInstnace", scoreInstance)
        }

        updateDashboard()
        Log.i("notsavedInstnace", scoreInstance)
    }

    private fun updateDashboard(){
        val scoreTextView = findViewById<TextView>(R.id.score)
        val climbsCount = findViewById<TextView>(R.id.climbsCount)


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
        score = fall(score)
        climbs = 0
        println("Score$score\n Climbs$climbs")
        updateDashboard()
        score = 0
        resetBtn.visibility = View.INVISIBLE
        fallBtn.visibility = View.INVISIBLE

    }

    fun onClickReset(view: View?){
        score = 0
        climbs = 0
        println("Score$score\n Climbs$climbs")
        updateDashboard()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(scoreInstance, score)
        outState.putInt(climbsInstance, climbs)
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