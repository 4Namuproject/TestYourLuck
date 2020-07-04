package kr.co.namu.testyourluck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lotsBtn.setOnClickListener {
            val myIntent = Intent(this, LotsActivity::class.java)
            startActivity(myIntent)
        }

        rpsBtn.setOnClickListener {
            val myIntent = Intent(this, RockPaperScissorsActivity::class.java)
            startActivity(myIntent)
        }

        baseballBtn.setOnClickListener {

            val myIntent = Intent(this, BaseballActivity::class.java)
            startActivity(myIntent)

        }

    }
}