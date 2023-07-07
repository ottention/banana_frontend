package com.example.banana.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.banana.R
import com.example.banana.databinding.FragmentHomeBinding
import com.example.banana.databinding.FragmentVisitorCommentsBinding
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance


class VisitorCommentsFragment : Fragment() {

    private lateinit var API : API
    private var _binding : FragmentVisitorCommentsBinding? = null
    private val binding get() = _binding!!

    fun newInstance() : VisitorCommentsFragment {
        return VisitorCommentsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        API = RetrofitInstance.retrofitInstance().create(API::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVisitorCommentsBinding.inflate(inflater,container,false)
        val view = binding.root


        //초기 설정
        visitorComments()

        //나의 명함 방명록
        binding.btnMyCardComments.setOnClickListener {

            binding.btnMyCardComments.setBackgroundResource(R.drawable.border_underline)
            binding.btnCommentsIwrote.setBackgroundColor(Color.parseColor("#FFFFFF"))

            val MyComment1 = MyCardCommentsFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.visitorComments_FrameLayout,MyComment1)
                addToBackStack(null)
                commit()
            }
        }

        //내가 작성한 방명록
        binding.btnCommentsIwrote.setOnClickListener {

            binding.btnMyCardComments.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.btnCommentsIwrote.setBackgroundResource(R.drawable.border_underline)

            val MyComment2 = CommentsIwroteFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.visitorComments_FrameLayout,MyComment2)
                addToBackStack(null)
                commit()
            }
        }


        return view
    }

    fun visitorComments() {
        binding.btnMyCardComments.setBackgroundResource(R.drawable.border_underline)
        binding.btnCommentsIwrote.setBackgroundColor(Color.parseColor("#FFFFFF"))

        val MyComment1 = MyCardCommentsFragment()
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.visitorComments_FrameLayout,MyComment1)
            addToBackStack(null)
            commit()
        }
    }




}