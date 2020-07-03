package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_rock_paper_scissors.*

class RockPaperScissorsActivity : AppCompatActivity() {

//    그림파일 id값들 저장할 목록
    val imgList = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rock_paper_scissors)

//        목록에 그림 세장 추가 (가위 / 바위 / 보)
        imgList.add(R.drawable.scissor)
        imgList.add(R.drawable.paper)
        imgList.add(R.drawable.rock)

        scissorBtn.setOnClickListener {

//            0~2를 랜덤으로 추출.

//            0 <= (랜덤*3).int로 변환 < 3

            val randomNum = (Math.random() * 3).toInt()
            val thisTimePick = imgList[randomNum]

//            랜덤으로 뽑힌 그림을 띄워주기
            cpuImg.setImageResource(thisTimePick)

        }
    }
}