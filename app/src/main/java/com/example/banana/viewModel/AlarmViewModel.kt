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

//    fun showAlarm() {
//        _alarmList.value!!.add(AlarmData("23.03.03","000님이 방명록에 좋아요를 눌렀습니다."))
//        _alarmList.value!!.add(AlarmData("23.03.02","000님이 방명록에 좋아요를 눌렀습니다."))
//        _alarmList.value!!.add(AlarmData("23.03.01","000님이 방명록에 좋아요를 눌렀습니다."))
//        _alarmList.value!!.add(AlarmData("23.02.03","000님이 방명록에 좋아요를 눌렀습니다."))
//        _alarmList.value!!.add(AlarmData("23.01.03","000님이 방명록에 좋아요를 눌렀습니다."))
//        _alarmList.value!!.add(AlarmData("23.01.08","000님이 방명록에 좋아요를 눌렀습니다."))
//        _alarmList.value!!.add(AlarmData("23.01.07","000님이 방명록에 좋아요를 눌렀습니다."))
//        _alarmList.value!!.add(AlarmData("23.01.06","000님이 방명록에 좋아요를 눌렀습니다."))
//        _alarmList.value!!.add(AlarmData("23.01.04","000님이 방명록에 좋아요를 눌렀습니다."))
//        _alarmList.value!!.add(AlarmData("23.01.01","000님이 방명록에 좋아요를 눌렀습니다."))
//    }

//    fun deleteAll() {
//        _alarmList.value!!.clear()
//    }

}