package com.example.banana.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banana.R
import com.example.banana.auth.authApplication
import com.example.banana.data.businessCardId
import com.example.banana.data.getCardResponseModel
import com.example.banana.fragment.CardAPI
import com.example.banana.fragment.HomeFragment
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class DetailCardDataViewModel : ViewModel() {
    private lateinit var APIS : API
    private var _cardData = MutableLiveData<getCardResponseModel>()
    var cardData : LiveData<getCardResponseModel> = _cardData

    @SuppressLint("SuspiciousIndentation")
    fun getCardData(cardId: Long) {

        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = authApplication.prefs.getString("accessToken", "")
        viewModelScope.launch {
            try{
                APIS.getCard(
                    token,
                    cardId
                ).enqueue(object : retrofit2.Callback<getCardResponseModel> {
                    override fun onFailure(call: Call<getCardResponseModel>, t: Throwable) {
                        Log.e("TAG", "sendOnFailure: ${t.fillInStackTrace()}")
                    }

                    override fun onResponse(
                        call: Call<getCardResponseModel>,
                        response: Response<getCardResponseModel>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("TAG - isSuccessful", response.body().toString())
                               _cardData.value = response.body()!!

                        } else {
                            Log.d("TAG - failed", response.code().toString())

                        }
                    }

                })
            } catch (e:Exception) {
                Log.d("cardID response : ", "Fail 3")
            }
        }

    }
}