package com.example.banana.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banana.adapter.CommentsAdapter
import com.example.banana.databinding.FragmentMyCardCommentsBinding
import com.example.banana.retrofit.API
import com.example.banana.viewModel.MyCardCommentsViewModel


class MyCardCommentsFragment : Fragment() {

    private lateinit var API : API


    private lateinit var commentsAdapter: CommentsAdapter
    private var _binding : FragmentMyCardCommentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyCardCommentsViewModel


    fun newInstance() : MyCardCommentsFragment {
        return MyCardCommentsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyCardCommentsBinding.inflate(inflater,container,false)
        val view = binding.root


        viewModel = ViewModelProvider(this).get(MyCardCommentsViewModel::class.java)
        commentsAdapter = CommentsAdapter()

        binding.rvComments.adapter = commentsAdapter
        binding.rvComments.layoutManager = LinearLayoutManager(context)

        viewModel.commentsList.observe(viewLifecycleOwner){
            commentsAdapter.updateList(it)
        }

//        myCardCommentsCard1()
//
//
        //card01 클릭
        binding.btnCard01.setOnClickListener {
            myCardCommentsCard1()
        }
//
        //card02 클릭
        binding.btnCard02.setOnClickListener {
            myCardCommentsCard2()
        }
//
        //card03 클릭

        binding.btnCard03.setOnClickListener {
            myCardCommentsCard3()
        }
        return view
    }




    //---------------------------------------------------


    fun myCardCommentsCard1() {

        binding.btnCard01.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnCard02.setBackgroundColor(Color.parseColor("#C3C3C3"))
        binding.btnCard03.setBackgroundColor(Color.parseColor("#C3C3C3"))

        viewModel.showCommentsList1()

        viewModel.commentsList.observe(viewLifecycleOwner){
            commentsAdapter.updateList(it)
        }


    }
//
    fun myCardCommentsCard2() {

        binding.btnCard02.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnCard01.setBackgroundColor(Color.parseColor("#C3C3C3"))
        binding.btnCard03.setBackgroundColor(Color.parseColor("#C3C3C3"))

    viewModel.showCommentsList2()

    viewModel.commentsList.observe(viewLifecycleOwner){
        commentsAdapter.updateList(it)
    }

    }
//
    fun myCardCommentsCard3() {

        binding.btnCard03.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnCard02.setBackgroundColor(Color.parseColor("#C3C3C3"))
        binding.btnCard01.setBackgroundColor(Color.parseColor("#C3C3C3"))

    viewModel.showCommentsList3()

    viewModel.commentsList.observe(viewLifecycleOwner){
        commentsAdapter.updateList(it)
    }
    }


}