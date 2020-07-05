package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_lotto.*
import java.util.*
import kotlin.collections.ArrayList

class LottoActivity : AppCompatActivity() {

//    당첨번호 6개 텍스트뷰를 담고 있는 목록
    val lottoNumTxtList = ArrayList<TextView>()

//    당첨 번호 6개 (Int)를 담고 있는 목록
    val winNumList = ArrayList<Int>()

//    보너스 번호를 저장하는 변수
    var winBonusNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lotto)

//        당첨번호 텍스트뷰 6개를 목록에 추가
        lottoNumTxtList.add(lottoNumTxt01)
        lottoNumTxtList.add(lottoNumTxt02)
        lottoNumTxtList.add(lottoNumTxt03)
        lottoNumTxtList.add(lottoNumTxt04)
        lottoNumTxtList.add(lottoNumTxt05)
        lottoNumTxtList.add(lottoNumTxt06)

//        한장 구매하기 버튼 눌리면
        buyOneLottoBtn.setOnClickListener {
//            랜덤으로 1~45 숫자를 뽑아서 => 6개 텍스트뷰에 반영

//            이미 뽑아둔 번호가 있다면 전부 삭제
            winNumList.clear()

//            6개의 텍스트뷰를 txt에 담으면서 반복
            for (txt in lottoNumTxtList) {

//                중복이 아닐때 까지 계속 다시 뽑자
                while (true) {

//                    1~45의 정수를 랜덤으로 뽑자.
                    val randomNum = (Math.random() * 45 + 1).toInt()

//                    중복 검사를 실행해서
                    var duplicatedCheckResult = true // 중복검사 통과로 전제

//                    당첨 번호를 (저장해둔 배열을) 돌아보면서 같은걸 발견하면 중복검사 통과 실패

                    for (num in winNumList) {
                        if (num == randomNum) {
                            duplicatedCheckResult = false
                        }
                    }

//                    중복검사를 통과했다면
                    if (duplicatedCheckResult) {

//                        뽑은 랜덤 번호를 당첨번호로 인정. (등록)
                        winNumList.add(randomNum)

                        break
                    }
                }

            }

//            당첨번호를 작은 수 부터 정렬
            Collections.sort(winNumList)

//            실제로 텍스트뷰에 뽑힌 번호 반영
            for (i in winNumList.indices) {
//                1~6번 텍스트뷰 가져오기
                val lottoTxt = lottoNumTxtList[i]

//                당첨번호도 상황에 맞게 가져오기
                val winNum = winNumList[i]

                lottoTxt.text = winNum.toString()

            }

//            보너스번호를 뽑아서 반영
//            1~45를 랜덤으로 추출 => 당첨번호와 중복 검사 => 통과하면 반영

            while (true) {
//                1~45 랜덤 추출
                val randomNum = (Math.random() * 45 + 1).toInt()

//                중복 검사 로직
                var duplCheckResult = true
                for (num in winNumList) {
                    if (randomNum == num) {
                        duplCheckResult = false
                    }
                }

//                중복검사 통과시 보너스 번호로 선정
                if (duplCheckResult) {
                    winBonusNum = randomNum
                    break
                }

            }

            bonusNumTxt.text = winBonusNum.toString()

        }

    }
}