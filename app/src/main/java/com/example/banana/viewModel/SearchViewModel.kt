package com.example.banana.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banana.data.TopTenTags
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class SearchViewModel : ViewModel() {
    private lateinit var APIS : API

    private var _topTenTagsList = MutableLiveData<ArrayList<TopTenTags>>()
    var topTenTagsList : LiveData<ArrayList<TopTenTags>> = _topTenTagsList

    init {
//        showChartList()
        showTopTenTags()
    }

    private fun showTopTenTags() {

        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token ="eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg5ODU4ODI2LCJleHAiOjE2OTI0NTA4MjZ9.Sgv-vKEeQ4Zbb5zR6SQuQ7wD2W_FrFuqSyJcPU_6Wos"

        viewModelScope.launch {
            try{
                APIS.getTopTenTags(token).enqueue(object : retrofit2.Callback<ArrayList<TopTenTags>> {
                    override fun onResponse(call: Call<ArrayList<TopTenTags>>, response: Response<ArrayList<TopTenTags>>) {
                        if (response.isSuccessful) {

                            Log.d("TopTenTags Response : ", "success")
                            _topTenTagsList.value = response.body()



                            Log.d("TopTenTags : " , response.body().toString())


                        } else {

                            Log.d("TopTenTags Response : ", "Fail 1")
                            Log.d("TopTenTags Response : ", response.message().toString())
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<TopTenTags>>, t: Throwable) {
                        Log.d("TopTenTags Response : ", t.message.toString())
                    }
                })
            } catch (e:Exception) {
                Log.d("TopTenTags response : ", "Fail 3")
            }
        }
    }
}