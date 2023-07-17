package com.example.banana.auth

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.banana.activity.JoinActivity

import com.example.banana.model.LoginGoogleResponseModel
import com.example.banana.model.LoginKaKaoResponseModel
import com.example.banana.model.reIssueResponseModel
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Response


class LoginRepository {

    val TAG = "LoginRepository"
    private val sendTokenBaseUrl = "http://52.78.202.79:8080/"// 받아오기
    private val APIS = RetrofitInstance.retrofitInstance().create(API::class.java)

    internal val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
        } else if (token != null) {
            UserApiClient.instance.me { user, error ->
                val kakaoId = user!!.id
                Log.i(
                    TAG, "사용자 정보 요청 성공" +
                            "\ntoken: ${token.accessToken}" +
                            "\n회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}"
                )
                sendKakaoToken(token.accessToken)
            }
        }
    }

    fun kakaoLogin(context: Context) : Int {
        // kakao 로그인 시도 -> 앱이 없으면 인터넷으로 앱이 있으면 카카오 로그인 창으로
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인

        var flag = 0;
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    Log.e(TAG, "call back으로 이동")
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    Log.d(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    flag = sendKakaoToken(token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }

        return flag;
    }

    // kakaoToken sending
    fun sendKakaoToken(kakaoToken: String) : Int {
        var flag = 0
        APIS.sendkakaoToken(
            kakaoToken
        ).enqueue(object : retrofit2.Callback<LoginKaKaoResponseModel> {

            override fun onFailure(call: Call<LoginKaKaoResponseModel>, t: Throwable) {
                Log.e(TAG, "sendOnFailure: ${t.fillInStackTrace()}")
            }

            override fun onResponse(
                call: Call<LoginKaKaoResponseModel>,
                response: Response<LoginKaKaoResponseModel>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, "sendOnResponse.accessToken: ${response.body()!!.accessToken}")
                    Log.d(TAG, "sendOnResponse.refreshToken: ${response.body()!!.refreshToken}")
                    Log.d(TAG, response.code().toString())
                    saveJWT(response.body()!!.accessToken,response.body()!!.refreshToken);
                }else {
                    if(response.code().toString() == "401") {
                        reissue(authApplication.prefs.getString("refreshToken", ""))
                        flag = -1
                    }
                    Log.d(TAG, "failed : " + response.body().toString())
                }}

        })

        return flag
    }

    // googleToken sending
    fun sendGoogleToken(idToken: String) : Int {
        var flag = 0
        APIS.sendGoogleToken(
            idToken
        ).enqueue(object : retrofit2.Callback<LoginGoogleResponseModel> {
            override fun onFailure(call: Call<LoginGoogleResponseModel>, t: Throwable) {
                Log.e(TAG, "sendOnFailure: ${t.fillInStackTrace()}")
            }
            override fun onResponse(
                call: Call<LoginGoogleResponseModel>,
                response: Response<LoginGoogleResponseModel>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, response.code().toString())
                    Log.d(TAG, "sendOnResponse.accessToken: ${response.body()!!.accessToken}")
                    Log.d(TAG, "sendOnResponse.refreshToken: ${response.body()!!.refreshToken}")
                    saveJWT(response.body()!!.accessToken,response.body()!!.refreshToken);
                    Log.d(TAG, "${authApplication.prefs.getString("accessToken", "")}")
                } else {
                    if(response.code().toString() == "401") {
                        Log.d("Login 처음 요청", "실패")
                    }
                }
            }

        })
        return flag
    }

    fun reissue(requestToken : String) : Int {
        var flag = 0
        APIS.reissue(
            requestToken
        ).enqueue(object : retrofit2.Callback<reIssueResponseModel> {

            override fun onFailure(call: Call<reIssueResponseModel>, t: Throwable) {
                Log.e(TAG, "sendOnFailure: ${t.fillInStackTrace()}")
            }
            override fun onResponse(
                call: Call<reIssueResponseModel>,
                response: Response<reIssueResponseModel>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, "sendOnResponse.accessToken: ${response.body()!!.accessToken}")
                    updateJWT(response.body()!!.accessToken);

                } else {
                    Log.d(TAG, response.code().toString())
                    removeJWT()
                    flag = -1
                }
            }

        })

        return flag
    }


//    public fun getQRCode() {
//        LoginService.loginRetrofit(sendTokenBaseUrl).getQRCode().enqueue(object : retrofit2.Callback<ResponseGetQRCode>{
//            override fun onResponse(call: Call<ResponseGetQRCode>, response: Response<ResponseGetQRCode>) {
//                if (response.isSuccessful) {
//                    var imageString = response.body()?.qrImage
////                    var toBitmap = BitmapFactory.decodeByteArray(image,0,image!!.size)
////                    var bitmap = BitmapFactory.decodeStream(imageString)
//                    val imageBytes = Base64.decode(imageString,0)
//                    val image = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.size)
////                    val imageViewQrCode: ImageView = findViewById<View>(R.id.imageViewQrCode) as ImageView
////                    imageViewQrCode.setImageBitmap(image)
//                    Log.d("getQr Response : ", "goood 1")
//
//                } else {
//                    Log.d("getQr Response : ", "Fail 1")
//                }
//            }
//            override fun onFailure(call: Call<ResponseGetQRCode>, t: Throwable) {
//                Log.d("getQr Response : ", "Fail 2")
//            }
//
//        })
//    }



        fun saveJWT(accessToken: String, refreshToken: String) {
            Log.d("Login : ", "saveJWT")
            authApplication.prefs.setString("accessToken", accessToken)
            authApplication.prefs.setString("refreshToken", refreshToken)
        }

        fun updateJWT(accessToken: String){
            Log.d("Login : ", "updateJWT")
            authApplication.prefs.setString("accessToken", accessToken)
        }

        // 로그아웃
        fun removeJWT() {
            Log.d("Login : ", "removeJWT")
            authApplication.prefs.setString("accessToken", "")
            authApplication.prefs.setString("refreshToken", "")
        }

}



