package com.example.banana.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import com.example.banana.R
import com.example.banana.RecentSearchAdapter
import com.example.banana.data.RecentSearchData
import com.example.banana.databinding.FragmentSearchBinding
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent


class SearchFragment : Fragment() {
    private lateinit var recentSearchAdapter : RecentSearchAdapter
    private var _binding : FragmentSearchBinding ?= null
    private val binding get() = _binding!!
    private var recentSearchWord = ArrayList<RecentSearchData>()

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


//        recentSearchWord.clear()

        recentSearchWord.add(RecentSearchData("취준생"))
        recentSearchWord.add(RecentSearchData("대학생"))
        recentSearchWord.add(RecentSearchData("학생"))
        recentSearchWord.add(RecentSearchData("개발자"))
        recentSearchWord.add(RecentSearchData("디자이너"))
        recentSearchWord.add(RecentSearchData("고등학생"))
        recentSearchWord.add(RecentSearchData("인스타그램"))
        recentSearchWord.add(RecentSearchData("맞팔"))
        recentSearchWord.add(RecentSearchData("고등학교"))
        recentSearchWord.add(RecentSearchData("대외활동"))


        recentSearchAdapter = RecentSearchAdapter(recentSearchWord)
        binding.rvRecentSearch.adapter = recentSearchAdapter
       // binding.rvRecentSearch.layoutManager = GridLayoutManager(context,3)
        binding.rvRecentSearch.layoutManager = FlexboxLayoutManager(context).apply {
            flexWrap = FlexWrap.WRAP
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
        }


        //검색 버튼
        binding.btnSearchResult.setOnClickListener {
            val searchResultText = binding.editText.text.toString()

            setFragmentResult("searchResult", bundleOf("bundleKey" to searchResultText))
            val searchResult = SearchResultFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameArea,searchResult)
                addToBackStack(null)
                commit()
            }



        }
        return view
    }


}