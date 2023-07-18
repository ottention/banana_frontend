package com.example.banana.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banana.data.comment
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class CommentsIwroteViewModel: ViewModel() {

    private lateinit var APIS : API
    private var _commentsList = MutableLiveData<ArrayList<comment>>()
    var commentsList : LiveData<ArrayList<comment>> = _commentsList

    init {

        showCommentsList()

    }

    fun  showCommentsList() {

        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjg5MDUzNTgwLCJleHAiOjE2OTE2NDU1ODB9.0AvbVqizNfP3FpARzZmB2UgbJqCvvZo5Pa7tuGc7Bco"
        viewModelScope.launch {
            try{

                APIS.getCommentsIwrote(token,4).enqueue(object : retrofit2.Callback<ArrayList<comment>> {
                    override fun onResponse(call: Call<ArrayList<comment>>, response: Response<ArrayList<comment>>) {
                        if (response.isSuccessful) {


                            Log.d("getComments Response : ", "success")


                            _commentsList.value = response.body()

                            Log.d("commentList : " , response.body().toString())




                        } else {
                            Log.d("commentList : " , response.body().toString())
                            Log.d("commentList : " , response.message())
                            Log.d("getComments Response : ", "Fail 1")
                        }
                    }
                    override fun onFailure(call: Call<ArrayList<comment>>, t: Throwable) {
                        Log.d("getComments Response : ", t.message.toString())
                    }

                })


            } catch (e:Exception) {
                Log.d("getComments response : ", "Fail 3")
            }
        }

    }

}