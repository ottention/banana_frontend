package com.example.banana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

class CreateQRActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_qractivity)


        val btn_x = findViewById<ImageView>(R.id.btn_x)
        //qr 생성
        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap("https://www.naver.com/", BarcodeFormat.QR_CODE, 400, 400)
            val imageViewQrCode: ImageView = findViewById<View>(R.id.imageViewQrCode) as ImageView
            imageViewQrCode.setImageBitmap(bitmap)
        } catch (e: Exception) {
        }

        //뒤로가기 버튼
        btn_x.setOnClickListener{
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }


    }
}