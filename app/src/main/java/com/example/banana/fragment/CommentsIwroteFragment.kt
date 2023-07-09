package com.example.banana.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banana.adapter.CommentsAdapter
import com.example.banana.data.CommentsData
import com.example.banana.databinding.FragmentCommentsIwroteBinding


class CommentsIwroteFragment : Fragment() {

    private lateinit var commentsAdapter: CommentsAdapter
    private var _binding : FragmentCommentsIwroteBinding? = null
    private val binding get() = _binding!!
    private var commentData = ArrayList<CommentsData>()


    fun newInstance() : CommentsIwroteFragment {
        return CommentsIwroteFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentsIwroteBinding.inflate(inflater,container,false)
        val view = binding.root

        commentsIwrote()


        return view
    }

    fun commentsIwrote() {

        commentData.add(CommentsData("writer","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.08"))
        commentData.add(CommentsData("writer","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.07"))
        commentData.add(CommentsData("writer","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.06"))
        commentData.add(CommentsData("writer","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.05"))
        commentData.add(CommentsData("writer","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.08"))
        commentData.add(CommentsData("writer","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.07"))
        commentData.add(CommentsData("writer","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.06"))
        commentData.add(CommentsData("writer","타인의 방명록에서는 자신이 작성한 댓글만 볼 수 있음",false,"23.08.05"))

        commentsAdapter = CommentsAdapter()
        binding.rvComments.adapter = commentsAdapter
        binding.rvComments.layoutManager = LinearLayoutManager(context)
    }


}