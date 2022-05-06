# 

---

# Seminar2

---

## FollowerData.kt

<img width="385" alt="스크린샷 2022-04-22 오후 11 22 53" src="https://user-images.githubusercontent.com/97952129/164733872-fa50b765-3fc0-4ad1-9dcc-d1bb51928305.png">


- follower list 에 들어갈 Data class 생성
- 이름(name) 과 소개(introduce)

## FollowerAdaptor.kt


<img width="793" alt="스크린샷 2022-04-22 오후 11 24 28" src="https://user-images.githubusercontent.com/97952129/164734120-ad54124d-75e2-4452-b16f-56891d5322ee.png">



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


- corner속성으로 radius 를 조절하여 둥근 모서리 만들기
- gradient 속성을 활용해 그라데이션 효과 주기

## round_btn.xml

<img width="623" alt="스크린샷 2022-04-22 오후 11 31 07" src="https://user-images.githubusercontent.com/97952129/164735434-f5150a9c-53ae-4ab2-9de9-b113badc631d.png">


- corner속성으로 radius 를 조절하여 둥근 모서리 만들기
- stroke 속성으로 테두리 두께와 색 설정하기


---
# 실행화면 영상
https://user-images.githubusercontent.com/97952129/164733263-20f4388e-2ec1-44b9-8c82-6283b78afc3d.mov
---

