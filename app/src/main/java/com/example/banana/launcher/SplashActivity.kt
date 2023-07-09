package com.example.banana.launcher

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import com.example.banana.activity.MainActivity
import com.example.banana.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var top1 = findViewById<TextView>(R.id.top1_title);
        var top2 = findViewById<TextView>(R.id.top2_title);
        var top3 = findViewById<TextView>(R.id.top3_title);
        var down1 = findViewById<TextView>(R.id.down1_title);
        var down2 = findViewById<TextView>(R.id.down2_title);
        var down3 = findViewById<TextView>(R.id.down3_title);

        ObjectAnimator.ofFloat(top1, "translationY", -100f).apply {
            duration = 1000
            start()
        }
        ObjectAnimator.ofFloat(top2, "translationY", -200f).apply {
            duration = 1000
            start()
        }
        ObjectAnimator.ofFloat(top3, "translationY", -300f).apply {
            duration = 1000
            start()
        }

        ObjectAnimator.ofFloat(down1, "translationY", 100f).apply {
            duration = 1000
            start()
        }
        ObjectAnimator.ofFloat(down2, "translationY", 200f).apply {
            duration = 1000
            start()
        }
        ObjectAnimator.ofFloat(down3, "translationY", 300f).apply {
            duration = 1000
            start()
        }

        Handler().postDelayed({
            // fade_in, fade_out xml 위치 이동 필요
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.drawable.fade_in, R.drawable.fade_out);
            finish()
        }, 3000)

    }
}