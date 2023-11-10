# kotlin-wise-saying
[Kotlin] 명언을 볼 수 있는 `안드로이드` 앱

![명언_앱](https://github.com/1three/kotlin-wise-saying/assets/94810322/021efd86-06c3-42c2-9284-274a1fb6955f)

<br>

## ✍🏻 배운 점

#### 📝 랜덤한 값 가져오기

List의 random() 함수를 통해 랜덤한 값을 가져올 수 있다는 것을 알게 되었습니다.

```Kotlin
private val sentenceList = mutableListOf<String>()
sentenceList.add("검정 화면에 대충 흰 글씨를\n쓰면 명언 같다.")
sentenceList.add("사람에게 하나의 입과 두 개의\n귀가 있는 것은 말하기보다\n듣기를 두 배로 하라는 뜻이다.")
sentenceList.add("결점이 없는 친구를 사귀려고\n한다면 평생 친구를 가질 수\n없을 것이다.")
sentenceList.add("자기 아이에게 육체적 노동을\n가르치지 않는 것은\n약탈과 강도를 가르치는 것과\n마찬가지다.")
sentenceList.add("승자는 눈을 밟아 길을 만들지만\n패자는 눈이 녹기를 기다린다.")
sentenceList.add("그 사람 입장에 서기 전까지\n절대 그 사람을 욕하거나\n책망하지 마라.")
sentenceList.add("뛰어난 말에게도\n채찍이 필요하다.")

val randomSentence = sentenceList.random()
```

<br>

#### 📝 뒤로 가기 시에도, 새로운 명언 나오게 만들기

기존에는 앱을 다시 실행할 때에만 메인 페이지에 새로운 명언이 표출되었습니다.<br>

그러나 onResume() 함수를 통해 뒤로 가기를 통해 다시 메인 액티비티로 돌아왔을 때에도<br>
새로운 명언이 나오도록 구현할 수 있었습니다.

```Kotlin
override fun onResume() {
    super.onResume()
    val randomSentence = sentenceList.random()
    binding.wiseSayingTextArea.text = randomSentence.replace("\n", System.getProperty("line.separator"))
}
```
