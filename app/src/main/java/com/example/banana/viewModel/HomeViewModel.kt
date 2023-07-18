package com.example.banana.viewModel

import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.banana.R
import com.example.banana.adapter.KeywordViewAdapter
import com.example.banana.data.*
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private lateinit var APIS : API

    private var _businessCardId = MutableLiveData<ArrayList<businessCardIdData>>()
    var businessCardId : LiveData<ArrayList<businessCardIdData>> = _businessCardId

    private var _getCard = MutableLiveData<getCardResponseModel>()
    var getCard : LiveData<getCardResponseModel> = _getCard




    init {
        getBusinessCardId()
    }


    //card01 클릭
    fun showCard1(){

        var card01 = _businessCardId.value?.get(0)!!.businessCardId


        getCard(card01)

        Log.d("showCard1",card01.toString())

    }

    //card02 클릭
    fun showCard2(){

        var card02 = _businessCardId.value?.get(1)!!.businessCardId
        getCard(card02)
        Log.d("showCard2",card02.toString())
    }

    //card03 클릭
    fun showCard3(){

        var card03 = _businessCardId.value?.get(2)!!.businessCardId
        getCard(card03)
        Log.d("showCard3",card03.toString())

    }

    //카드 id 가져오기
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
                            showCard1()


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


    //cardId 조회
    fun getCard(a:Long) {
        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)

        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg5MDUzNTgwLCJleHAiOjE2OTE2NDU1ODB9.I3ART9XCYkp1l7YnC6cGv6uMvCwBqsqcUW2r1GXMKx4"

        viewModelScope.launch {
            try{

                APIS.getCard(token,a).enqueue(object : retrofit2.Callback<getCardResponseModel> {
                    override fun onResponse(call: Call<getCardResponseModel>, response: Response<getCardResponseModel>) {
                        if (response.isSuccessful) {


                            Log.d("getCard Response : ", "success")


                            _getCard.value = response.body()


                            Log.d("getCard : " , response.body().toString())


                        } else {
                            Log.d("getCard : " , response.body().toString())
                            Log.d("getCard : " , response.message())
                            Log.d("getCard Response : ", "Fail 1")
                        }
                    }

                    override fun onFailure(call: Call<getCardResponseModel>, t: Throwable) {
                        Log.d("getCard Response : ", t.message.toString())
                    }
                })
            } catch (e:Exception) {
                Log.d("getCard response : ", "Fail 3")
            }
        }
    }







}