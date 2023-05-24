package com.example.banana

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.banana.auth.authApplication
import com.kakao.sdk.common.util.Utility


class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 처음 어플 켜서 아직 정보가 있다면? -> 바로 시작
        // 없다면? 시작하기 혹은 회원가입
        if ((authApplication.prefs.getString("accessToken", "") != " ")) {
            Log.d(TAG,"jwt : " + authApplication.prefs.getString("accessToken", ""))
//            LoginRepository().goHome(this);
            val intent = Intent(this, testActivty::class.java )
            startActivity(intent)
        }
        Log.d(TAG, "keyhash : ${Utility.getKeyHash(this)}")

        val joinBtn = findViewById<Button>(R.id.join_button)
        val launchBtn = findViewById<Button>(R.id.launch_button)

        launchBtn.setOnClickListener {
            goJoin(this)
        }

        joinBtn.setOnClickListener {
            goJoin(this)
        }
    }

    fun goJoin(context: Context) {
        val intent = Intent(this, JoinActivity::class.java)
        startActivity(intent)
    }

}