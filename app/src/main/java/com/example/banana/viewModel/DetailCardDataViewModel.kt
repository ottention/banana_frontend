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
import com.example.banana.data.ResponseNote
import com.example.banana.data.businessCardId
import com.example.banana.data.comment
import com.example.banana.data.getCardResponseModel
import com.example.banana.data.note
import com.example.banana.fragment.CardAPI
import com.example.banana.fragment.HomeFragment
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import org.apache.commons.lang3.ObjectUtils
import retrofit2.Call
import retrofit2.Response

class DetailCardDataViewModel : ViewModel() {
    private lateinit var APIS: API
    private var _cardData = MutableLiveData<getCardResponseModel>()
    var cardData: LiveData<getCardResponseModel> = _cardData

    private var _memoData = MutableLiveData<ArrayList<ResponseNote>>()
    var MemoData: LiveData<ArrayList<ResponseNote>> = _memoData

    private var _noteId = MutableLiveData<Long>()
    var noteId: LiveData<Long> = _noteId

    @SuppressLint("SuspiciousIndentation")
    fun getCardData(cardId: Long) {

        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = authApplication.prefs.getString("accessToken", "")
        viewModelScope.launch {
            try {
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
            } catch (e: Exception) {
                Log.d("cardID response : ", "Fail 3")
            }
        }

    }

    @SuppressLint("SuspiciousIndentation")
    fun getNote(cardId: Long) {
        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = authApplication.prefs.getString("accessToken", "")
        viewModelScope.launch {
            try {
                APIS.getNote(
                    token,
                    cardId
                ).enqueue(object : retrofit2.Callback<ArrayList<ResponseNote>> {
                    override fun onFailure(call: Call<ArrayList<ResponseNote>>, t: Throwable) {
                        Log.e("TAG", "sendOnFailure: ${t.fillInStackTrace()}")
                    }

                    override fun onResponse(
                        call: Call<ArrayList<ResponseNote>>,
                        response: Response<ArrayList<ResponseNote>>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("TAG - isSuccessful", response.body().toString())
                            _memoData.value = response.body()!!

                        } else {
                            Log.d("TAG - failed", response.code().toString())

                        }
                    }

                })
            } catch (e: Exception) {
                Log.d("notes response : ", "Fail 3")
            }
        }

    }

    @SuppressLint("SuspiciousIndentation")
    fun addNote(cardId: Long, note: note) {
        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = authApplication.prefs.getString("accessToken", "")
        viewModelScope.launch {
            try {
                APIS.addNote(
                    token,
                    cardId, note
                ).enqueue(object : retrofit2.Callback<Long> {
                    override fun onFailure(call: Call<Long>, t: Throwable) {
                        Log.e("TAG", "sendOnFailure: ${t.fillInStackTrace()}")
                    }

                    override fun onResponse(
                        call: Call<Long>,
                        response: Response<Long>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("TAG - isSuccessful : add note", response.body().toString())
                            _noteId.value = response.body()!!

                        } else {
                            Log.d("TAG - failed", response.code().toString())

                        }
                    }

                })
            } catch (e: Exception) {
                Log.d("add notes response : ", "Fail 3")
            }
        }

    }

    @SuppressLint("SuspiciousIndentation")
    fun updateNote(cardId: Long, noteId: Long, note: note) {
        APIS = RetrofitInstance.retrofitInstance().create(API::class.java)
        val token = authApplication.prefs.getString("accessToken", "")
        viewModelScope.launch {
            try {
                APIS.updateNote(
                    token,
                    cardId,
                    noteId, note
                ).enqueue(object : retrofit2.Callback<ObjectUtils.Null> {
                    override fun onFailure(call: Call<ObjectUtils.Null>, t: Throwable) {
                        Log.e("TAG", "sendOnFailure: ${t.fillInStackTrace()}")
                    }

                    override fun onResponse(
                        call: Call<ObjectUtils.Null>,
                        response: Response<ObjectUtils.Null>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("TAG - isSuccessful : update Note", response.body().toString())

                        } else {
                            Log.d("TAG - failed", response.code().toString())

                        }
                    }

                })
            } catch (e: Exception) {
                Log.d("update note response : ", "Fail 3")
            }
        }

    }
}