# SignInActivity

 

### 회원가입 화면에서 입력했던 아이디와 비밀번호를 받아오기 (성장과제)
![Untitled](https://user-images.githubusercontent.com/97952129/162616058-57847fb4-1193-49a0-8be9-482f1d84f8c2.png)


- registerForActivityResult() 를 선언한 변수 resultLauncher 에 담는다.
- registerForActivityResult() 는 인자로 StartActicityForResult() 를 받는다.
- if 문을 통해 result 객체의 resultcode  를 확인한다.
- result의 intent로 부터 getStringExtra로 원하던 아이디와 비밀번호를 받아온다.
- 로그인 화면의 idText 와 pwdText 를 setText()로 받아온 id 와 pwd 의 데이터를 보여준다.

### EditText에 아이디, 비밀번호 중 하나라도 비어있으면 토스트 메세지 출력
![Untitled (2)](https://user-images.githubusercontent.com/97952129/162616187-b8d434e3-8a53-4178-9b6f-7df6569f9668.png)


- binding에서 받아온 idText의 text 가 널값일 경우를 isNullOrBlank()를 통해 확인하였다.
- 둘 중에 하나라도 비어있을 경우 아이디 비밀번호를 확인해달라는 토스트 메세지를 띄웠고

그렇지 않을 경우 로그인 성공 토스트 메세지를 띄운다.

→ 처음에는 if 문에서 binding.idText.text = “” 이렇게 작성했었는데 idText 자체가 String 타입이 아니라는 오류가 발생해서  isNullOrBlank() 를 사용하였다. 

### Activity를 intent 를  통해 넘어오기
![Untitled (3)](https://user-images.githubusercontent.com/97952129/162616206-d3b655c1-2d7a-43b4-a8d7-e1a15e900882.png)



- intent 를 활용하여 SignUpActivity 에서 아이디와 비밀번호의 입력 데이터를  받은 Activity를 반환하여 보여준다.

→ 결과값을 받은 resultLauncher 로 변경해주지 않고 기존의 SignInActivity로 돌아와서 어려움을 겪었었다...

# SignUpActivity

### 회원가입 버튼을 눌렀을 경우

![Untitled (4)](https://user-images.githubusercontent.com/97952129/162616232-8737fe14-b4b1-499e-aeda-e1dee50b27b6.png)



- 이름, 아이디, 비밀번호 중 하나라도 비어있다면 토스트메세지를 출력한다.
- 전부 다 입력되었다면 intent 를 활용하여 id와 pwd 값을 저장한다.
- finish() 함수를 사용하여 Activity 종료한다.

→ stack 처럼 쌓여있어서 바로 전에 있던 SignInActivity 로 넘어간다.

# activity_main_xml 와 activity_sign_up_xml
![Untitled (5)](https://user-images.githubusercontent.com/97952129/162616249-8076aaec-b98b-41c5-a2a7-a58337a1e0c7.png)


- hint 속성을 사용하여 미리보기 글씨를 나타내었다.
- inputType 속성을 사용하였고 “textPassword” 로 입력내용을 가렸다.


---

https://user-images.githubusercontent.com/97952129/162616272-1c7f6851-d7ab-4963-be4b-e53fe579f344.mp4


# activity_home_xml
![Untitled (6)](https://user-images.githubusercontent.com/97952129/162616300-631aabd9-e334-4379-b968-6ab6b330ef16.png)


- ScrollView를 구현하기 위해 전체 레이아웃을 ConstraintLayout 으로 설정하고 ScrollView 안에 ConstraintLayout 을 구현해주었다.
- src 속성을 사용하여 사진을 넣어주었다.
- constraintDimensionRatio 속성을 사용하여 사진의 비율을 1:1 로 맞춰준다.


https://user-images.githubusercontent.com/97952129/162616326-085dce5f-d0ed-4a54-a75c-cbc8d9e8b514.mp4


