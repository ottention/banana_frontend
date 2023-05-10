package com.example.banana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
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
            if (AuthApiClient.instance.hasToken()) {
                UserApiClient.instance.accessTokenInfo { _, error ->
                    if (error != null) {
                        if (error is KakaoSdkError && error.isInvalidTokenError() == true) {
                            //로그인 필요
                        }
                        else {
                            //기타 에러
                        }
                    }
                    else {
                        //토큰 유효성 체크 성공(필요 시 토큰 갱신됨)
                    }
                }
            }
            else {
                //로그인 필요
            }
            Toast.makeText(this, "회원가입이 필요합니다.", Toast.LENGTH_SHORT).show()
        }

        joinBtn.setOnClickListener{
            Log.d("clicekd", "clicked");
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }
    }
}