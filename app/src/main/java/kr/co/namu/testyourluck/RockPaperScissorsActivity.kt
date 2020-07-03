package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_rock_paper_scissors.*

class RockPaperScissorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rock_paper_scissors)

        scissorBtn.setOnClickListener {

            cpuImg.setImageResource(R.drawable.scissor)

        }
    }
}