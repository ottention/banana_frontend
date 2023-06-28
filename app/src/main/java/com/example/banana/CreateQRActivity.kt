package com.example.banana

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry.NoModelLoaderAvailableException
import com.example.banana.data.ResponseGetQRCode
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import okhttp3.internal.notify
import retrofit2.Call
import retrofit2.Response

class CreateQRActivity : AppCompatActivity() {


    private lateinit var retAPI :API

    lateinit var imageViewQrCode : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_qractivity)

        //API 호출
//        retAPI = RetrofitInstance.retrofitInstance().create(API::class.java)

        imageViewQrCode = findViewById(R.id.imageViewQrCode)
        val btn_x = findViewById<ImageView>(R.id.btn_x)


        var imageUrl = intent.getStringExtra("QrUrl")
        Log.d("url", imageUrl.toString())

                            Glide.with(this@CreateQRActivity)
                        .load(imageUrl)
                        .into(imageViewQrCode)




        //뒤로가기 버튼
        btn_x.setOnClickListener{
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }
//        getQRCode()

    }

//    val accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg3OTM3MjQ3LCJleHAiOjE2OTA1MjkyNDd9.aiKbUg52Uj0rSvQTumCd_pfvc_SOlk6C4xKcaN1tZbE"
//    private fun getQRCode() {
//        retAPI.getQRCode(accessToken).enqueue(object : retrofit2.Callback<ResponseGetQRCode>{
//            override fun onResponse(call: Call<ResponseGetQRCode>, response: Response<ResponseGetQRCode>) {
//                if (response.isSuccessful) {
//
//
//
//                    var imageString = response.body()
////                    var toBitmap = BitmapFactory.decodeByteArray(image,0,image!!.size)
////                    var bitmap = BitmapFactory.decodeStream(imageString)
////                    val imageBytes = Base64.decode(imageString,0)
////                    val image = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.size)
//                    val imageViewQrCode: ImageView = findViewById<View>(R.id.imageViewQrCode) as ImageView
//
////
////                    imageViewQrCode.setImageBitmap(image)
//                    Glide.with(this@CreateQRActivity)
//                        .load(imageString)
//                        .into(imageViewQrCode)
//
//                    Log.d("getQr Response : ", "success")
//
//                } else {
//                    Log.d("getQr Response : ", "Code: ${response.code()} , Message: ${response.message()} , Success: ${response.isSuccessful}")
//                }
//            }
//            override fun onFailure(call: Call<ResponseGetQRCode>, t: Throwable) {
//                Log.d("getQr Response : ", "Fail 2")
//            }
//
//        })
//    }
}