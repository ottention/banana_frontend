package com.example.banana.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banana.ChartAdapter
import com.example.banana.R
import com.example.banana.WalletAdapter
import com.example.banana.data.ChartData
import com.example.banana.data.WalletData
import com.example.banana.databinding.FragmentChartBinding
import com.example.banana.databinding.FragmentWalletBinding

class ChartFragment : Fragment() {

    private lateinit var chartAdapter: ChartAdapter
    private var _binding : FragmentChartBinding? = null
    private val binding get() = _binding!!
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
        _binding = FragmentChartBinding.inflate(inflater, container, false)
        val view = binding.root

        //차트 초기 설정
        initChart()


        //태그 1
        binding.btnTag1.setOnClickListener {
            tag1()
        }

        //태그 2
        binding.btnTag2.setOnClickListener {
            tag2()
        }

        //태그 3
        binding.btnTag3.setOnClickListener {
            tag3()
        }

        //태그 4
        binding.btnTag4.setOnClickListener {
            tag4()
        }

        //태그 5
        binding.btnTag5.setOnClickListener {
            tag5()
        }

        //태그 6
        binding.btnTag6.setOnClickListener {
            tag6()
        }

        //태그 7
        binding.btnTag7.setOnClickListener {
            tag7()
        }

        //태그 8
        binding.btnTag8.setOnClickListener {
            tag8()
        }

        //태그 9
        binding.btnTag9.setOnClickListener {
            tag9()
        }

