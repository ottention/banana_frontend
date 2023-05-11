package com.example.banana

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("key", "keyhash : ${Utility.getKeyHash(this)}")

        val joinBtn = findViewById<Button>(R.id.join_button)
        val launchBtn = findViewById<Button>(R.id.launch_button)

        launchBtn.setOnClickListener {


            // 1. 구글 로그인 정보 먼저 찾음
            // 2. 있다면 구글 로그인, 없다면 카카오 로그인 정보 찾음
            // 3. 둘 다 없으면, joinActivity로 감

            val account = this?.let { GoogleSignIn.getLastSignedInAccount(it) }
            if (account != null) {
                // 구글 로그인 성공!
                Toast.makeText(
                    this,
                    "로그인 정보 있음. 구글 로그인 \n token : " + account.idToken,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                if (AuthApiClient.instance.hasToken()) {
                    UserApiClient.instance.accessTokenInfo { token, error ->
                        if (error != null) {
                            if (error is KakaoSdkError && error.isInvalidTokenError() == true) {
                                goJoin(this)
                            } else {
                                //기타 에러
                            }
                        } else {
                            Toast.makeText(this, "로그인 정보 있음. 카카오 로그인" + token, Toast.LENGTH_SHORT).show()
                            Log.d("MainActivty", token.toString())
                            // 이거 테스트 하려면 카카오 앱 필요... 흑흑
                        }
                    }
                } else {
                    goJoin(this)
                }
            }

            joinBtn.setOnClickListener {
                goJoin(this)
            }
        }
    }

        fun goJoin(context: Context) {
            Log.d("clicekd", "clicked");
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }
    }