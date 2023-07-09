package com.example.banana.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banana.data.CommentsData
import com.example.banana.data.ResponsegetMyCardComments
import com.example.banana.data.comment
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MyCardCommentsViewModel : ViewModel() {
    private lateinit var APIS : API
    private var _commentsList = MutableLiveData<ArrayList<comment>>()
    var commentsList : LiveData<ArrayList<comment>> = _commentsList



    init {

            showCommentsList()

    }

    private fun  showCommentsList() {

        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        var token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMyIsImlhdCI6MTY4ODUzOTAzNywiZXhwIjoxNjg4NTYwNjM3fQ.kbIjEqKu3uvk9NSjvCoNrR8z-quXg0qADkpJU9mgnqk"

        viewModelScope.launch {
            try{

                APIS.getMyCardComments(token).enqueue(object : retrofit2.Callback<ResponsegetMyCardComments> {
                    override fun onResponse(call: Call<ResponsegetMyCardComments>, response: Response<ResponsegetMyCardComments>) {
                        if (response.isSuccessful) {


                            Log.d("getComments Response : ", "success")


                            _commentsList.value = response.body()?.commentsList

                            Log.d("commentList : " , response.body()?.commentsList.toString())




                        } else {
                            Log.d("getComments Response : ", "Fail 1")
                        }
                }
                    override fun onFailure(call: Call<ResponsegetMyCardComments>, t: Throwable) {
                        Log.d("getComments Response : ", "Fail 2")
                    }

                })


            } catch (e:Exception) {
                Log.d("getComments response : ", "Fail 3")
            }
        }

    }
}