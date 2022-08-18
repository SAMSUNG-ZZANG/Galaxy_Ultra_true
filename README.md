# 🖤Seminar07

# ✔️ 자동 로그인 구현

- SharedPreferences 를 사용하여 자동 로그인을 구현
    
     -key-value 를 이용하여 간단한 데이터 저장이 가능한 DB
    
    ### SOPTSharedPreferences.kt
    
    ```kotlin
    object SOPTSharedPreferences {
        private const val STORAGE_KEY = "USER_AUTH"
        private const val AUTO_LOGIN = "AUTO_LOGIN"
    
        fun init(context: Context):SharedPreferences{
            return context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE) //shared 에 있는 STORAGE_KEY 데이터를 불러옴
    	//MODE_PRIVATE 앱 안 어디에서든지 불러올 수 있는 상태
        }
    
    //로그인
        //파일 읽기
        fun getAutoLogin(context: Context):Boolean{
            return init(context).getBoolean(AUTO_LOGIN, false)
        }
    
        //파일 쓰기
        fun setAutoLogin(context: Context,value: Boolean){
            init(context).edit() //파일 수정
                .putBoolean(AUTO_LOGIN, value) 
                .apply()
        }
    
    //로그아웃
        fun setLogout(context: Context): Boolean{
            init(context).edit() //파일 수정
                .remove(AUTO_LOGIN) //key 값에 해당하는 value 삭제
                .clear() //모든 값을 지움
                .apply()
    
            return init(context).getBoolean(AUTO_LOGIN, false)
        }
    
    }
    ```
    

---

### SignInActivity.kt

```kotlin
private fun initClickEvent(){
     binding.ivSignInCheckbox.setOnClickListener{
         binding.ivSignInCheckbox.isSelected=!binding.ivSignInCheckbox.isSelected // 버튼 클릭 여부에 따라 selector 변환
SOPTSharedPreferences.setAutoLogin(this,binding.ivSignInCheckbox.isSelected)
//체크 되면 true, SharedPreferences에 저장
}

}

private fun isAutoLogin() {
     if(SOPTSharedPreferences.getAutoLogin(this)){
showToast("자동로그인 되었습니다")
         startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
         finish() // 저장된 값 true 면 HomeActivity로 이동
     }
 }
```

### activity_main.xml

```xml
<androidx.appcompat.widget.AppCompatButton
    android:id="@+id/iv_signIn_checkbox"
    android:background="@drawable/select_checkbox"
    android:layout_width="20dp"
    android:layout_height="20dp"
    android:layout_marginEnd="100dp"
    android:layout_marginTop="25dp"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintTop_toBottomOf="@+id/mainImage" />

<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="170dp"
    android:layout_marginEnd="25dp"
    android:fontFamily="@font/noto_sans_medium_kr"
    android:text="@string/auto_login"
    android:textColor="@color/black"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

  

### select_checkbox.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@drawable/ic_checkbox_off" android:state_selected="false" />
    <item android:drawable="@drawable/ic_checkbox_on" android:state_selected="true" />
</selector>
```

- SignInActivity에 자동로그인 로직 구현
    
       - 자동로그인 버튼 배치 (activity_main.xml)
    
       - selector 함수를 사용하여 클릭 여부에 따른 이미지 변화 (select_checkbox.xml)
    
       - isSelected 값에 따라 자동로그인 구현(SignInActivity.kt)
    

---

# ✔️ 필수과제_자동 로그인 해제

- 프로필 화면에서 설정 버튼을 누르면 SettingActivity 로 이동
- 자동로그인 버튼을 누르면 자동로그인 해제 및 SignInActivity 로 이동

 

### ProfileFragment.kt

