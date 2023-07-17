package com.example.banana.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banana.auth.LoginRepository
import com.example.banana.auth.authApplication
import com.example.banana.data.businessCardId
import com.example.banana.data.comment
import com.example.banana.fragment.HomeFragment
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MyCardIdViewModel : ViewModel() {
    private lateinit var APIS : API
    private var _CardIds = MutableLiveData<ArrayList<businessCardId>>()
    var requestCode = 0
    var CardIds : LiveData<ArrayList<businessCardId>> = _CardIds

    @SuppressLint("SuspiciousIndentation")
    fun  getCardIds() {
        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = authApplication.prefs.getString("accessToken", "")
        viewModelScope.launch {
            try{
                APIS.getMyCards(token).enqueue(object : retrofit2.Callback<ArrayList<businessCardId>> {
                    override fun onResponse(call: Call<ArrayList<businessCardId>>, response: Response<ArrayList<businessCardId>>) {
                        if (response.isSuccessful) {
                            _CardIds.value = response.body()
                            Log.d("cardID" , response.body().toString())

                        }else {
                            if(response.code() == 401) {
                                Log.d("Login" , "유효하지 않은 accessToken")
                                requestCode  = LoginRepository().reissue(authApplication.prefs.getString("refreshToken", ""))

                            }
                        }
                    }
                    override fun onFailure(call: Call<ArrayList<businessCardId>>, t: Throwable) {
                        Log.d("cardID", t.message.toString())
                    }

                })

            } catch (e:Exception) {
                Log.d("cardID response : ", "Fail 3")
            }
        }

    }


}