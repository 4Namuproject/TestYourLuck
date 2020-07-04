package kr.co.namu.testyourluck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_baseball.*
import kr.co.namu.testyourluck.adapters.ChattingAdapter
import kr.co.namu.testyourluck.datas.ChattingMessage

class BaseballActivity : AppCompatActivity() {

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

    }
}