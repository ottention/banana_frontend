package com.example.banana

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.core.motion.utils.Utils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient



class JoinActivity : AppCompatActivity() {

    val TAG = "JoinActivity"
    private lateinit var mGoogleSignInClient : GoogleSignInClient

    internal val callback : (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Toast.makeText(this,"로그인실패", Toast.LENGTH_SHORT).show()
        } else if (token != null) {
            UserApiClient.instance.me { user, error ->
                val kakaoId = user!!.id
                Log.i(TAG, "사용자 정보 요청 성공" +
                        "\ntoken: ${token.accessToken}" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}")
                goHome(this)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)


        // google 가져오기
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(getString(R.string.server_client_id_forGoogle))
            .requestServerAuthCode(getString(R.string.server_client_id_forGoogle))
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // google joining
        findViewById<LinearLayout>(R.id.google_join_btn).setOnClickListener {
            signIn()
        }
        // kakao joining
        findViewById<LinearLayout>(R.id.kakao_join_btn).setOnClickListener {

            // kakao 로그인 시도 -> 앱이 없으면 인터넷으로 앱이 있으면 카카오 로그인 창으로
            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        Log.e(TAG, "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if (token != null) {
                        Log.d(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                        goHome(this)
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }

    fun goHome(context : Context) {
//        intent = Intent(context, HomeActivity::class.java)
//        startActivity(intent)
    }

    private fun signIn(){
        val signInIntent = mGoogleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
        Log.d(TAG, "signIn")
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        } else {
            Log.d(TAG, "something wrong")
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>){
        try {
            // token이 안받아진다는 문제.. 네...
            val account = completedTask.getResult(ApiException::class.java)
            Log.i(TAG, "사용자 정보 요청 성공" +
                    "\n토큰: ${account?.idToken.toString()}" + // 요게 토큰값
                    "\n아이디: ${account?.id.toString()}" +
                    "\nid: ${account?.id.toString()}" +
                    "\nserverAuthCode: ${account?.serverAuthCode.toString()}" +
                    "\n이메일: ${account?.email.toString()}" +
                    "\n이름: ${account?.displayName.toString()}")
            goHome(this)
            googleLogout()
        } catch (e: ApiException){
            Log.w("failed", "signInResult:failed code=" + e.statusCode)
        }
    }

    private fun googleLogout() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                // 로그아웃 성공시 실행
                // 로그아웃 이후의 이벤트들(토스트 메세지, 화면 종료)을 여기서 수행하면 됨

            }
    }

    fun kakaoLogout() {
        // 로그아웃
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e("Hello", "로그아웃 실패", error)
            } else {
                Log.i("Hello", "로그아웃 성공") }
        }
    }
}