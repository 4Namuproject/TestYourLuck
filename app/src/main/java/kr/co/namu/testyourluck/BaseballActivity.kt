package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_baseball.*
import kr.co.namu.testyourluck.adapters.ChattingAdapter
import kr.co.namu.testyourluck.datas.ChattingMessage

class BaseballActivity : AppCompatActivity() {

//    문제로 나온 세자리 숫자를 저장할 배열
    val cpuNumbers = ArrayList<Int>()

//    컴퓨터와 사람이 주고받은 채팅 메세지들을 담아주는 목록
    val chattingMessageList = ArrayList<ChattingMessage>()

//    리스트뷰에 채팅을 뿌려주는 어댑터
    lateinit var mChatAdapter : ChattingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baseball)

//        컴퓨터가 환영 메세지를 말함.
        chattingMessageList.add(ChattingMessage("CPU", "숫자 야구 게임에 오신것을 환영합니다."))
        chattingMessageList.add(ChattingMessage("CPU", "제가 생각하는 세자리 숫자를 맞춰주세요."))
        chattingMessageList.add(ChattingMessage("CPU", "0은 없고, 중복된 숫자도 없습니다."))

//        리스트뷰에 출력 (어댑터 연결)
        mChatAdapter = ChattingAdapter(this, R.layout.chatting_message_list_item, chattingMessageList)
        chattingListView.adapter = mChatAdapter

//        문제 출제 기능 실행
        makeCpuNumbers()

//        입력 버튼 누르면 이벤트 처리
        okBtn.setOnClickListener {

//            입력한 내용 확인
            val input = inputNumberEdt.text.toString()

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


        }

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