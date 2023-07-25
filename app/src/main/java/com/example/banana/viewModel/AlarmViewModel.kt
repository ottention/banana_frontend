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

        showAlarm()
    }


fun showAlarm(){
    _alarmList.value?.add(AlarmData("23.03.03","000님이 방명록에 좋아요를 눌렀습니다."))
    _alarmList.value?.add(AlarmData("23.03.03","000님이 방명록에 좋아요를 눌렀습니다."))
    _alarmList.value?.add(AlarmData("23.03.03","000님이 방명록에 좋아요를 눌렀습니다."))
    _alarmList.value?.add(AlarmData("23.03.03","000님이 방명록에 좋아요를 눌렀습니다."))
    _alarmList.value?.add(AlarmData("23.03.03","000님이 방명록에 좋아요를 눌렀습니다."))

}

}