package com.example.banana.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banana.CommentsAdapter
import com.example.banana.R
import com.example.banana.data.CommentsData
import com.example.banana.data.comment
import com.example.banana.databinding.FragmentCommentsIwroteBinding
import com.example.banana.databinding.FragmentMyCardCommentsBinding
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import com.example.banana.viewModel.MyCardCommentsViewModel


class MyCardCommentsFragment : Fragment() {

    private lateinit var API : API


    private lateinit var commentsAdapter: CommentsAdapter
    private var _binding : FragmentMyCardCommentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MyCardCommentsViewModel
//    private var commentData = ArrayList<CommentsData>()


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
//        //card01 클릭
//        binding.btnCard01.setOnClickListener {
//            myCardCommentsCard1()
//        }
//
//        //card02 클릭
//        binding.btnCard02.setOnClickListener {
//            myCardCommentsCard2()
//        }
//
//        //card03 클릭
//
//        binding.btnCard03.setOnClickListener {
//            myCardCommentsCard3()
//        }
        return view
    }




    //---------------------------------------------------


//    fun myCardCommentsCard1() {
//
//        binding.btnCard01.setBackgroundColor(Color.parseColor("#000000"))
//        binding.btnCard02.setBackgroundColor(Color.parseColor("#C3C3C3"))
//        binding.btnCard03.setBackgroundColor(Color.parseColor("#C3C3C3"))
//
//        commentData.clear()
//        commentData.add(CommentsData("kim","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음1",false,"23.08.08"))
//        commentData.add(CommentsData("shin","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.07"))
//        commentData.add(CommentsData("choi","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.06"))
//        commentData.add(CommentsData("lim","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.05"))
//        commentData.add(CommentsData("kim","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.08"))
//        commentData.add(CommentsData("shin","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.07"))
//        commentData.add(CommentsData("choi","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.06"))
//        commentData.add(CommentsData("lim","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.05"))
//
//        commentsAdapter = CommentsAdapter(commentData)
//        binding.rvComments.adapter = commentsAdapter
//        binding.rvComments.layoutManager = LinearLayoutManager(context)
//    }
//
//    fun myCardCommentsCard2() {
//
//        binding.btnCard02.setBackgroundColor(Color.parseColor("#000000"))
//        binding.btnCard01.setBackgroundColor(Color.parseColor("#C3C3C3"))
//        binding.btnCard03.setBackgroundColor(Color.parseColor("#C3C3C3"))
//
//        commentData.clear()
//        commentData.add(CommentsData("kim","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음2",false,"23.08.08"))
//        commentData.add(CommentsData("shin","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.07"))
//        commentData.add(CommentsData("choi","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.06"))
//        commentData.add(CommentsData("lim","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.05"))
//        commentData.add(CommentsData("kim","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.08"))
//        commentData.add(CommentsData("shin","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.07"))
//        commentData.add(CommentsData("choi","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.06"))
//        commentData.add(CommentsData("lim","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.05"))
//
//        commentsAdapter = CommentsAdapter(commentData)
//        binding.rvComments.adapter = commentsAdapter
//        binding.rvComments.layoutManager = LinearLayoutManager(context)
//    }
//
//    fun myCardCommentsCard3() {
//
//        binding.btnCard03.setBackgroundColor(Color.parseColor("#000000"))
//        binding.btnCard02.setBackgroundColor(Color.parseColor("#C3C3C3"))
//        binding.btnCard01.setBackgroundColor(Color.parseColor("#C3C3C3"))
//
//        commentData.clear()
//        commentData.add(CommentsData("kim","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음3",false,"23.08.08"))
//        commentData.add(CommentsData("shin","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.07"))
//        commentData.add(CommentsData("choi","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.06"))
//        commentData.add(CommentsData("lim","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.05"))
//        commentData.add(CommentsData("kim","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.08"))
//        commentData.add(CommentsData("shin","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.07"))
//        commentData.add(CommentsData("choi","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.06"))
//        commentData.add(CommentsData("lim","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.05"))
//
//        commentsAdapter = CommentsAdapter(commentData)
//        binding.rvComments.adapter = commentsAdapter
//        binding.rvComments.layoutManager = LinearLayoutManager(context)
//    }


}