        //태그 10
        binding.btnTag10.setOnClickListener {
            tag10()
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

    //--------------------------------------함수-------------------------------------------
    //차트 초기 설정
    fun initChart() {

        binding.btnTag1.setBackgroundColor(Color.parseColor("#000000"))
        chartData.clear()

        chartData.add(ChartData(R.drawable.default_card,"Top1","24"))
        chartData.add(ChartData(R.drawable.default_card,"Top2","23"))
        chartData.add(ChartData(R.drawable.default_card,"Top3","22"))
        chartData.add(ChartData(R.drawable.default_card,"Top4","21"))
        chartData.add(ChartData(R.drawable.default_card,"Top5","20"))
        chartData.add(ChartData(R.drawable.default_card,"Top6","19"))
        chartData.add(ChartData(R.drawable.default_card,"Top7","18"))
        chartData.add(ChartData(R.drawable.default_card,"Top8","17"))
        chartData.add(ChartData(R.drawable.default_card,"Top9","16"))
        chartData.add(ChartData(R.drawable.default_card,"Top10","15"))

        chartAdapter = ChartAdapter(chartData)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = GridLayoutManager(context,1)

        chartAdapter.itemClick = object : ChartAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                Toast.makeText(context, "명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //tag 1
    fun tag1() {
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

        chartData.clear()

        chartData.add(ChartData(R.drawable.card011,"Top1","24"))
        chartData.add(ChartData(R.drawable.card011,"Top2","23"))
        chartData.add(ChartData(R.drawable.card011,"Top3","22"))
        chartData.add(ChartData(R.drawable.card011,"Top4","21"))
        chartData.add(ChartData(R.drawable.card011,"Top5","20"))
        chartData.add(ChartData(R.drawable.card011,"Top6","19"))
        chartData.add(ChartData(R.drawable.card011,"Top7","18"))
        chartData.add(ChartData(R.drawable.card011,"Top8","17"))
        chartData.add(ChartData(R.drawable.card011,"Top9","16"))
        chartData.add(ChartData(R.drawable.card011,"Top10","15"))

        chartAdapter = ChartAdapter(chartData)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = GridLayoutManager(context,1)

        chartAdapter.itemClick = object : ChartAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                Toast.makeText(context, "명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //태그 2
    fun tag2() {
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

        chartData.clear()

        chartData.add(ChartData(R.drawable.card021,"Top1","24"))
        chartData.add(ChartData(R.drawable.card021,"Top2","23"))
        chartData.add(ChartData(R.drawable.card021,"Top3","22"))
        chartData.add(ChartData(R.drawable.card021,"Top4","21"))
        chartData.add(ChartData(R.drawable.card021,"Top5","20"))
        chartData.add(ChartData(R.drawable.card021,"Top6","19"))
        chartData.add(ChartData(R.drawable.card021,"Top7","18"))
        chartData.add(ChartData(R.drawable.card021,"Top8","17"))
        chartData.add(ChartData(R.drawable.card021,"Top9","16"))
        chartData.add(ChartData(R.drawable.card021,"Top10","15"))



        chartAdapter = ChartAdapter(chartData)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = GridLayoutManager(context,1)

        chartAdapter.itemClick = object : ChartAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                Toast.makeText(context, "명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //태그 3
    fun tag3() {

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

        chartData.clear()

        chartData.add(ChartData(R.drawable.card031,"Top1","24"))
        chartData.add(ChartData(R.drawable.card031,"Top2","23"))
        chartData.add(ChartData(R.drawable.card031,"Top3","22"))
        chartData.add(ChartData(R.drawable.card031,"Top4","21"))
        chartData.add(ChartData(R.drawable.card031,"Top5","20"))
        chartData.add(ChartData(R.drawable.card031,"Top6","19"))
        chartData.add(ChartData(R.drawable.card031,"Top7","18"))
        chartData.add(ChartData(R.drawable.card031,"Top8","17"))
        chartData.add(ChartData(R.drawable.card031,"Top9","16"))
        chartData.add(ChartData(R.drawable.card031,"Top10","15"))

        chartAdapter = ChartAdapter(chartData)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = GridLayoutManager(context,1)

        chartAdapter.itemClick = object : ChartAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                Toast.makeText(context, "명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //태그 4
    fun tag4() {
        binding.btnTag4.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))

        chartData.clear()

        chartData.add(ChartData(R.drawable.card011,"Top1","24"))
        chartData.add(ChartData(R.drawable.card011,"Top2","23"))
        chartData.add(ChartData(R.drawable.card011,"Top3","22"))
        chartData.add(ChartData(R.drawable.card011,"Top4","21"))
        chartData.add(ChartData(R.drawable.card011,"Top5","20"))
        chartData.add(ChartData(R.drawable.card011,"Top6","19"))
        chartData.add(ChartData(R.drawable.card011,"Top7","18"))
        chartData.add(ChartData(R.drawable.card011,"Top8","17"))
        chartData.add(ChartData(R.drawable.card011,"Top9","16"))
        chartData.add(ChartData(R.drawable.card011,"Top10","15"))

        chartAdapter = ChartAdapter(chartData)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = GridLayoutManager(context,1)

        chartAdapter.itemClick = object : ChartAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                Toast.makeText(context, "명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //태그 5
    fun tag5() {

        binding.btnTag5.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))

        chartData.clear()

        chartData.add(ChartData(R.drawable.card021,"Top1","24"))
        chartData.add(ChartData(R.drawable.card021,"Top2","23"))
        chartData.add(ChartData(R.drawable.card021,"Top3","22"))
        chartData.add(ChartData(R.drawable.card021,"Top4","21"))
        chartData.add(ChartData(R.drawable.card021,"Top5","20"))
        chartData.add(ChartData(R.drawable.card021,"Top6","19"))
        chartData.add(ChartData(R.drawable.card021,"Top7","18"))
        chartData.add(ChartData(R.drawable.card021,"Top8","17"))
        chartData.add(ChartData(R.drawable.card021,"Top9","16"))
        chartData.add(ChartData(R.drawable.card021,"Top10","15"))

        chartAdapter = ChartAdapter(chartData)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = GridLayoutManager(context,1)

        chartAdapter.itemClick = object : ChartAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                Toast.makeText(context, "명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //태그 6
    fun tag6() {
        binding.btnTag6.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))

        chartData.clear()

        chartData.add(ChartData(R.drawable.card031,"Top1","24"))
        chartData.add(ChartData(R.drawable.card031,"Top2","23"))
        chartData.add(ChartData(R.drawable.card031,"Top3","22"))
        chartData.add(ChartData(R.drawable.card031,"Top4","21"))
        chartData.add(ChartData(R.drawable.card031,"Top5","20"))
        chartData.add(ChartData(R.drawable.card031,"Top6","19"))
        chartData.add(ChartData(R.drawable.card031,"Top7","18"))
        chartData.add(ChartData(R.drawable.card031,"Top8","17"))
        chartData.add(ChartData(R.drawable.card031,"Top9","16"))
        chartData.add(ChartData(R.drawable.card031,"Top10","15"))

        chartAdapter = ChartAdapter(chartData)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = GridLayoutManager(context,1)

        chartAdapter.itemClick = object : ChartAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                Toast.makeText(context, "명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //태그 7
    fun tag7() {

        binding.btnTag7.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))

        chartData.clear()

        chartData.add(ChartData(R.drawable.card011,"Top1","24"))
        chartData.add(ChartData(R.drawable.card011,"Top2","23"))
        chartData.add(ChartData(R.drawable.card011,"Top3","22"))
        chartData.add(ChartData(R.drawable.card011,"Top4","21"))
        chartData.add(ChartData(R.drawable.card011,"Top5","20"))
        chartData.add(ChartData(R.drawable.card011,"Top6","19"))
        chartData.add(ChartData(R.drawable.card011,"Top7","18"))
        chartData.add(ChartData(R.drawable.card011,"Top8","17"))
        chartData.add(ChartData(R.drawable.card011,"Top9","16"))
        chartData.add(ChartData(R.drawable.card011,"Top10","15"))

        chartAdapter = ChartAdapter(chartData)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = GridLayoutManager(context,1)

        chartAdapter.itemClick = object : ChartAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                Toast.makeText(context, "명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //태그 8
    fun tag8() {

        binding.btnTag8.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))

        chartData.clear()

        chartData.add(ChartData(R.drawable.card021,"Top1","24"))
        chartData.add(ChartData(R.drawable.card021,"Top2","23"))
        chartData.add(ChartData(R.drawable.card021,"Top3","22"))
        chartData.add(ChartData(R.drawable.card021,"Top4","21"))
        chartData.add(ChartData(R.drawable.card021,"Top5","20"))
        chartData.add(ChartData(R.drawable.card021,"Top6","19"))
        chartData.add(ChartData(R.drawable.card021,"Top7","18"))
        chartData.add(ChartData(R.drawable.card021,"Top8","17"))
        chartData.add(ChartData(R.drawable.card021,"Top9","16"))
        chartData.add(ChartData(R.drawable.card021,"Top10","15"))

        chartAdapter = ChartAdapter(chartData)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = GridLayoutManager(context,1)

        chartAdapter.itemClick = object : ChartAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                Toast.makeText(context, "명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //태그 9
    fun tag9() {

        binding.btnTag9.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag10.setBackgroundColor(Color.parseColor("#E6E6E6"))

        chartData.clear()

        chartData.add(ChartData(R.drawable.card031,"Top1","24"))
        chartData.add(ChartData(R.drawable.card031,"Top2","23"))
        chartData.add(ChartData(R.drawable.card031,"Top3","22"))
        chartData.add(ChartData(R.drawable.card031,"Top4","21"))
        chartData.add(ChartData(R.drawable.card031,"Top5","20"))
        chartData.add(ChartData(R.drawable.card031,"Top6","19"))
        chartData.add(ChartData(R.drawable.card031,"Top7","18"))
        chartData.add(ChartData(R.drawable.card031,"Top8","17"))
        chartData.add(ChartData(R.drawable.card031,"Top9","16"))
        chartData.add(ChartData(R.drawable.card031,"Top10","15"))

        chartAdapter = ChartAdapter(chartData)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = GridLayoutManager(context,1)

        chartAdapter.itemClick = object : ChartAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                Toast.makeText(context, "명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //태그 10
    fun tag10() {
        binding.btnTag10.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnTag1.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag2.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag3.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag4.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag5.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag6.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag7.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag8.setBackgroundColor(Color.parseColor("#E6E6E6"))
        binding.btnTag9.setBackgroundColor(Color.parseColor("#E6E6E6"))

        chartData.clear()

        chartData.add(ChartData(R.drawable.default_card,"Top1","24"))
        chartData.add(ChartData(R.drawable.default_card,"Top2","23"))
        chartData.add(ChartData(R.drawable.default_card,"Top3","22"))
        chartData.add(ChartData(R.drawable.default_card,"Top4","21"))
        chartData.add(ChartData(R.drawable.default_card,"Top5","20"))
        chartData.add(ChartData(R.drawable.default_card,"Top6","19"))
        chartData.add(ChartData(R.drawable.default_card,"Top7","18"))
        chartData.add(ChartData(R.drawable.default_card,"Top8","17"))
        chartData.add(ChartData(R.drawable.default_card,"Top9","16"))
        chartData.add(ChartData(R.drawable.default_card,"Top10","15"))

        chartAdapter = ChartAdapter(chartData)
        binding.rvChart.adapter = chartAdapter
        binding.rvChart.layoutManager = GridLayoutManager(context,1)

        chartAdapter.itemClick = object : ChartAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                Toast.makeText(context, "명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun alarm() {
        Toast.makeText(context, "alarm", Toast.LENGTH_SHORT).show()
    }

    fun search() {
        Toast.makeText(context, "search", Toast.LENGTH_SHORT).show()
    }

    fun like() {

    }
}