package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_baseball.*
import kr.co.namu.testyourluck.adapters.ChattingAdapter
import kr.co.namu.testyourluck.datas.ChattingMessage

class BaseballActivity : AppCompatActivity() {

//     몇번만에 정답을 맞췄는지 기록하기 위한 변수
    var tryCount = 0

//    문제로 나온 세자리 숫자를 저장할 배열
    val cpuNumbers = ArrayList<Int>()

//    컴퓨터와 사람이 주고받은 채팅 메세지들을 담아주는 목록
    val chattingMessageList = ArrayList<ChattingMessage>()

//    리스트뷰에 채팅을 뿌려주는 어댑터
    lateinit var mChatAdapter : ChattingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baseball)

//        리스트뷰에 출력 (어댑터 연결)
        mChatAdapter = ChattingAdapter(this, R.layout.chatting_message_list_item, chattingMessageList)
        chattingListView.adapter = mChatAdapter

//        컴퓨터가 환영 메세지를 말함.

        Handler().postDelayed({
//            일정 시간 이후에 실행할 내용
            chattingMessageList.add(ChattingMessage("CPU", "숫자 야구 게임에 오신것을 환영합니다."))
            mChatAdapter.notifyDataSetChanged()

        }, 700)

        Handler().postDelayed({
            chattingMessageList.add(ChattingMessage("CPU", "제가 생각하는 세자리 숫자를 맞춰주세요."))
            mChatAdapter.notifyDataSetChanged()
        }, 1400)

        Handler().postDelayed({
            chattingMessageList.add(ChattingMessage("CPU", "0은 없고, 중복된 숫자도 없습니다."))
            mChatAdapter.notifyDataSetChanged()
        }, 2100)


//        문제 출제 기능 실행
        makeCpuNumbers()

//        입력 버튼 누르면 이벤트 처리
        okBtn.setOnClickListener {


//            입력한 내용 확인
            val input = inputNumberEdt.text.toString()


//            같은 값을 다시 넣을 일은 없다.
//            입력되어있던 내용을 빈칸으로 변경
            inputNumberEdt.setText("")

//            3자리가 아니면 거부.
            if (input.length != 3) {
                Toast.makeText(this, "세자리 숫자로 입력해주세요.", Toast.LENGTH_SHORT).show()
//                이 뒤는 실행 못하게 강제 종료.
                return@setOnClickListener
            }

//            이 뒤로는 무조건 세자리 숫자라는게 확정된 상태.

//            채팅 내용으로 띄워주기.
            chattingMessageList.add(ChattingMessage("USER", input))

//            어댑터가 새로 고침 해야 반영됨.
            mChatAdapter.notifyDataSetChanged()

//            리스트뷰를 바닥 (맨 마지막 채팅)으로 끌어내리기
            chattingListView.smoothScrollToPosition(chattingMessageList.size-1)

//            컴퓨터 ?S ?B인지 답장하기
            checkStrikeAndBall(input)

        }

    }

//    컴퓨터가 답장해주는 기능
//    입력값을 알아야 ?S ?B인지 판단 가능.

    fun checkStrikeAndBall(inputString : String) {

//        한번 시도 했다고, 시도 횟수를 증가.
        tryCount++

//        정답 : 741 => 입력 : 145 1S 1B
//        입력값을 숫자로 바꾸자.
        val inputNum = inputString.toInt()

//        숫자를 다시 배열로 쪼개주자.
        val userNumbers = ArrayList<Int>()

        userNumbers.add(inputNum / 100) // 100의 자리 ex. 745 / 100 => 7
        userNumbers.add(inputNum / 10 % 10) // 10의 자리
        userNumbers.add(inputNum % 10) // 1의 자리

//        S 갯수, B 갯수 파악.
        var strikeCount = 0
        var ballCount = 0

//        i가 내 숫자를 확인하는 역할
        for (i in 0..2) {

//            j가 문제 숫자를 확인하는 역할
            for (j in 0..2) {

//                두 칸에 적힌 숫자가 같은가?
                if (userNumbers[i] == cpuNumbers[j]) {
//                    같다 : S / B 추가 검사
                    if (i == j) {
//                        스트라이크를 찾았다
                        strikeCount++
                    }
                    else {
//                        볼을 찾았다
                        ballCount++
                    }

                }

            }

        }

//        구해낸 스트라이크 갯수와 / 볼 갯수로 컴퓨터가 답장하게 하자.

        val cpuMessage = "${strikeCount}S ${ballCount}B 입니다."

//        컴퓨터의 답장을 0.7초 후에 띄우도록

        Handler().postDelayed({
            chattingMessageList.add(ChattingMessage("CPU", cpuMessage))
            mChatAdapter.notifyDataSetChanged()
            chattingListView.smoothScrollToPosition(chattingMessageList.size-1)
        }, 700)


//        정답 체크 자체를 1.4초 후에 해서, 채팅이 이어지는것처럼 보이게 하자.
        Handler().postDelayed({
            //        만약 3S 라면, 정답이라는 메세지를 주고, 게임을 종료시키자.
            if (strikeCount == 3) {
                chattingMessageList.add(ChattingMessage("CPU", "정답입니다!"))

//            몇번만에 맞췄는지도 컴퓨터가 이야기 해주자.
                chattingMessageList.add(ChattingMessage("CPU", "${tryCount}회 만에 맞췄습니다."))

                mChatAdapter.notifyDataSetChanged()
                chattingListView.smoothScrollToPosition(chattingMessageList.size-1)

//            입력을 못하게 막자. => enabled를 false로 비활성화.
                inputNumberEdt.isEnabled = false
                okBtn.isEnabled = false

//            게임 종료 안내 메세지 토스트로
                Toast.makeText(this, "이용해 주셔서 감사합니다.", Toast.LENGTH_SHORT).show()

            }

        }, 1400)


    }

//    컴퓨터가 문제를 내주는 기능

    fun makeCpuNumbers() {

//        cpuNumbers에 랜덤한 세자리 숫자 배치.

        for (i in 0..2) {

//            중복이면 랜덤숫자를 다시 뽑아야함. => 중복이 아닐때까지 계속 뽑자.
            while (true) {

//                1~9의 정수를 랜덤 뽑기.
                val randomNum = (Math.random()*9+1).toInt()

//                이미 뽑은 숫자인지 확인 (중복검사)
                var isDuplicatedNum = false

//                문제로 나온 숫자를 둘러봐서, 같은 숫자가 발견되면 중복인걸로.
                for (num in cpuNumbers) {
                    if (num == randomNum) {
                        isDuplicatedNum = true
                    }
                }

//                중복이 아니어야 문제 숫자로 사용.
                if (!isDuplicatedNum) {
                    cpuNumbers.add(randomNum)
//                    다음 숫자 뽑으러 이동.
                    break
                }

            }

        }

//        로그로 문제 숫자 확인
        for (num in cpuNumbers) {
            Log.d("문제출제", num.toString())
        }

    }

}