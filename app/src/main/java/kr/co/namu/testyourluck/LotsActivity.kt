package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lots.*

class LotsActivity : AppCompatActivity() {

    //    제비 텍스트 뷰들을 담아둘 목록
    val luckList = ArrayList<TextView>()

    //    추첨 결과를 모아둘 목록 : true / 생존,  false : 꽝
    val resultList = ArrayList<Boolean>()

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

            if (count == 1) {
                Toast.makeText(this, "최소 1개의 꽝은 필요합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            count--
            unLuckyCountTxt.text = count.toString()
        }

        unLuckyPlusBtn.setOnClickListener {
            var count = unLuckyCountTxt.text.toString().toInt()

            val peopleCount = peopleCountTxt.text.toString().toInt()

            if (count == peopleCount - 1){
                Toast.makeText(this, "꽝 갯수는 인원수와 같을 수 없습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            count++
            unLuckyCountTxt.text = count.toString()
        }

        testLuckBtn.setOnClickListener {

//            기존 당첨 결과 전부 삭제
            resultList.clear()

//            제비뽑기 : 꽝 갯수 + 당첨 갯수 = 전체 인원수

//            꽝 갯수 설정값 알아내기
            var unLuckyCount = unLuckyCountTxt.text.toString().toInt()

//            전체 인원수 알아내기
            var totalCount = peopleCountTxt.text.toString().toInt()

//            불변하는 전체 인원수 (반복에서 사용)
            val totalCountCopy = totalCount

//            전체 인원수 만큼 반복으로 제비뽑기

            for (i in 0..totalCountCopy-1) {

//                랜덤으로 꽝인지 / 생존인지
//                생존 : 1, 꽝 : 2 => 꽝확률 2/3 => 0.75 (75%)

//                Math.random() => 0~0.999999999 값이 랜덤으로 뜸.

                val cutLine = unLuckyCount.toDouble() / totalCount.toDouble()

                val randomVal = Math.random()

                Log.d("랜덤값", randomVal.toString())
                Log.d("컷트라인", cutLine.toString())

//                꽝으로 처리.
                if (randomVal < cutLine) {
                    resultList.add(false)
                    Log.d("당첨결과", false.toString())
//                  만약 꽝이뽑혔다면 꽝 갯수 하나 줄여주자.
                    unLuckyCount--
                }
                else {
//                    생존 처리
                    resultList.add(true)
                    Log.d("당첨결과", true.toString())
                }

//                한번 뽑고 나면 전체 갯수도 줄이자.
                totalCount--
            }

//            최종 결과 확인
            for (i in resultList.indices) {

//                추첨결과 - Boolean
                val result = resultList[i];
//                변경해줄 텍스트뷰
                val luckTxt = luckList[i];

                if (result) {
                    luckTxt.text = "생존!"
                }
                else {
                    luckTxt.text = "꽝!"
                }

            }
            Log.d("추첨결과", resultList.size.toString())


        }

    }
}