package com.example.banana.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.banana.model.LoginGoogleResponseModel
import com.example.banana.model.LoginKaKaoResponseModel
import com.example.banana.model.reIssueResponseModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.security.AccessController.getContext


class LoginRepository {

    val TAG = "LoginRepository"
    private val getAccessTokenBaseUrl = "https://www.googleapis.com"
    private val sendTokenBaseUrl = "http://3.37.86.26:8080/" // 받아오기


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

    fun kakaoLogin(context: Context) {
        // kakao 로그인 시도 -> 앱이 없으면 인터넷으로 앱이 있으면 카카오 로그인 창으로
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인

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
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    Log.d(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    sendKakaoToken(token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }

    fun goHome(context: Context) {
//        intent = Intent(context, HomeActivity::class.java)
//        startActivity(intent)
    }



    // kakaoToken sending
    fun sendKakaoToken(kakaoToken: String) {
        LoginService.loginRetrofit(sendTokenBaseUrl).sendkakaoToken(
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
                    }
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())
                        Log.d(TAG, "kakao : "+ jObjError.getJSONObject("error").getString("message"))
                    } catch (e: Exception) {
                        Log.d(TAG, e.message.toString())
                    }
                    Log.d(TAG, "failed : " + response.body().toString())
                }}

        })
    }

    // googleToken sending
    fun sendGoogleToken(idToken: String) {
        LoginService.loginRetrofit(sendTokenBaseUrl).sendGoogleToken(
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
                        reissue(authApplication.prefs.getString("userId", ""))
                    }
                    Log.d(TAG, response.code().toString())
                }
            }

        })
    }


    fun reissue(requestToken : String) {
        LoginService.loginRetrofit(sendTokenBaseUrl).reissue(
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
                }
            }

        })
    }



        fun saveJWT(accessToken: String, refreshToken: String) {
            authApplication.prefs.setString("accessToken", accessToken)
            authApplication.prefs.setString("refreshToken", refreshToken)
        }

        fun updateJWT(accessToken: String){
            authApplication.prefs.setString("accessToken", accessToken)
        }

        // 로그아웃
        fun removeJWT() {
        authApplication.prefs.setString("accessToken", "")
        authApplication.prefs.setString("refreshToken", "")
    }


        // 구글 로그인에서 accessToken값 가져오기 ---------------
//    fun getAccessToken(authCode: String) {
//        LoginService.loginRetrofit(getAccessTokenBaseUrl).getAccessToken(
//            request = LoginGoogle_at_RequestModel(
//                // 수정해야함 -> string으로 빼기
//                grant_type = "authorization_code",
//                client_id = "418803016352-m76e2uci5o15ode6am21dpqf602uogkq.apps.googleusercontent.com",
//                client_secret = "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",
//                code = authCode.orEmpty()
//            )
//        ).enqueue(object : retrofit2.Callback<LoginGoogle_at_ResponseModel> {
//            override fun onResponse(
//                call: Call<LoginGoogle_at_ResponseModel>,
//                response: Response<LoginGoogle_at_ResponseModel>
//            ) {
//                if (response.isSuccessful) {
//                    val accessToken = response.body()?.access_token.orEmpty()
//                    Log.d(TAG, "accessToken: $accessToken")
//                } else {
//                    Log.d(TAG, "failed")
//                }
//            }
//
//            override fun onFailure(call: Call<LoginGoogle_at_ResponseModel>, t: Throwable) {
//                Log.e(TAG, "getOnFailure: ", t.fillInStackTrace())
//            }
//        })
//    }

}



