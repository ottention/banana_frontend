package com.example.banana.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banana.data.ccomment
import com.example.banana.data.comment
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import org.apache.commons.lang3.ObjectUtils
import retrofit2.Call
import retrofit2.Response

class CommentDetailViewModel : ViewModel() {
    private lateinit var APIS : API
    private var _commentId = MutableLiveData<Long>()
    var commentId : LiveData<Long> = _commentId

    private var _commentsList = MutableLiveData<ArrayList<comment>>()
    var commentsList : LiveData<ArrayList<comment>> = _commentsList
    init {

        getCommentToSpCard(1L)

    }
    @SuppressLint("SuspiciousIndentation")
    public fun getCommentToSpCard(guestBookId : Long) {
        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNjg5OTM4MjAwLCJleHAiOjE2OTI1MzAyMDB9.USu2Sw-wSWXlLnoA-VQhTAfrMcVuk8twk2s2RO57wJQ"
        viewModelScope.launch {
            try{
                APIS.getComment(token,guestBookId).enqueue(object : retrofit2.Callback<ArrayList<comment>> {
                    override fun onResponse(call: Call<ArrayList<comment>>, response: Response<ArrayList<comment>>) {
                        if (response.isSuccessful) {
                            Log.d("commentData - success: " , response.body().toString())
                            _commentsList.value = response.body()
                        } else {
                            Log.d("commentData: " , response.body().toString())

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
    @SuppressLint("SuspiciousIndentation")
    public fun addComment(comment: ccomment) {

        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNjg5OTM4MjAwLCJleHAiOjE2OTI1MzAyMDB9.USu2Sw-wSWXlLnoA-VQhTAfrMcVuk8twk2s2RO57wJQ"
        viewModelScope.launch {
            try{
                APIS.addComment(token,1, comment).enqueue(object : retrofit2.Callback<Long> {
                    override fun onResponse(call: Call<Long>, response: Response<Long>) {
                        if (response.isSuccessful) {
                                _commentId.value = response.body()
                            Log.d("commentId - addCommentSuccess: " , response.body().toString())

                        } else {
                            Log.d("addComment - fail: " , response.code().toString())

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

    @SuppressLint("SuspiciousIndentation")
    public fun removeComment(commentId: Long) {

        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNjg5OTM4MjAwLCJleHAiOjE2OTI1MzAyMDB9.USu2Sw-wSWXlLnoA-VQhTAfrMcVuk8twk2s2RO57wJQ"
        viewModelScope.launch {
            try{
                APIS.removeComment(token,commentId).enqueue(object : retrofit2.Callback<ObjectUtils.Null> {
                    override fun onResponse(call: Call<ObjectUtils.Null>, response: Response<ObjectUtils.Null>) {
                        if (response.isSuccessful) {
                            Log.d("comment remove : ", "success")
                        } else {
                            Log.d("comment remove - fail: ", response.code().toString())
                        }
                    }
                    override fun onFailure(call: Call<ObjectUtils.Null>, t: Throwable) {
                        Log.d("comment remove : error Response : ", t.message.toString())
                    }

                })


            } catch (e:Exception) {
                Log.d("getComments response : ", "Fail 3")
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    public fun updateComment(commentId: Long) {

        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwiaWF0IjoxNjg5ODU4ODI2LCJleHAiOjE2ODk4ODA0MjZ9.CJUrfSPV7cdEzT_5Ffp8zRc0gzMFKqRGf7Fg581C3hg"
        viewModelScope.launch {
            try{
                APIS.removeComment(token,1).enqueue(object : retrofit2.Callback<ObjectUtils.Null> {
                    override fun onResponse(call: Call<ObjectUtils.Null>, response: Response<ObjectUtils.Null>) {
                        if (response.isSuccessful) {
                            Log.d("comment update : ", "success")
                        } else {
                            Log.d("comment update : ", "fail")
                        }
                    }
                    override fun onFailure(call: Call<ObjectUtils.Null>, t: Throwable) {
                        Log.d("comment update : error Response : ", t.message.toString())
                    }

                })


            } catch (e:Exception) {
                Log.d("getComments response : ", "Fail 3")
            }
        }
    }
}