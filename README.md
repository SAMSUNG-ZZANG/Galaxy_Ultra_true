
# Seminar03

---

# BottomNavigation

---

## TestViewPagerAdapter.kt

```
package com.example.sopt_main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TestViewPagerAdaptor(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity){
        val fragments =mutableListOf<Fragment>()

        override fun getItemCount() : Int = fragments.size

        override fun createFragment(position: Int) : Fragment = fragments[position]
    }

```

- FragmentStateAdapter 상속받기
- getItemCount() : 어댑터가 갖고 있는 아이템 수를 반환하는 메소드
- createFragment() : 연결되어 있는 Fragment 를 제공해주는 메소드

---

## HomeActivity.kt

```
private fun initAdaptor(){
    val fragmentList =listOf(ProfileFragment(),HomeFragment(),TestFragment3())
    testViewPagerAdaptor = TestViewPagerAdaptor(this)
    testViewPagerAdaptor.fragments.addAll(fragmentList)

    homeBinding.homeVp.adapter= testViewPagerAdaptor
}
```

- 만들어준 TestViewPagerAdapter 와 연동해주기

```
private fun initBottomNavi(){
    homeBinding.homeVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            homeBinding.homeBnv.menu.getItem(position).isChecked= true
        }
    })

    homeBinding.homeBnv.setOnItemSelectedListener{
when(it.itemId){
            R.id.menu_android-> {
                homeBinding.homeVp.currentItem= FIRST_FRAGMENT
                return@setOnItemSelectedListener true
            }
            R.id.menu_list-> {
                homeBinding.homeVp.currentItem= SECOND_FRAGMENT
                return@setOnItemSelectedListener true
            }
            else -> {
                homeBinding.homeVp.currentItem= THIRD_FRAGMENT
                return@setOnItemSelectedListener true
            }
        }

}
}

companion object{
    const val FIRST_FRAGMENT = 0
    const val SECOND_FRAGMENT = 1
    const val THIRD_FRAGMENT = 2

}
```

- bottomNavigation 과 viewPager2 를 연동해주는 함수
- 특정 버튼을 눌렀을 때 isChecked() 를 활용하여 체크되어 있는지 확인
- 현재 어떤 아이템이 눌렸는지에 따라 화면을 전환해주는 setOnItemSelectedListener

---

## activity_home.xml

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".HomeActivity">

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/home_vp"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_constraintBottom_toTopOf="@id/home_bnv"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/home_bnv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/white"
        app:itemIconTint="@color/select_bottom_navi"
        app:itemRippleColor="#6424D5"
        app:itemTextColor="@color/select_bottom_navi"
        app:layout_constraintBottom_toBottomOf="parent"
        app:menu="@menu/menu_sample"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```

- 전환하는 화면들을 담을 viewPager2 와 하단에 bottomNavigation 설정
- 선택했을 때와 선택되지 않았을 때의 색상을 지정
- menu 를 불러와서 적용

---

## menu_sample.xml

```
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/menu_android"
        android:icon="@drawable/profile"
        android:title="프로필" />
    <item
        android:id="@+id/menu_list"
        android:icon="@drawable/home"
        android:title="홈"/>
    <item
        android:id="@+id/menu_setting"
        android:icon="@drawable/camera"
        android:title="카메라"/>
</menu>
```

- bottomNavigation에 들어갈 3개의 메뉴 설정하기

---

# TabLayout

---

## fragment_home.xml
=======
# 

---



---

## FollowerData.kt

<img width="385" alt="스크린샷 2022-04-22 오후 11 22 53" src="https://user-images.githubusercontent.com/97952129/164733872-fa50b765-3fc0-4ad1-9dcc-d1bb51928305.png">


- follower list 에 들어갈 Data class 생성
- 이름(name) 과 소개(introduce)

## FollowerAdaptor.kt


<img width="793" alt="스크린샷 2022-04-22 오후 11 24 28" src="https://user-images.githubusercontent.com/97952129/164734120-ad54124d-75e2-4452-b16f-56891d5322ee.png">



```
<com.google.android.material.tabs.TabLayout
    android:id="@+id/home_tap"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:layout_constraintTop_toBottomOf="@+id/home_name"
    app:tabIndicatorColor="@color/purple_200"/>

