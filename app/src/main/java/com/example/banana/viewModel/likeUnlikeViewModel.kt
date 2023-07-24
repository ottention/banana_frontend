package com.example.banana.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banana.data.Responselike
import com.example.banana.data.comment
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class likeUnlikeViewModel : ViewModel()  {

    private lateinit var APIS : API
    private var _likeInfo = MutableLiveData<Responselike>()
    var likeInfo : LiveData<Responselike> = _likeInfo

    @SuppressLint("SuspiciousIndentation")
    public fun like(businessCardId : Long) {
        Log.d("TAG", "getCommentToSpCard1")
        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNjg5ODU4ODI2LCJleHAiOjE2ODk4ODA0MjZ9.CJUrfSPV7cdEzT_5Ffp8zRc0gzMFKqRGf7Fg581C3hg"
        viewModelScope.launch {
            try{
                APIS.like(token,businessCardId).enqueue(object : retrofit2.Callback<Responselike> {
                    override fun onResponse(call: Call<Responselike>, response: Response<Responselike>) {
                        if (response.isSuccessful) {
                            _likeInfo.value = response.body()
                            Log.d("like - success: " , response.body().toString())
                        } else {
                            Log.d("like : " , response.code().toString())

                        }
                    }
                    override fun onFailure(call: Call<Responselike>, t: Throwable) {
                        Log.d("like Response : ", t.message.toString())
                    }

                })


            } catch (e:Exception) {
                Log.d("getComments response : ", "Fail connected")
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    public fun unlike(businessCardId : Long) {
        Log.d("TAG", "getCommentToSpCard1")
        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNjg5ODU4ODI2LCJleHAiOjE2ODk4ODA0MjZ9.CJUrfSPV7cdEzT_5Ffp8zRc0gzMFKqRGf7Fg581C3hg"
        viewModelScope.launch {
            try{
                APIS.unlike(token,businessCardId).enqueue(object : retrofit2.Callback<Responselike> {
                    override fun onResponse(call: Call<Responselike>, response: Response<Responselike>) {
                        if (response.isSuccessful) {
                            _likeInfo.value = response.body()
                            Log.d("unlike - success: " , response.body().toString())
                        } else {
                            Log.d("unlike: " , response.code().toString())

                        }
                    }
                    override fun onFailure(call: Call<Responselike>, t: Throwable) {
                        Log.d("unlike Response : ", t.message.toString())
                    }

                })


            } catch (e:Exception) {
                Log.d("getComments response : ", "Fail connected")
            }
        }
    }
}