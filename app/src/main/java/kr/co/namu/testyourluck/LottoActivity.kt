package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.EditText
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

//    내 번호 6개 EditText를 담고 있는 목록
    val myNumEdtList = ArrayList<EditText>()

//    각 등수별 당첨 횟수를 기록할 변수
    var firstRankCount = 0
    var secondRankCount = 0
    var thirdRankCount = 0
    var fourthRankCount = 0
    var fifthRankCount = 0
    var unRankCount = 0

//    사용 금액과, 당첨 금액을 저장할 변수
    var useMoney = 0L
    var winMoney = 0L

//    반복을 돌려주는 핸들러
    val myHandler = Handler()

//    핸들러가 수행해줄 일

    val myRunnable = object : Runnable {
        override fun run() {

            if (useMoney < 10000000) {

                makeWinNumbers()
                buyLottoLoop()

            }

        }

    }

    fun buyLottoLoop() {
        myHandler.post(myRunnable)
    }


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

//        내 번호 에딧텍스트 6개를 목록에 추가
        myNumEdtList.add(myNumEdt01)
        myNumEdtList.add(myNumEdt02)
        myNumEdtList.add(myNumEdt03)
        myNumEdtList.add(myNumEdt04)
        myNumEdtList.add(myNumEdt05)
        myNumEdtList.add(myNumEdt06)

//        한장 구매하기 버튼 눌리면
        buyOneLottoBtn.setOnClickListener {

            makeWinNumbers()

        }

//        자동 구매하기 버튼이 눌리면
        autoLottoBtn.setOnClickListener {

            buyLottoLoop()

        }

    }

//


//    당첨번호 만드는 코드를 모아둔 함수

    fun makeWinNumbers() {

//        천원을 썼다고 계산
        useMoney += 1000

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

//        등수 계산 기능 실행
        getMyRank()
    }

//    내 번호와, 당첨 번호를 비교해서 몇등인지 계산해주는 함수

    fun getMyRank() {

//        맞춘 숫자가 몇개인지 저장할 변수
        var correctNumCount = 0

//        당첨 번호를 바꿔가면서 (하나씩 꺼내서) 확인
        for (winNum in winNumList) {

//            내 번호와 (하나씩 꺼내서) 비교하자.
            for (myNumEdt in myNumEdtList) {

//                내 당첨번호 입력 칸에 적힌 문자를 => Int로 변환 (비교해야되니까)
                val myNum = myNumEdt.text.toString().toInt()

//                같은 숫자 하나 발견
                if (myNum == winNum) {

//                    맞춘 숫자 갯수 하나 증가
                    correctNumCount++
                }

            }

        }

//        맞춘 갯수에 따라 등수 판단
        if (correctNumCount == 6) {
            Log.d("등수", "1등")

//            1등 당첨횟수 증가
            firstRankCount++
//            텍스트뷰에도 반영
            firstRankCountTxt.text = "1등 : ${firstRankCount}회"


//            당첨금액을 12억원 증가
            winMoney += 1200000000

        }
        else if (correctNumCount == 5) {

//            보너스 번호를 들고 내 번호들과 검사.
//            같은게 있다면 2등, 아니면 3등

            var isBonusNumCorrect = false
            for (myNumEdt in myNumEdtList) {
//                내 입력값을 int로 변환
                val myNum = myNumEdt.text.toString().toInt()

//                보너스 번호와 같은지
                if (myNum == winBonusNum) {
                    isBonusNumCorrect = true
                }
            }

//            보너스번호 여부에 따라 등수 판정
            if (isBonusNumCorrect) {
                Log.d("등수", "2등")

                secondRankCount++
                secondRankCountTxt.text = "2등 : ${secondRankCount}회"

//                4천만원 추가
                winMoney += 40000000

            }
            else {
                Log.d("등수", "3등")
                thirdRankCount++
                thirdRankCountTxt.text = "3등 : ${thirdRankCount}회"

//                150만원 추가
                winMoney += 1500000

            }

        }
        else if (correctNumCount == 4) {
            Log.d("등수", "4등")
            fourthRankCount++
            fourthRankCountTxt.text = "4등 : ${fourthRankCount}회"

//            5만원 추가
            winMoney += 50000

        }
        else if (correctNumCount == 3) {
            Log.d("등수", "5등")

            fifthRankCount++
            fifthRankCountTxt.text = "5등 : ${fifthRankCount}회"

//            사용 금액을 5천원 줄이자.
            useMoney -= 5000

        }
        else {
            Log.d("등수", "꽝")
            unRankCount++
            unRankedCountTxt.text = "낙첨 : ${unRankCount}회"
        }

//        사용 금액과, 당첨 금액을 갱신.
        useMoneyTxt.text = String.format("사용 금액 : %,d원", useMoney)
        winMoneyTxt.text = String.format("당첨 금액 : %,d원", winMoney)


    }

}