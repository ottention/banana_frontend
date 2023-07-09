package com.example.banana.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.banana.R
import com.example.banana.data.ChartData
import com.example.banana.retrofit.API
import kotlinx.coroutines.launch

class ChartViewModel : ViewModel() {
    private lateinit var APIS : API
    private var _chartList = MutableLiveData<ArrayList<ChartData>>()
    var chartList : LiveData<ArrayList<ChartData>> = _chartList

    init {
        showChartList()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun showChartList() {

        viewModelScope.launch {
        var tempChartList = ArrayList<ChartData>()

            tempChartList.add(ChartData(R.drawable.default_card,"Top1","24"))
            tempChartList.add(ChartData(R.drawable.default_card,"Top2","23"))
            tempChartList.add(ChartData(R.drawable.default_card,"Top3","22"))
            tempChartList.add(ChartData(R.drawable.default_card,"Top4","21"))
            tempChartList.add(ChartData(R.drawable.default_card,"Top5","20"))
            tempChartList.add(ChartData(R.drawable.default_card,"Top6","19"))
            tempChartList.add(ChartData(R.drawable.default_card,"Top7","18"))
            tempChartList.add(ChartData(R.drawable.default_card,"Top8","17"))
            tempChartList.add(ChartData(R.drawable.default_card,"Top9","16"))
            tempChartList.add(ChartData(R.drawable.default_card,"Top10","15"))
            _chartList.value = tempChartList
        }
    }
}