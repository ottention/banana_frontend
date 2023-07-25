package com.example.banana.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.banana.data.AlarmData
import com.example.banana.data.TopTenTags

class AlarmViewModel: ViewModel() {

    private var _alarmList = MutableLiveData<ArrayList<AlarmData>>()
    var alarmList : LiveData<ArrayList<AlarmData>> = _alarmList

    init {

//        showAlarm()
    }




}