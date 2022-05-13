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

# 이미지 원형 표시하기

```
private  fun initImage(){
    Glide.with(this)
        .load(R.drawable.selfie_true)
        .circleCrop()
        .into(binding.imageView)

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