<androidx.core.widget.NestedScrollView
    android:layout_width="match_parent"
    android:layout_height="0dp"
    app:layout_constraintTop_toBottomOf="@+id/home_tap"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent">

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/home_vp"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:paddingTop="156dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/home_tap" />

</androidx.core.widget.NestedScrollView>

```

- tapLayout 과 연동시켜줄 viewPager2
- 중첩스크롤을 막기위한 NestedScrollView 설정

---

## TabViewPagerAdapter.kt

```
package com.example.sopt_main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabViewPagerAdaptor(fragment: Fragment):
    FragmentStateAdapter(fragment){
    val fragments =mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size
=======
- RecyclerViewHolder.ViewHolder 클래스는 binding.root 를 넘겨줌
- onBind() : ViewHolder 가 가진 view  에 어댑터로 부터 받은 데이터를 받아옴
- getItemCount() :  Recycleview 로 보여줄 데이터의 개수를 반환. followerList 의 size 만큼
- onCreateViewHolder () : binding 객체를 생성하여 ViewHolder 의 생성자로 넘겨주는 함수
- onBindViewHolder() : viewHolder 에 있는 onBind() 함수 호출해서 넘겨줌

## FollowerFragment.kt

<img width="793" alt="스크린샷 2022-04-22 오후 11 25 13" src="https://user-images.githubusercontent.com/97952129/164734307-995d96ec-e349-4fc0-8834-b346045b73ef.png">


- adaptor 와 RecycleView 를 연동
- initFollowerAdaptor() : 어댑터로 전달해 줄 list 작성

---

## Repository 관련 함수는 위와 동일

---

## HorizontalItemDecorator.kt & VerticalItemDecorator.kt

<img width="921" alt="스크린샷 2022-04-22 오후 11 26 11" src="https://user-images.githubusercontent.com/97952129/164734500-ed7761f6-7788-4544-8248-813478a16d30.png">


- getItemOffset() 함수 오버라이딩
- getItemOffset()의 매개변수는 테두리(rect)와 데코레이트 하는 자식 뷰(View), 적용할 부모 뷰(RecycleView), 현재 recycleview view(State)
- ItemDecoration()을 이용하여 Item의 간격을 조정하는 함수

---

# Layout

## fragment_follower.xml

<img width="662" alt="스크린샷 2022-04-22 오후 11 27 20" src="https://user-images.githubusercontent.com/97952129/164734691-ab39a9e8-bd2b-4983-99f0-0eb143ada17d.png">




    override fun createFragment(position: Int): Fragment = fragments[position]


}
```

- FragmentStateAdapter를 상속받는 어댑터
- getItemCount(): 만들어진 개수 만큼 반환하는 메소드
- createFragment() : 연결되어 있는 프래그먼트를 제공해주는 메소드

---

## HomeFragment.kt

```
private fun initAdaptor(){
    val fragmentList =listOf(TabFollowerFragment(),TabFollowingFragment())

    tabViewPagerAdaptor = TabViewPagerAdaptor(this)
    tabViewPagerAdaptor.fragments.addAll(fragmentList)
=======
- layoutManager 속성을 LinearLayoutManager 로 설정

## fragment_repository.xml

<img width="662" alt="스크린샷 2022-04-22 오후 11 28 02" src="https://user-images.githubusercontent.com/97952129/164734800-bedf045c-7bb8-443b-af1c-eb35d8440741.png">


- layoutManager 속성을 GridLayoutManager 로 설정
- spanCount =  2 로 2열의 그리드 형태

## follower_list.xml

<img width="617" alt="스크린샷 2022-04-22 오후 11 29 25" src="https://user-images.githubusercontent.com/97952129/164735092-6f860b60-f3a2-4702-b89d-e35d560c3e94.png">


- ellipsize 를 활용해 end 를 속성으로 줘서 ... 을 뒤에 나타나게 함
- maxline 은 최대 넘어갈 수 있는 행을 나타냄

---

# drawable

## round_btn.xml

<img width="617" alt="스크린샷 2022-04-22 오후 11 30 24" src="https://user-images.githubusercontent.com/97952129/164735310-340dfd24-c9d1-4fd9-8c80-ece0bcba65c2.png">


    binding.homeVp.adapter= tabViewPagerAdaptor
}


