# 

---

# Seminar2

---

## FollowerData.kt

- follower list 에 들어갈 Data class 생성
- 이름(name) 과 소개(introduce)

## FollowerAdaptor.kt

- RecyclerViewHolder.ViewHolder 클래스는 binding.root 를 넘겨줌
- onBind() : ViewHolder 가 가진 view  에 어댑터로 부터 받은 데이터를 받아옴
- getItemCount() :  Recycleview 로 보여줄 데이터의 개수를 반환. followerList 의 size 만큼
- onCreateViewHolder () : binding 객체를 생성하여 ViewHolder 의 생성자로 넘겨주는 함수
- onBindViewHolder() : viewHolder 에 있는 onBind() 함수 호출해서 넘겨줌

## FollowerFragment.kt

- adaptor 와 RecycleView 를 연동
- initFollowerAdaptor() : 어댑터로 전달해 줄 list 작성

---

## Repository 관련 함수는 위와 동일

---

## HorizontalItemDecorator.kt & VerticalItemDecorator.kt

- getItemOffset() 함수 오버라이딩
- getItemOffset()의 매개변수는 테두리(rect)와 데코레이트 하는 자식 뷰(View), 적용할 부모 뷰(RecycleView), 현재 recycleview view(State)
- ItemDecoration()을 이용하여 Item의 간격을 조정하는 함수

---

# Layout

## fragment_follower.xml

```
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/rv_follower"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
    tools:itemCount="4"
    tools:listitem="@layout/follower_list"/>

```

- layoutManager 속성을 LinearLayoutManager 로 설정

## fragment_repository.xml

```
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/rv_follower"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"
    android:orientation="vertical"
    app:spanCount="2"
    tools:listitem="@layout/follower_list"/>
```

- layoutManager 속성을 GridLayoutManager 로 설정
- spanCount =  2 로 2열의 그리드 형태

## follower_list.xml

- ellipsize 를 활용해 end 를 속성으로 줘서 ... 을 뒤에 나타나게 함
- maxline 은 최대 넘어갈 수 있는 행을 나타냄

---

# drawable

## round_btn.xml

- corner속성으로 radius 를 조절하여 둥근 모서리 만들기
- gradient 속성을 활용해 그라데이션 효과 주기

## round_btn.xml

- corner속성으로 radius 를 조절하여 둥근 모서리 만들기
- stroke 속성으로 테두리 두께와 색 설정하기


---
# 실행화면 영상
https://user-images.githubusercontent.com/97952129/164733263-20f4388e-2ec1-44b9-8c82-6283b78afc3d.mov
---

