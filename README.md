# Seminar04

---

## 

# ✔️POSTMAN 테스트


<img width="849" alt="스크린샷 2022-05-13 오후 7 37 08" src="https://user-images.githubusercontent.com/97952129/168267943-eea2febc-e614-4daf-9b91-b82c227f4198.png">


- 회원가입 서버 연결

<img width="870" alt="스크린샷 2022-05-13 오후 7 42 33" src="https://user-images.githubusercontent.com/97952129/168267889-74883b31-e0f2-4477-8a11-3c4add815000.png">


- 애뮬에서 회원가입한 아이디로 로그인 서버 연결

---

# ✔️회원가입 완료
<img src="https://user-images.githubusercontent.com/97952129/168303390-208d12a9-40d9-46b1-91bc-c57a867d066f.gif" width ="300" height="500"/>

---

# ✔️로그인 구현
<img src="https://user-images.githubusercontent.com/97952129/168268256-4839b6b0-e219-4e34-ae50-fa346f943c0e.gif" width="300" height="500"/>


---

## Interface(SoptService)

```kotlin
interface SoptService {
    @POST("auth/signin")
    fun postLogin(
        @Body body: RequestSignIn
    ): Call<ResponseSignIn>

    @POST("auth/signup")
    fun postSignUp(
        @Body body: RequestSignUp
    ): Call<ResponseSignUp>
}
```

- 서버에게 데이터를 제출하는 @POST 사용함, API의 url 을 작성해줌
- @BODY 안에 Call<T> 형식으로 응답될 body 타입의 data class를 불러옴
- 여기서 Response 에 해당하는 객체는 json object 타입
- SoptService 안에  로그인과 회원가입 둘다 작성해줌

---

## ServiceCreator

```kotlin
object ServiceCreator {
    private  const val BASE_URL = "http://13.124.62.236/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val soptService: SoptService = retrofit.create(SoptService::class.java)
}
```

- object 를 활용하여 어디서든 접근 가능하게 만들어줌
- gson 컨버터 연동을 해주어야함,,꼭,,
- SoptService 에 있는 인터페이스 객체를 create에 넘겨주기

---

## SignIn( 로그인 data class )

### ResponseSignIn

```kotlin

data class ResponseSignIn(
    val status: Int,
    val message: String,
    val data: Data
){
    data class Data(
        val name: String,
        val email: String
    )
}

```

- 서버로부터 받아와 ResponseData로 변환해주기

### RequestSignIn

```kotlin
data class RequestSignIn(
    val email :String,
    val password : String
)

```

- gsonData로 변환시켜 서버에게 전달하기

---

## SignUp( 회원가입 data class )

### ResponseSignUp

```kotlin
data class ResponseSignUp(
    val status: Int,
    val message: String,
    val data: Data
) {
    data class Data(
        val id : Int
    )
}
```

### RequestSignUp

```kotlin
data class RequestSignUp(
    val name:String,
    val email:String,
    val password : String

)
```

---

### SignInActivity

```kotlin
private fun loginNetwork(){
        val requestSignIn = RequestSignIn(
            email = binding.mainEditId.text.toString(),
            password = binding.mainEditPwd.text.toString()
        )
//        Log.d(TAG, "loginNetwork: ${binding.mainEditId.text}, ${binding.mainEditPwd}")
        val call: Call<ResponseSignIn> = ServiceCreator.soptService.postLogin(requestSignIn)

        call.enqueue(object : Callback<ResponseSignIn> {
            override fun onResponse(
                call: Call<ResponseSignIn>,
                response: Response<ResponseSignIn>
            ){
                if(response.isSuccessful) {
                    val data = response.body()?.data

                    Toast.makeText(this@SignInActivity,"${data?.email}님 반갑습니다!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignInActivity,HomeActivity::class.java))
                }else{
                    Toast.makeText(this@SignInActivity,"로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@SignInActivity,"onresponse else", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }
        })

    }
```

- requestSignIn 변수에 editText에서 받아온 email 과 password 를 넣어줌
- call 변수에 만들어준 interface에 접근하여 Call 객체 받아옴
- onResponse() : 서버에서 데이터를 잘받아왔다면 토스트메세지 띄우고 HomeActivity 로 이동

만약, 실패했다면 실패했다는 토스트 메세지 띄우기

---

### SignUpActivity

```kotlin
private fun signUpNetwork(){
    val requestSignUp = RequestSignUp(
        name = binding.signUpEditName.text.toString(),
        email = binding.signUpEditId.text.toString(),
        password = binding.signUpEditPwd.text.toString()
    )

    val call: Call<ResponseSignUp> = ServiceCreator.soptService.postSignUp(requestSignUp)

    call.enqueue(object : Callback<ResponseSignUp> {
        override fun onResponse(
            call: Call<ResponseSignUp>,
            response: Response<ResponseSignUp>
        ){

            if(response.isSuccessful) {
                val data = response.body()?.data
                Toast.makeText(this@SignUpActivity,"${data?.id}님 반갑습니다!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@SignUpActivity,"회원가입 실패", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@SignUpActivity,"onresponse else", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
            Log.e("NetworkTest","error:$t")
        }
    })

}
```

- 위의 SignInActivity의 loginNetwork() 와 비슷한 구현 방법

---

# 깃허브 연동하기

## Interface

```kotlin
interface GithubService {
    @GET("users/{username}/following")
    fun getUserInfo(@Path("username")username:String): Call<List<ResponseFollowerInfo>>
}
```

- 서버에서 데이터를 불러오는 @GET 사용
- API 문서의 어노테이션 작성
- @Path(”동적으로 변하는 변수")
- Call<T> 객체 불러오기 → 이때 불러오는 객체는 json Array 여서 List<ResponseFollowerInfo> 로 불러와줌

---

## GithubServiceCreator

```kotlin
object GithubServiceCreator {
    private const val BASE_URL = "https://api.github.com/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        val githubService: GithubService = retrofit.create(GithubService::class.java)

}
```

---

## ResponseFollowerInfo

```kotlin
data class ResponseFollowerInfo(

    val login: String,
    val avatar_url: String

)

```

- 서버에게 응답 받을 깃허브 아이디와 프로필 사진 data class 작성

---

## FollowerFragment

```kotlin
private fun followerUserNetwork(){

    val call: Call<List<ResponseFollowerInfo>> = GithubServiceCreator.githubService.getUserInfo("jinsilWoo")

    call.enqueue(object : Callback<List<ResponseFollowerInfo>> {

            override fun onResponse(
                call: Call<List<ResponseFollowerInfo>>,
                response: Response<List<ResponseFollowerInfo>>
            ) {
                val data = response.body()

                followerAdapter = FollowerAdapter()
                _binding?.rvFollower?.adapter= followerAdapter

                if (data != null) {
                    followerAdapter.followerList = data.toMutableList()
                }
                followerAdapter.notifyDataSetChanged()

            }

            override fun onFailure(call: Call<List<ResponseFollowerInfo>>, t: Throwable) {
                Log.e("GitHubTest", "error:$t")
            }
        }
    )
}

```

- call 변수에 GithubServiceCreator 의 getUserInfo(”계정이름”) 넣어줌
- 서버 연결이 되었다면 followerAdapter 의 followerList에 서버에서 받은 데이터를 넣어줌
- 이 때, data 의 형식은 toMutableList() 로 해줌
