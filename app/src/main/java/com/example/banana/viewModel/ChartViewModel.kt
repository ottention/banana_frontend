package com.example.banana.viewModel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banana.R
import com.example.banana.auth.authApplication
import com.example.banana.data.ChartData
import com.example.banana.data.TopTenTags
import com.example.banana.data.businessCardIdData
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class ChartViewModel : ViewModel() {
    private lateinit var APIS : API

    private var _topTenTagsList = MutableLiveData<ArrayList<TopTenTags>>()
    var topTenTagsList : LiveData<ArrayList<TopTenTags>> = _topTenTagsList

    private var _chartList = MutableLiveData<ArrayList<ChartData>>()
    var chartList : LiveData<ArrayList<ChartData>> = _chartList

    init {
//        showChartList()
        showTopTenTags()
    }


//    @SuppressLint("SuspiciousIndentation")
//    private fun showChartList() {
//
//        viewModelScope.launch {
//        var tempChartList = ArrayList<ChartData>()
//
//            tempChartList.add(ChartData(R.drawable.default_card,"Top1","24"))
//            tempChartList.add(ChartData(R.drawable.default_card,"Top2","23"))
//            tempChartList.add(ChartData(R.drawable.default_card,"Top3","22"))
//            tempChartList.add(ChartData(R.drawable.default_card,"Top4","21"))
//            tempChartList.add(ChartData(R.drawable.default_card,"Top5","20"))
//            tempChartList.add(ChartData(R.drawable.default_card,"Top6","19"))
//            tempChartList.add(ChartData(R.drawable.default_card,"Top7","18"))
//            tempChartList.add(ChartData(R.drawable.default_card,"Top8","17"))
//            tempChartList.add(ChartData(R.drawable.default_card,"Top9","16"))
//            tempChartList.add(ChartData(R.drawable.default_card,"Top10","15"))
//            _chartList.value = tempChartList
//        }
//    }

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