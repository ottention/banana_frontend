package com.example.banana.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banana.auth.LoginRepository
import com.example.banana.auth.authApplication
import com.example.banana.data.cardRequestData
import com.example.banana.data.AddCardDataResponseModel
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import org.apache.commons.lang3.ObjectUtils
import retrofit2.Call
import retrofit2.Response

class AddCardViewModel  : ViewModel() {
    private lateinit var APIS : API
    private var _cardId = MutableLiveData<AddCardDataResponseModel>()
    var cardId : LiveData<AddCardDataResponseModel> = _cardId

    @SuppressLint("SuspiciousIndentation")
    fun updateCardData(cardId : Long, card: cardRequestData) {
        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
//        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg5MDUzNTgwLCJleHAiOjE2OTE2NDU1ODB9.I3ART9XCYkp1l7YnC6cGv6uMvCwBqsqcUW2r1GXMKx4"
        val token = authApplication.prefs.getString("accessToken", "")
        viewModelScope.launch {
            try{
                APIS.updateMyCard(
                    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg5MDUzNTgwLCJleHAiOjE2OTE2NDU1ODB9.I3ART9XCYkp1l7YnC6cGv6uMvCwBqsqcUW2r1GXMKx4",
                    cardId,card
                ).enqueue(object : retrofit2.Callback<ObjectUtils.Null> {
                    override fun onFailure(call: Call<ObjectUtils.Null>, t: Throwable) {
                        Log.e("TAG", "sendOnFailure: ${t.fillInStackTrace()}")
                    }

                    override fun onResponse(
                        call: Call<ObjectUtils.Null>,
                        response: Response<ObjectUtils.Null>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("TAG : update - isSuccessful", response.body().toString())
                        } else {
                            Log.d("TAG - failed", response.code().toString())
                            LoginRepository().reissue(authApplication.prefs.getString("refreshToken", ""))
                        }
                    }

                })
            } catch (e:Exception) {
                Log.d("cardID response : ", "Fail 3")
            }
        }

    }

    fun saveCardData(card: cardRequestData) {
        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
//        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg5MDUzNTgwLCJleHAiOjE2OTE2NDU1ODB9.I3ART9XCYkp1l7YnC6cGv6uMvCwBqsqcUW2r1GXMKx4"
        val token = authApplication.prefs.getString("accessToken", "")

        viewModelScope.launch {
            try{
                APIS.saveMyCard(
                    "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg5MDUzNTgwLCJleHAiOjE2OTE2NDU1ODB9.I3ART9XCYkp1l7YnC6cGv6uMvCwBqsqcUW2r1GXMKx4",
                    card
                ).enqueue(object : retrofit2.Callback<AddCardDataResponseModel> {
                    override fun onFailure(call: Call<AddCardDataResponseModel>, t: Throwable) {
                        Log.e("TAG", "sendOnFailure: ${t.fillInStackTrace()}")
                    }

                    override fun onResponse(
                        call: Call<AddCardDataResponseModel>,
                        response: Response<AddCardDataResponseModel>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("TAG - make card isSuccessful", response.body().toString())
                            _cardId.value = response.body()!!

                        } else {
                            Log.d("TAG - failed", response.code().toString())
                            LoginRepository().reissue(authApplication.prefs.getString("refreshToken", ""))
                        }
                    }

                })
            } catch (e:Exception) {
                Log.d("cardID response : ", "Fail 3")
            }
        }

    }
}