package com.example.banana.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banana.adapter.ChartAdapter
import com.example.banana.R
import com.example.banana.data.ChartData
import com.example.banana.databinding.FragmentChartBinding
import com.example.banana.viewModel.ChartViewModel

class ChartFragment : Fragment() {

    private lateinit var chartAdapter: ChartAdapter
    private lateinit var binding : FragmentChartBinding

    private lateinit var viewModel: ChartViewModel
    private var chartData = ArrayList<ChartData>()

    fun newInstance() : ChartFragment{
        return ChartFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_chart,container,false)
        val view = binding.root


        binding.btnTag1.setBackgroundColor(Color.parseColor("#000000"))


//        viewModel.topTenTagsList.observe(viewLifecycleOwner) {
//        binding.btnTag1.text = viewModel.topTenTagsList.value!!.get(0).tag
//        binding.btnTag2.text = viewModel.topTenTagsList.value!!.get(1).tag
//        binding.btnTag3.text = viewModel.topTenTagsList.value!!.get(2).tag
//        binding.btnTag4.text = viewModel.topTenTagsList.value!!.get(3).tag
//        binding.btnTag5.text = viewModel.topTenTagsList.value!!.get(4).tag
//        binding.btnTag6.text = viewModel.topTenTagsList.value!!.get(5).tag
//        binding.btnTag7.text = viewModel.topTenTagsList.value!!.get(6).tag
//        binding.btnTag8.text = viewModel.topTenTagsList.value!!.get(7).tag
//        binding.btnTag9.text = viewModel.topTenTagsList.value!!.get(8).tag
//        binding.btnTag10.text = viewModel.topTenTagsList.value!!.get(9).tag
//        }
//        binding.btnTag1.text = viewModel.topTenTags.value!!.get(0).tag
//        binding.btnTag2.text = viewModel.topTenTags.value!!.get(1).tag
//        binding.btnTag3.text = viewModel.topTenTags.value!!.get(2).tag
//        binding.btnTag4.text = viewModel.topTenTags.value!!.get(3).tag
//        binding.btnTag5.text = viewModel.topTenTags.value!!.get(4).tag
//        binding.btnTag6.text = viewModel.topTenTags.value!!.get(5).tag
//        binding.btnTag7.text = viewModel.topTenTags.value!!.get(6).tag
//        binding.btnTag8.text = viewModel.topTenTags.value!!.get(7).tag
//        binding.btnTag9.text = viewModel.topTenTags.value!!.get(8).tag
//        binding.btnTag10.text = viewModel.topTenTags.value!!.get(9).tag

        viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)

        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = LinearLayoutManager(context)

        viewModel.chartList.observe(viewLifecycleOwner) {
            chartAdapter.updateList(it)
        }


        //태그 1
        binding.btnTag1.setOnClickListener {
            binding.btnTag1.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))

