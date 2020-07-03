package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lots.*

class LotsActivity : AppCompatActivity() {

//    제비 텍스트 뷰들을 담아둘 목록
    val luckList = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lots)

        title = "제비 뽑기"

//        목록에 제비들을 담자.
        luckList.add(luckTxt01)
        luckList.add(luckTxt02)
        luckList.add(luckTxt03)
        luckList.add(luckTxt04)
        luckList.add(luckTxt05)

        peopleCountMinusBtn.setOnClickListener {

//            지금 몇명인지 받아오기
            var peopleCount = peopleCountTxt.text.toString().toInt()

//            지금 갯수가 2개면 강제종료
            if (peopleCount == 2) {
                Toast.makeText(this, "제비는 최소 2개까지 가능합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            1명 줄여주기
            peopleCount--

//            인원수 문구 변경
            peopleCountTxt.text = peopleCount.toString()

//            제비를 일단 다 숨겼다가 => 필요한 만큼만 보여주기

//            제비 전부 숨기기
            for (luck in luckList) {
                luck.visibility = View.GONE
            }

//            필요한 만큼 보여주기
            for (i in 0..peopleCount-1) {
                luckList[i].visibility = View.VISIBLE
            }


        }

        peopleCountPlusBtn.setOnClickListener {
            //            지금 몇명인지 받아오기
            var peopleCount = peopleCountTxt.text.toString().toInt()

//            만약 이미 5명이면 강종
            if (peopleCount == 5) {
                Toast.makeText(this, "제비는 5개 까지만 가능합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            1명 늘려주기
            peopleCount++

//            인원수 문구 변경
            peopleCountTxt.text = peopleCount.toString()


//            제비를 일단 다 숨겼다가 => 필요한 만큼만 보여주기

//            제비 전부 숨기기
            for (luck in luckList) {
                luck.visibility = View.GONE
            }

//            필요한 만큼 보여주기
            for (i in 0..peopleCount-1) {
                luckList[i].visibility = View.VISIBLE
            }

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