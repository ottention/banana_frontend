package com.example.banana.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banana.data.businessCardIdData
import com.example.banana.data.comment
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response


class MyCardCommentsViewModel : ViewModel() {
    private lateinit var APIS : API


    private var _businessCardId = MutableLiveData<ArrayList<businessCardIdData>>()
    var businessCardId : LiveData<ArrayList<businessCardIdData>> = _businessCardId

    private var _commentsList = MutableLiveData<ArrayList<comment>>()
    var commentsList : LiveData<ArrayList<comment>> = _commentsList



    init {
        getBusinessCardId()
//            showCommentsList1()

    }

    fun getBusinessCardId() {
        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg5MDUzNTgwLCJleHAiOjE2OTE2NDU1ODB9.I3ART9XCYkp1l7YnC6cGv6uMvCwBqsqcUW2r1GXMKx4"
        viewModelScope.launch {
            try{

                APIS.getBusinessCardId(token).enqueue(object : retrofit2.Callback<ArrayList<businessCardIdData>> {
                    override fun onResponse(call: Call<ArrayList<businessCardIdData>>, response: Response<ArrayList<businessCardIdData>>) {
                        if (response.isSuccessful) {


                            Log.d("businessCardIdData Response : ", "success")


                            _businessCardId.value = response.body()
                            showCommentsList1()

                            Log.d("businessCardIdData : " , response.body().toString())


                        } else {
                            Log.d("businessCardIdData : " , response.body().toString())
                            Log.d("businessCardIdData : " , response.message())
                            Log.d("businessCardIdData Response : ", "Fail 1")
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<businessCardIdData>>, t: Throwable) {
                        Log.d("businessCardIdData Response : ", t.message.toString())
                    }
                })
            } catch (e:Exception) {
                Log.d("businessCardIdData response : ", "Fail 3")
            }
        }
    }


     fun  showCommentsList1() {

         var card01 = _businessCardId.value?.get(0)!!.businessCardId

        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg5MDUzNTgwLCJleHAiOjE2OTE2NDU1ODB9.I3ART9XCYkp1l7YnC6cGv6uMvCwBqsqcUW2r1GXMKx4"
        viewModelScope.launch {
            try{

                APIS.getMyCardComments(token,card01,1).enqueue(object : retrofit2.Callback<ArrayList<comment>> {
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

    fun  showCommentsList2() {

        var card02 = _businessCardId.value?.get(1)!!.businessCardId

        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg5MDUzNTgwLCJleHAiOjE2OTE2NDU1ODB9.I3ART9XCYkp1l7YnC6cGv6uMvCwBqsqcUW2r1GXMKx4"
        viewModelScope.launch {
            try{

                APIS.getMyCardComments(token,card02,1).enqueue(object : retrofit2.Callback<ArrayList<comment>> {
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

    fun  showCommentsList3() {

        var card03 = _businessCardId.value?.get(2)!!.businessCardId

        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg5MDUzNTgwLCJleHAiOjE2OTE2NDU1ODB9.I3ART9XCYkp1l7YnC6cGv6uMvCwBqsqcUW2r1GXMKx4"
        viewModelScope.launch {
            try{

                APIS.getMyCardComments(token,card03,1).enqueue(object : retrofit2.Callback<ArrayList<comment>> {
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