//        viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)
//
//        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
//        binding.rvChart.adapter = chartAdapter
//        binding.rvChart.layoutManager = LinearLayoutManager(context)

        viewModel.chartList.observe(viewLifecycleOwner) {
            chartAdapter.updateList(it)
        }
        }

        //태그 2
        binding.btnTag2.setOnClickListener {

            binding.btnTag2.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))

        viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)

        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = LinearLayoutManager(context)

        viewModel.chartList.observe(viewLifecycleOwner) {
            chartAdapter.updateList(it)
        }
        }

        //태그 3
        binding.btnTag3.setOnClickListener {
            binding.btnTag3.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))


            viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)

        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = LinearLayoutManager(context)

        viewModel.chartList.observe(viewLifecycleOwner) {
            chartAdapter.updateList(it)
        }
        }

        //태그 4
        binding.btnTag4.setOnClickListener {
            binding.btnTag4.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))


            viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)

        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = LinearLayoutManager(context)

        viewModel.chartList.observe(viewLifecycleOwner) {
            chartAdapter.updateList(it)
        }
        }

        //태그 5
        binding.btnTag5.setOnClickListener {
            binding.btnTag5.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))


            viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)

        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = LinearLayoutManager(context)

        viewModel.chartList.observe(viewLifecycleOwner) {
            chartAdapter.updateList(it)
        }
        }

        //태그 6
        binding.btnTag6.setOnClickListener {
            binding.btnTag6.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))


            viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)

        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = LinearLayoutManager(context)

        viewModel.chartList.observe(viewLifecycleOwner) {
            chartAdapter.updateList(it)
        }
        }

        //태그 7
        binding.btnTag7.setOnClickListener {
            binding.btnTag7.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))


            viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)

        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = LinearLayoutManager(context)

        viewModel.chartList.observe(viewLifecycleOwner) {
            chartAdapter.updateList(it)
        }
        }

        //태그 8
        binding.btnTag8.setOnClickListener {
            binding.btnTag8.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))


            viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)

        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = LinearLayoutManager(context)

        viewModel.chartList.observe(viewLifecycleOwner) {
            chartAdapter.updateList(it)
        }
        }

        //태그 9
        binding.btnTag9.setOnClickListener {
            binding.btnTag9.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))


            viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)

        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = LinearLayoutManager(context)

        viewModel.chartList.observe(viewLifecycleOwner) {
            chartAdapter.updateList(it)
        }
        }

        //태그 10
        binding.btnTag10.setOnClickListener {
            binding.btnTag10.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
            binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))


            viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)

        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = LinearLayoutManager(context)

        viewModel.chartList.observe(viewLifecycleOwner) {
            chartAdapter.updateList(it)
        }
        }

        //알람 버튼 클릭
        binding.btnAlarm.setOnClickListener {
            alarm()
        }

        //검색 버튼 클릭
        binding.btnSearch.setOnClickListener {
            search()
        }


        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.topTenTagsList.observe(viewLifecycleOwner) {
            binding.btnTag1.text = viewModel.topTenTagsList.value!!.get(0).tag
            binding.btnTag2.text = viewModel.topTenTagsList.value!!.get(1).tag
            binding.btnTag3.text = viewModel.topTenTagsList.value!!.get(2).tag
            binding.btnTag4.text = viewModel.topTenTagsList.value!!.get(3).tag
            binding.btnTag5.text = viewModel.topTenTagsList.value!!.get(4).tag
            binding.btnTag6.text = viewModel.topTenTagsList.value!!.get(5).tag
            binding.btnTag7.text = viewModel.topTenTagsList.value!!.get(6).tag
            binding.btnTag8.text = viewModel.topTenTagsList.value!!.get(7).tag
            binding.btnTag9.text = viewModel.topTenTagsList.value!!.get(8).tag
            binding.btnTag10.text = viewModel.topTenTagsList.value!!.get(9).tag
        }
    }

    //--------------------------------------함수-------------------------------------------
    //차트 초기 설정
//    fun initChart() {
//
//        binding.btnTag1.setBackgroundColor(Color.parseColor("#000000"))
//        chartData.clear()
//
//        chartData.add(ChartData(R.drawable.default_card,"Top1","24"))
//        chartData.add(ChartData(R.drawable.default_card,"Top2","23"))
//        chartData.add(ChartData(R.drawable.default_card,"Top3","22"))
//        chartData.add(ChartData(R.drawable.default_card,"Top4","21"))
//        chartData.add(ChartData(R.drawable.default_card,"Top5","20"))
//        chartData.add(ChartData(R.drawable.default_card,"Top6","19"))
//        chartData.add(ChartData(R.drawable.default_card,"Top7","18"))
//        chartData.add(ChartData(R.drawable.default_card,"Top8","17"))
//        chartData.add(ChartData(R.drawable.default_card,"Top9","16"))
//        chartData.add(ChartData(R.drawable.default_card,"Top10","15"))
//
//        Log.d(javaClass.name,"어댑터 생성")
//        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
//        binding.rvChart.adapter = chartAdapter
//        binding.rvChart.layoutManager = GridLayoutManager(context,1)
//
//    }

    //하트 버튼 클릭
    fun onLikeClick(){

    }

    //카드 클릭
    fun onCardClick(){


    }

    //tag 1
