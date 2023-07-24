package com.example.banana.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banana.data.ccomment
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class CommentDetailViewModel : ViewModel() {
    private lateinit var APIS : API
    private var _commentId = MutableLiveData<Long>()
    var commentId : LiveData<Long> = _commentId

    @SuppressLint("SuspiciousIndentation")
    public fun addChart(comment: ccomment) {

        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjg5MDUzNTgwLCJleHAiOjE2OTE2NDU1ODB9.0AvbVqizNfP3FpARzZmB2UgbJqCvvZo5Pa7tuGc7Bco"
        viewModelScope.launch {
            try{
                APIS.addComment(token,1, comment).enqueue(object : retrofit2.Callback<Long> {
                    override fun onResponse(call: Call<Long>, response: Response<Long>) {
                        if (response.isSuccessful) {
                                _commentId.value = response.body()
                        } else {
                            Log.d("cardId : " , response.body().toString())

                        }
                    }
                    override fun onFailure(call: Call<Long>, t: Throwable) {
                        Log.d("getComments Response : ", t.message.toString())
                    }

                })


            } catch (e:Exception) {
                Log.d("getComments response : ", "Fail 3")
            }
        }
    }
}