```kotlin
private fun settingClickEvent(){
    _binding?.btnProfileSetting?.setOnClickListener{
val intent = Intent(activity, SettingActivity::class.java)
            startActivity(intent)
	}
}
```

  - Fragment 에서 Activity 로 이동

 - Fragment 에서는 Context 를 못받아오기 때문에 Intent(this 가아니라 Intent(activity 로 해주기

### SettingActivity.kt

```kotlin
class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isAutoLogout()
    }

//자동 로그인
    private fun isAutoLogout() {
        binding.btnSettingLogout.setOnClickListener{ // 자동로그인 해제 버튼 눌렀을 때
SOPTSharedPreferences.setLogout(this) //setLogout() 
						showToast("자동 로그인 해제")
            startActivity(Intent(this@SettingActivity, SignInActivity::class.java)) //SignInActivity 로 이동
            finish()
	}
}

```

---

# ✔️ 성장과제_온보딩 화면 구현하기

- NavigationComponent 를 사용하여 Fragment 간의 전환을 보다 더 간단하고 안정적이게 함
- Fragment 간 어떻게 교체가 이루어지는지 drag&drop 을 통해 Navigation Graph 에 작성
    
    ![스크린샷 2022-06-10 오후 5.56.07.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3acfa5e5-b004-49e6-af81-166ae6b5ed01/스크린샷_2022-06-10_오후_5.56.07.png)
    

### nav_sample.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_sample"
    app:startDestination="@id/sampleFragment1"> //처음 시작

    <fragment
        android:id="@+id/sampleFragment1"
        android:name="com.example.sopt_main.ui.fragment.onboarding.SampleFragment1"
        android:label="fragment_sample1"
        tools:layout="@layout/fragment_sample1" >
        <action 
            android:id="@+id/action_sampleFragment1_to_sampleFragment2"
            app:destination="@id/sampleFragment2" /> // 이동
    </fragment>
    <fragment
        android:id="@+id/sampleFragment2"
        android:name="com.example.sopt_main.ui.fragment.onboarding.SampleFragment2"
        android:label="fragment_sample2"
        tools:layout="@layout/fragment_sample2" >
        <action
            android:id="@+id/action_sampleFragment2_to_sampleFragment3"
            app:destination="@id/sampleFragment3" />
    </fragment>
    <fragment
        android:id="@+id/sampleFragment3"
        android:name="com.example.sopt_main.ui.fragment.onboarding.SampleFragment3"
        android:label="fragment_sample3"
        tools:layout="@layout/fragment_sample3" />
</navigation>
```

### SampleFragment1.kt

 

```kotlin
binding.btnNext.setOnClickListener{
findNavController().navigate(R.id.action_sampleFragment1_to_sampleFragment2)
}
```

 

- 위와 같은 함수를 추가해주면 fragment 간의 이동 구현
- navigate(이동할 Destination Id 또는 Action Id)

---
=======

# ✔️ 그 외

- 확장함수를 활용한 showToast()
- 로그인과 회원가입 서버통신을 확장함수 사용해보기

### SignInActivity.kt

```kotlin
private fun isAutoLogin() {
        if(SOPTSharedPreferences.getAutoLogin(this)){
            showToast("자동로그인 되었습니다") //확장함수 사용
            startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
            finish()
        }
    }

//확장함수
    fun Context.showToast(msg:String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
```

### ResponseType.kt

```kotlin
fun <ResponseType> Call<ResponseType>.enqueueUtil(
    onSuccess: (ResponseType) -> Unit,
    onError : ((stateCode:Int) -> Unit)? = null
){
    this.enqueue(object : Callback<ResponseType> {
        override fun onResponse(call: Call<ResponseType>, response: Response<ResponseType>) {
            if(response.isSuccessful){
                onSuccess.invoke(response.body() ?: return)
            }else {
                onError?.invoke(response.code())
            }
        }

        override fun onFailure(call: Call<ResponseType>, t: Throwable) {
            Log.d("NetworkTest","error:$t")
        }
    })
}
```

### SignInActivity.kt

```kotlin
private fun loginNetwork(){
    val requestSignIn = RequestSignIn(
        email = binding.mainEditId.text.toString(),
        password = binding.mainEditPwd.text.toString()
    )

    val call: Call<ResponseSignIn> = ServiceCreator.soptService.postLogin(requestSignIn)

//확장함수를 활용
    call.enqueueUtil(
        onSuccess ={
Toast.makeText(this@SignInActivity,"${it.data?.email}님 반갑습니다!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
},
        onError ={
Toast.makeText(this@SignInActivity,"로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            Toast.makeText(this@SignInActivity,"onresponse else", Toast.LENGTH_SHORT).show()
}
)
}
```

## 실행화면

https://user-images.githubusercontent.com/97952129/173033891-00585299-1ec3-4d12-8e40-432ae2eb1d46.mov
