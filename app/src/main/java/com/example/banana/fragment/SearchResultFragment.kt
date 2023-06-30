package com.example.banana.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.banana.ChartAdapter
import com.example.banana.R
import com.example.banana.data.ChartData
import com.example.banana.databinding.FragmentSearchBinding
import com.example.banana.databinding.FragmentSearchResultBinding


class SearchResultFragment : Fragment() {

    private lateinit var resultAdapter: ChartAdapter
    private var _binding : FragmentSearchResultBinding?= null
    private val binding get() = _binding!!

    private var searchResultData = ArrayList<ChartData>()

    var searchResult : String = ""
    fun newInstance() : SearchResultFragment {
        return SearchResultFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchResultBinding.inflate(inflater,container,false)
        val view = binding.root


        //검색 결과 텍스트

       setFragmentResultListener("searchResult") { requestKey, bundle ->
           val result = bundle.getString("bundleKey")
           binding.textSearchResult.setText(result)
       }




        //검색 결과 리스트
        searchresult()

        //검색 버튼
        binding.btnSearch.setOnClickListener {
            val search = SearchFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameArea,search)
                addToBackStack(null)
                commit()
            }
        }


        //알림 버튼
        binding.btnAlarm.setOnClickListener {

        }






        return view
    }



    fun searchresult() {

        searchResultData.clear()

        searchResultData.add(ChartData(R.drawable.default_card,"Top1","24"))
        searchResultData.add(ChartData(R.drawable.default_card,"Top2","23"))
        searchResultData.add(ChartData(R.drawable.default_card,"Top3","22"))
        searchResultData.add(ChartData(R.drawable.default_card,"Top4","21"))
        searchResultData.add(ChartData(R.drawable.default_card,"Top5","20"))
        searchResultData.add(ChartData(R.drawable.default_card,"Top6","19"))
        searchResultData.add(ChartData(R.drawable.default_card,"Top7","18"))
        searchResultData.add(ChartData(R.drawable.default_card,"Top8","17"))
        searchResultData.add(ChartData(R.drawable.default_card,"Top9","16"))
        searchResultData.add(ChartData(R.drawable.default_card,"Top10","15"))

        resultAdapter = ChartAdapter(searchResultData,::onCardClick,::onLikeClick)
        binding.rvSearchResult.adapter = resultAdapter
        binding.rvSearchResult.layoutManager = GridLayoutManager(context,1)
    }

    //하트 클릭
    fun onLikeClick(){

    }

    //카드 클릭
    fun onCardClick(){


    }

}