//    fun tag1() {
//        binding.btnTag1.setBackgroundColor(Color.parseColor("#000000"))
//        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))
//
//        chartData.clear()
//
//        chartData.add(ChartData(R.drawable.default_card,"Top1","24"))
//        chartData.add(ChartData(R.drawable.default_card,"Top2","23"))
//        chartData.add(ChartData(R.drawable.default_card,"Top3","22"))
//        chartData.add(ChartData(R.drawable.default_card,"Top4","21"))
//        chartData.add(ChartData(R.drawable.default_card,"Top5","20"))
//        chartData.add(ChartData(R.drawable.default_card,"Top6","19"))
//        chartData.add(ChartData(R.drawable.default_card,"Top7","18"))
//        chartData.add(ChartData(R.drawable.default_card,"Top8","17"))
//        chartData.add(ChartData(R.drawable.default_card,"Top9","16"))
//        chartData.add(ChartData(R.drawable.default_card,"Top10","15"))
//
//        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
//        binding.rvChart.adapter = chartAdapter
//        binding.rvChart.layoutManager = GridLayoutManager(context,1)
//
//    }
//
//    //태그 2
//    fun tag2() {
//        binding.btnTag2.setBackgroundColor(Color.parseColor("#000000"))
//        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))
//
//        chartData.clear()
//
//        chartData.add(ChartData(R.drawable.card021,"Top1","24"))
//        chartData.add(ChartData(R.drawable.card021,"Top2","23"))
//        chartData.add(ChartData(R.drawable.card021,"Top3","22"))
//        chartData.add(ChartData(R.drawable.card021,"Top4","21"))
//        chartData.add(ChartData(R.drawable.card021,"Top5","20"))
//        chartData.add(ChartData(R.drawable.card021,"Top6","19"))
//        chartData.add(ChartData(R.drawable.card021,"Top7","18"))
//        chartData.add(ChartData(R.drawable.card021,"Top8","17"))
//        chartData.add(ChartData(R.drawable.card021,"Top9","16"))
//        chartData.add(ChartData(R.drawable.card021,"Top10","15"))
//
//
//
//        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
//        binding.rvChart.adapter = chartAdapter
//        binding.rvChart.layoutManager = GridLayoutManager(context,1)
//
//
//    }
//
//    //태그 3
//    fun tag3() {
//
//        binding.btnTag3.setBackgroundColor(Color.parseColor("#000000"))
//        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))
//
//        chartData.clear()
//
//        chartData.add(ChartData(R.drawable.card031,"Top1","24"))
//        chartData.add(ChartData(R.drawable.card031,"Top2","23"))
//        chartData.add(ChartData(R.drawable.card031,"Top3","22"))
//        chartData.add(ChartData(R.drawable.card031,"Top4","21"))
//        chartData.add(ChartData(R.drawable.card031,"Top5","20"))
//        chartData.add(ChartData(R.drawable.card031,"Top6","19"))
//        chartData.add(ChartData(R.drawable.card031,"Top7","18"))
//        chartData.add(ChartData(R.drawable.card031,"Top8","17"))
//        chartData.add(ChartData(R.drawable.card031,"Top9","16"))
//        chartData.add(ChartData(R.drawable.card031,"Top10","15"))
//
//        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
//        binding.rvChart.adapter = chartAdapter
//        binding.rvChart.layoutManager = GridLayoutManager(context,1)
//
//
//    }
//
//    //태그 4
//    fun tag4() {
//        binding.btnTag4.setBackgroundColor(Color.parseColor("#000000"))
//        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))
//
//        chartData.clear()
//
//        chartData.add(ChartData(R.drawable.card011,"Top1","24"))
//        chartData.add(ChartData(R.drawable.card011,"Top2","23"))
//        chartData.add(ChartData(R.drawable.card011,"Top3","22"))
//        chartData.add(ChartData(R.drawable.card011,"Top4","21"))
//        chartData.add(ChartData(R.drawable.card011,"Top5","20"))
//        chartData.add(ChartData(R.drawable.card011,"Top6","19"))
//        chartData.add(ChartData(R.drawable.card011,"Top7","18"))
//        chartData.add(ChartData(R.drawable.card011,"Top8","17"))
//        chartData.add(ChartData(R.drawable.card011,"Top9","16"))
//        chartData.add(ChartData(R.drawable.card011,"Top10","15"))
//
//        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
//        binding.rvChart.adapter = chartAdapter
//        binding.rvChart.layoutManager = GridLayoutManager(context,1)
//
//
//    }
//
//    //태그 5
//    fun tag5() {
//
//        binding.btnTag5.setBackgroundColor(Color.parseColor("#000000"))
//        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))
//
//        chartData.clear()
//
//        chartData.add(ChartData(R.drawable.card021,"Top1","24"))
//        chartData.add(ChartData(R.drawable.card021,"Top2","23"))
//        chartData.add(ChartData(R.drawable.card021,"Top3","22"))
//        chartData.add(ChartData(R.drawable.card021,"Top4","21"))
//        chartData.add(ChartData(R.drawable.card021,"Top5","20"))
//        chartData.add(ChartData(R.drawable.card021,"Top6","19"))
//        chartData.add(ChartData(R.drawable.card021,"Top7","18"))
//        chartData.add(ChartData(R.drawable.card021,"Top8","17"))
//        chartData.add(ChartData(R.drawable.card021,"Top9","16"))
//        chartData.add(ChartData(R.drawable.card021,"Top10","15"))
//
//        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
//        binding.rvChart.adapter = chartAdapter
//        binding.rvChart.layoutManager = GridLayoutManager(context,1)
//
//
//    }
//
//    //태그 6
//    fun tag6() {
//        binding.btnTag6.setBackgroundColor(Color.parseColor("#000000"))
//        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))
//
//        chartData.clear()
//
//        chartData.add(ChartData(R.drawable.card031,"Top1","24"))
//        chartData.add(ChartData(R.drawable.card031,"Top2","23"))
//        chartData.add(ChartData(R.drawable.card031,"Top3","22"))
//        chartData.add(ChartData(R.drawable.card031,"Top4","21"))
//        chartData.add(ChartData(R.drawable.card031,"Top5","20"))
//        chartData.add(ChartData(R.drawable.card031,"Top6","19"))
//        chartData.add(ChartData(R.drawable.card031,"Top7","18"))
//        chartData.add(ChartData(R.drawable.card031,"Top8","17"))
//        chartData.add(ChartData(R.drawable.card031,"Top9","16"))
//        chartData.add(ChartData(R.drawable.card031,"Top10","15"))
//
//        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
//        binding.rvChart.adapter = chartAdapter
//        binding.rvChart.layoutManager = GridLayoutManager(context,1)
//
//
//    }
//
//    //태그 7
//    fun tag7() {
//
//        binding.btnTag7.setBackgroundColor(Color.parseColor("#000000"))
//        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))
//
//        chartData.clear()
//
//        chartData.add(ChartData(R.drawable.card011,"Top1","24"))
//        chartData.add(ChartData(R.drawable.card011,"Top2","23"))
//        chartData.add(ChartData(R.drawable.card011,"Top3","22"))
//        chartData.add(ChartData(R.drawable.card011,"Top4","21"))
//        chartData.add(ChartData(R.drawable.card011,"Top5","20"))
//        chartData.add(ChartData(R.drawable.card011,"Top6","19"))
//        chartData.add(ChartData(R.drawable.card011,"Top7","18"))
//        chartData.add(ChartData(R.drawable.card011,"Top8","17"))
//        chartData.add(ChartData(R.drawable.card011,"Top9","16"))
//        chartData.add(ChartData(R.drawable.card011,"Top10","15"))
//
//        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
//        binding.rvChart.adapter = chartAdapter
//        binding.rvChart.layoutManager = GridLayoutManager(context,1)
//
//
//    }
//
//    //태그 8
//    fun tag8() {
//
//        binding.btnTag8.setBackgroundColor(Color.parseColor("#000000"))
//        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))
//
//        chartData.clear()
//
//        chartData.add(ChartData(R.drawable.card021,"Top1","24"))
//        chartData.add(ChartData(R.drawable.card021,"Top2","23"))
//        chartData.add(ChartData(R.drawable.card021,"Top3","22"))
//        chartData.add(ChartData(R.drawable.card021,"Top4","21"))
//        chartData.add(ChartData(R.drawable.card021,"Top5","20"))
//        chartData.add(ChartData(R.drawable.card021,"Top6","19"))
//        chartData.add(ChartData(R.drawable.card021,"Top7","18"))
//        chartData.add(ChartData(R.drawable.card021,"Top8","17"))
//        chartData.add(ChartData(R.drawable.card021,"Top9","16"))
//        chartData.add(ChartData(R.drawable.card021,"Top10","15"))
//
//        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
//        binding.rvChart.adapter = chartAdapter
//        binding.rvChart.layoutManager = GridLayoutManager(context,1)
//
//
//    }
//
//    //태그 9
//    fun tag9() {
//
//        binding.btnTag9.setBackgroundColor(Color.parseColor("#000000"))
//        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))
//
//        chartData.clear()
//
//        chartData.add(ChartData(R.drawable.card031,"Top1","24"))
//        chartData.add(ChartData(R.drawable.card031,"Top2","23"))
//        chartData.add(ChartData(R.drawable.card031,"Top3","22"))
//        chartData.add(ChartData(R.drawable.card031,"Top4","21"))
//        chartData.add(ChartData(R.drawable.card031,"Top5","20"))
//        chartData.add(ChartData(R.drawable.card031,"Top6","19"))
//        chartData.add(ChartData(R.drawable.card031,"Top7","18"))
//        chartData.add(ChartData(R.drawable.card031,"Top8","17"))
//        chartData.add(ChartData(R.drawable.card031,"Top9","16"))
//        chartData.add(ChartData(R.drawable.card031,"Top10","15"))
//
//        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
//        binding.rvChart.adapter = chartAdapter
//        binding.rvChart.layoutManager = GridLayoutManager(context,1)
//
//
//    }
//
//    //태그 10
//    fun tag10() {
//        binding.btnTag10.setBackgroundColor(Color.parseColor("#000000"))
//        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
//        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
//
//        chartData.clear()
//
//        chartData.add(ChartData(R.drawable.default_card,"Top1","24"))
//        chartData.add(ChartData(R.drawable.default_card,"Top2","23"))
//        chartData.add(ChartData(R.drawable.default_card,"Top3","22"))
//        chartData.add(ChartData(R.drawable.default_card,"Top4","21"))
//        chartData.add(ChartData(R.drawable.default_card,"Top5","20"))
//        chartData.add(ChartData(R.drawable.default_card,"Top6","19"))
//        chartData.add(ChartData(R.drawable.default_card,"Top7","18"))
//        chartData.add(ChartData(R.drawable.default_card,"Top8","17"))
//        chartData.add(ChartData(R.drawable.default_card,"Top9","16"))
//        chartData.add(ChartData(R.drawable.default_card,"Top10","15"))
//
//        chartAdapter = ChartAdapter(chartData,::onCardClick,::onLikeClick)
//        binding.rvChart.adapter = chartAdapter
//        binding.rvChart.layoutManager = GridLayoutManager(context,1)
//
//
//    }

    fun alarm() {
        Toast.makeText(context, "alarm", Toast.LENGTH_SHORT).show()
    }

    fun search() {
        Toast.makeText(context, "search", Toast.LENGTH_SHORT).show()
    }

    fun like() {

    }
}