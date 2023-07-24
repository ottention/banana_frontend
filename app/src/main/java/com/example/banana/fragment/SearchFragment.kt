package com.example.banana.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import com.example.banana.R
import com.example.banana.adapter.RecentSearchAdapter
import com.example.banana.data.RecentSearchData
import com.example.banana.data.TopTenTags
import com.example.banana.databinding.FragmentSearchBinding
import com.example.banana.viewModel.SearchViewModel
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent


class SearchFragment : Fragment() {
    private lateinit var recentSearchAdapter : RecentSearchAdapter
    private var _binding : FragmentSearchBinding ?= null
    private val binding get() = _binding!!
    private var recentSearchWord = ArrayList<TopTenTags>()
    private lateinit var viewModel : SearchViewModel

    fun newInstance() : SearchFragment{
        return  SearchFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        val view = binding.root




        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        viewModel.topTenTagsList.observe(viewLifecycleOwner) {

            recentSearchAdapter.updateList(it)

        }



        recentSearchAdapter = RecentSearchAdapter(recentSearchWord)
        binding.rvRecentSearch.adapter = recentSearchAdapter
       // binding.rvRecentSearch.layoutManager = GridLayoutManager(context,3)
        binding.rvRecentSearch.layoutManager = FlexboxLayoutManager(context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
        }

        //뒤로가기 버튼


        //검색 버튼
        binding.btnSearchResult.setOnClickListener {
            val searchResultText = binding.editText.text.toString()

            setFragmentResult("searchResult", bundleOf("bundleKey" to searchResultText))
            val searchResult = SearchResultFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameArea,searchResult)
//                addToBackStack(null)
                commit()
            }



        }

        //뒤로 가기
        binding.btnBack.setOnClickListener {
            val home = HomeFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameArea, home)
//                addToBackStack(null)
                commit()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

//    viewModel.topTenTagsList.observe(viewLifecycleOwner) {
//
//        recentSearchAdapter.updateList(it)
//        recentSearchAdapter = RecentSearchAdapter(recentSearchWord)
//        binding.rvRecentSearch.adapter = recentSearchAdapter
//        // binding.rvRecentSearch.layoutManager = GridLayoutManager(context,3)
//        binding.rvRecentSearch.layoutManager = FlexboxLayoutManager(context).apply {
//            flexWrap = FlexWrap.WRAP
//            flexDirection = FlexDirection.ROW
//            justifyContent = JustifyContent.FLEX_START
//        }
//    }
}