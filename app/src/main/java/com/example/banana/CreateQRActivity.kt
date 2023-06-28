package com.example.banana

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.banana.data.ResponseGetQRCode
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import retrofit2.Call
import retrofit2.Response
import java.io.InputStream
import javax.security.auth.callback.Callback

class CreateQRActivity : AppCompatActivity() {


    private lateinit var retAPI :API

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_qractivity)

        //API 호출
        retAPI = RetrofitInstance.retrofitInstance().create(API::class.java)

        val btn_x = findViewById<ImageView>(R.id.btn_x)
        //qr 생성
//        try {
//            val barcodeEncoder = BarcodeEncoder()
//            val bitmap = barcodeEncoder.encodeBitmap("https://www.naver.com/", BarcodeFormat.QR_CODE, 400, 400)
//            val imageViewQrCode: ImageView = findViewById<View>(R.id.imageViewQrCode) as ImageView
//
//            getQRCode()
//
//            imageViewQrCode.setImageBitmap(bitmap)
//        } catch (e: Exception) {
//        }

        //뒤로가기 버튼
        btn_x.setOnClickListener{
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }
        getQRCode()

    }

    val accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg3OTM3MjQ3LCJleHAiOjE2OTA1MjkyNDd9.aiKbUg52Uj0rSvQTumCd_pfvc_SOlk6C4xKcaN1tZbE"
    private fun getQRCode() {
        retAPI.getQRCode(accessToken).enqueue(object : retrofit2.Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {



                    var imageString = response.body()
//                    var toBitmap = BitmapFactory.decodeByteArray(image,0,image!!.size)
//                    var bitmap = BitmapFactory.decodeStream(imageString)
//                    val imageBytes = Base64.decode(imageString,0)
//                    val image = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.size)
                    val imageViewQrCode: ImageView = findViewById<View>(R.id.imageViewQrCode) as ImageView
//
//                    imageViewQrCode.setImageBitmap(image)
                    Glide.with(this@CreateQRActivity)
                        .load(imageString)
                        .into(imageViewQrCode)
                    Log.d("getQr Response : ", "success")

                } else {
                    Log.d("getQr Response : ", "Code: ${response.code()} , Message: ${response.message()} , Success: ${response.isSuccessful}")
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("getQr Response : ", "Fail 2")
            }

        })
    }
}