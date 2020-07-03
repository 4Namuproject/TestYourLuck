package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lots.*

class LotsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lots)

        title = "제비 뽑기"

        peopleCountMinusBtn.setOnClickListener {

//            지금 몇명인지 받아오기
            var peopleCount = peopleCountTxt.text.toString().toInt()

//            1명 줄여주기
            peopleCount--

//            인원수 문구 변경
            peopleCountTxt.text = peopleCount.toString()

        }

        peopleCountPlusBtn.setOnClickListener {
            //            지금 몇명인지 받아오기
            var peopleCount = peopleCountTxt.text.toString().toInt()

//            1명 늘려주기
            peopleCount++

//            인원수 문구 변경
            peopleCountTxt.text = peopleCount.toString()
        }

        unLuckyMinusBtn.setOnClickListener {
            var count = unLuckyCountTxt.text.toString().toInt()
            count--
            unLuckyCountTxt.text = count.toString()
        }

        unLuckyPlusBtn.setOnClickListener {
            var count = unLuckyCountTxt.text.toString().toInt()
            count++
            unLuckyCountTxt.text = count.toString()
        }


    }
}