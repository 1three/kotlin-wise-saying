package com.three.wise_saying

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.three.wise_saying.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sentenceList = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 명언 담기
        sentenceList.add("검정 화면에 대충 흰 글씨를\n쓰면 명언 같다.")
        sentenceList.add("사람에게 하나의 입과 두 개의\n귀가 있는 것은 말하기보다\n듣기를 두 배로 하라는 뜻이다.")
        sentenceList.add("결점이 없는 친구를 사귀려고\n한다면 평생 친구를 가질 수\n없을 것이다.")
        sentenceList.add("자기 아이에게 육체적 노동을\n가르치지 않는 것은\n약탈과 강도를 가르치는 것과\n마찬가지다.")
        sentenceList.add("승자는 눈을 밟아 길을 만들지만\n패자는 눈이 녹기를 기다린다.")
        sentenceList.add("그 사람 입장에 서기 전까지\n절대 그 사람을 욕하거나\n책망하지 마라.")
        sentenceList.add("뛰어난 말에게도\n채찍이 필요하다.")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.showAllSentenceBtn.setOnClickListener {
            val intent = Intent(this, SentenceActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val randomSentence = sentenceList.random()
        binding.wiseSayingTextArea.text = randomSentence.replace("\n", System.getProperty("line.separator"))
    }
}