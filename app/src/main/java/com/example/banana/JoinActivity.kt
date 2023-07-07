package com.example.banana

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.banana.auth.LoginRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class JoinActivity : AppCompatActivity() {

    val TAG = "JoinActivity"
    lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        // google 가져오기
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(getString(R.string.server_client_id_forGoogle))
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // google joining
        findViewById<LinearLayout>(R.id.google_join_btn).setOnClickListener {
            googleSignIn()
        }
        // kakao joining
        findViewById<LinearLayout>(R.id.kakao_join_btn).setOnClickListener {
            LoginRepository().kakaoLogin(this)
        }
    }

    fun googleLogout() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                // ...
            }
    }

    fun googleSignIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
        Log.d(TAG, "signIn")
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }else {
            Log.d(TAG, "failed : " + result.resultCode)
        }
    }

    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Log.i(
                TAG, "사용자 정보 요청 성공" +
                        "\n토큰: ${account?.idToken.toString()}" + // 요게 토큰값
                        "\nid: ${account?.id.toString()}" +
                        "\nserverAuthCode: ${account?.serverAuthCode.toString()}" +
                        "\n이메일: ${account?.email.toString()}" +
                        "\n이름: ${account?.displayName.toString()}"
            )
            var intent = Intent(this, FragmentActivity::class.java)
            ContextCompat.startActivity(this, intent, null)
            if(account!=null) {
                LoginRepository().sendGoogleToken(account.idToken.toString())
            }
            googleLogout()
        } catch (e: ApiException) {
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
        }
    }

}