private fun initTabLayout(){
    val tabLabel =listOf("팔로우","팔로워")

    TabLayoutMediator(binding.homeTap, binding.homeVp){tab, position->
tab.text= tabLabel[position]
}.attach()
}

```

- initAdapter() : 어댑터와 연결
- initTabLayout() : 페이지 개수 만큼 탭을 생성, attach()를 호출할 때마다 기존 탭 지우고 새로운 탭 생성

---
=======
- corner속성으로 radius 를 조절하여 둥근 모서리 만들기
- gradient 속성을 활용해 그라데이션 효과 주기

## round_btn.xml

<img width="623" alt="스크린샷 2022-04-22 오후 11 31 07" src="https://user-images.githubusercontent.com/97952129/164735434-f5150a9c-53ae-4ab2-9de9-b113badc631d.png">


# 이미지 원형 표시하기


```
private  fun initImage(){
    Glide.with(this)
        .load(R.drawable.selfie_true)
        .circleCrop()
        .into(binding.imageView)
=======
- corner속성으로 radius 를 조절하여 둥근 모서리 만들기
- stroke 속성으로 테두리 두께와 색 설정하기


}
```

- Glide 속성 중 circleCrop() 활용하여 사진을 원형으로 보이게 함

---

# Selector

<img width="310" alt="스크린샷 2022-05-06 오후 8 52 46" src="https://user-images.githubusercontent.com/97952129/167128209-dcf41876-0c36-4ebc-af67-f066afd4a765.png">

## select_btn.xml

```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/round_btn" android:state_selected="true" />
    <item android:drawable="@drawable/btn_not_seleted" android:state_selected="false" />
</selector>
```

- state_selected 속성을 활용하여 버튼 누를 때와 누르지 않았을 때의 버튼 모양을 다르게 설정
- 버튼 background 에 적용할 drawble 파일

---

## ProfileFragment.kt

```
private fun transactionFragment(){

    val fragment1 =FollowerFragment()
    val fragment2 =RepositoryFragment()

childFragmentManager.beginTransaction().add(R.id.profile_fragment,fragment1).commit()

    binding.homeFollowerBtn.setOnClickListener{
val transaction =childFragmentManager.beginTransaction()
        binding.homeFollowerBtn.isSelected=true
        binding.homeRepoBtn.isSelected=false
        transaction.replace(R.id.profile_fragment,fragment1)
        transaction.commit()
}

binding.homeRepoBtn.setOnClickListener{
val transaction =childFragmentManager.beginTransaction()
        binding.homeRepoBtn.isSelected=true
        binding.homeFollowerBtn.isSelected=false
        transaction.replace(R.id.profile_fragment,fragment2)
        transaction.commit()
}
}
```

```

override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    _binding = FragmentProfileBinding.inflate(layoutInflater,container,false)

    binding.homeFollowerBtn.isSelected=true
    binding.homeRepoBtn.isSelected=false
    transactionFragment()
    initImage()

    return binding.root
}
```

- setOnClickListener() 함수 안에 isSelected() 값을  true 또는 false  로 설정하여 버튼의 색상이 바뀌도록 설정

---


# 실행영상
https://user-images.githubusercontent.com/97952129/167128427-744bb7dd-7c0a-49ee-89a0-004ddb481fbb.mov
=======
---
# 실행화면 영상
https://user-images.githubusercontent.com/97952129/164733263-20f4388e-2ec1-44b9-8c82-6283b78afc3d.mov
---


