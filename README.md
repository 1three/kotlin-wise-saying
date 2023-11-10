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

<br>

## 🗄️ ListView란?
_**ListView**_ 는 안드로이드에서 사용되는 여러 항목을 세로로 나열하여 표시 하는 뷰로,<br>
각 항목은 리스트의 아이템으로 표현되며 사용자는 스크롤하여 리스트의 내용을 확인할 수 있습니다.

이를 사용하기 위해서는 아이템의 데이터와 뷰를 연결하는 어댑터 클래스를 구현해야 합니다.

<img width="700" alt="ListView" src="https://github.com/1three/kotlin-wise-saying/assets/94810322/1a7fd7b7-1701-4f2d-b789-9e293fa979c9">

`출처 : 구글 'android listview' 검색 이미지`

<br>

#### 📂 MainActivity.kt

MainActivity에서는 ListView를 사용해 데이터를 표시할 수 있습니다.

```Kotlin
package com.three.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 데이터 (리스트 아이템들) 만들기
        val list_item = mutableListOf<String>()

        list_item.add("A")
        list_item.add("B")
        list_item.add("C")

        // 2. 어댑터로 데이터(아이템들) 전달하기
        val listAdapter = ListViewAdapter(list_item)

        // 3. 메인 레이아웃의 ListView 가져오기
        val listView = findViewById<ListView>(R.id.mainListView)

        // 3-1. ListView 어댑터에 데이터가 전달된 어댑터를 연결하여 데이터를 표시
        listView.adapter = listAdapter
    }
}
```

<br>

#### 📂 ListViewAdapter.kt

ListViewAdapter.kt는 어댑터 클래스로, ListView의 데이터와 뷰를 연결하는 역할을 합니다.

```Kotlin
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter(val List: MutableList<String>): BaseAdapter() {
    // (중요) 리스트 아이템의 개수를 정하는 함수
    override fun getCount(): Int {
        return List.size
    }

    // 개별 아이템을 가져오는 함수
    override fun getItem(position: Int): Any {
        return List[position]
    }

    // 개별 아이템의 인덱스(아이디) 가져오는 함수
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // (중요) 리스트의 아이템을 하나씩 가져와 넣어주는 함수
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView

        // 재사용 가능한 뷰(convertView)가 없는 경우에만 새로 생성
        if (convertView == null) {
            // 1) parent?.context를 통해 부모 뷰 그룹의 컨텍스트 가져오기
            // 2) LayoutInflater를 사용하여 list_view_item.xml에 정의된 뷰를 인플레이트
            // * 부모 뷰 그룹에 대한 정보와 인플레이트한 뷰를 부모로 설정
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.list_view_item, parent, false)
        }

        // convertView에서 아이템의 제목을 나타내는 TextView 찾기
        val titleTextView = convertView!!.findViewById<TextView>(R.id.listViewItem)

        // 해당 아이템의 데이터를 가져와 TextView에 설정
        titleTextView.text = List[position]

        return convertView!!
    }
}
```

<br>

#### 📂 list_view_item.xml

list_view_item.xml은 리스트 뷰의 아이템을 나타내는 XML 파일입니다.

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <TextView
        android:id="@+id/listViewItem"
        android:textSize="20sp"
        android:text="List View Item"
        android:layout_margin="10dp"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
</LinearLayout>
```

<br>

#### 📂 activity_main.xml

activity_main.xml은 메인 액티비티의 레이아웃 파일입니다.

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <ListView
        android:id="@+id/mainListView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
