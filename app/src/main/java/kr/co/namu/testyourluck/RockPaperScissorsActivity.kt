package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_rock_paper_scissors.*

class RockPaperScissorsActivity : AppCompatActivity() {

//    그림파일 id값들 저장할 목록
    val imgList = ArrayList<Int>()

//    컴퓨터가 뭘 냈는지 저장
    var cpuPick = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rock_paper_scissors)

//        목록에 그림 세장 추가 (가위 / 바위 / 보)
        imgList.add(R.drawable.scissor)
        imgList.add(R.drawable.rock)
        imgList.add(R.drawable.paper)

        scissorBtn.setOnClickListener {

//            0~2를 랜덤으로 추출.

//            0 <= (랜덤*3).int로 변환 < 3

            val randomNum = (Math.random() * 3).toInt()
            val thisTimePick = imgList[randomNum]


//            랜덤으로 뽑힌 그림을 띄워주기
            cpuImg.setImageResource(thisTimePick)


            cpuPick = randomNum
            getResultOfRPS(0)

        }

        rockBtn.setOnClickListener {
            val randomNum = (Math.random() * 3).toInt()
            val thisTimePick = imgList[randomNum]
            cpuImg.setImageResource(thisTimePick)


            cpuPick = randomNum
            getResultOfRPS(1)

        }

        paperBtn.setOnClickListener {
            val randomNum = (Math.random() * 3).toInt()
            val thisTimePick = imgList[randomNum]
            cpuImg.setImageResource(thisTimePick)

            cpuPick = randomNum
            getResultOfRPS(2)
        }

    }

//    승/무/패를 판정해주는 기능

    fun getResultOfRPS(myPick : Int) {

//      사용자가 가위를 냈다
        if (myPick == 0) {

            if (cpuPick == 0) {
                Toast.makeText(this, "무승부", Toast.LENGTH_SHORT).show()
            }
            else if (cpuPick == 1) {
                Toast.makeText(this, "패배", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "승리", Toast.LENGTH_SHORT).show()
            }

        }
        else if (myPick == 1) {
//            바위를 낸 상황
            if (cpuPick == 0) {
                Toast.makeText(this, "승리", Toast.LENGTH_SHORT).show()
            }
            else if (cpuPick == 1) {
                Toast.makeText(this, "무승부", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "패배", Toast.LENGTH_SHORT).show()
            }
        }
        else {
//            보를 낸 상황
            if (cpuPick == 0) {
                Toast.makeText(this, "패배", Toast.LENGTH_SHORT).show()
            }
            else if (cpuPick == 1) {
                Toast.makeText(this, "승리", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "무승부", Toast.LENGTH_SHORT).show()
            }
        }
    }

}