package com.example.banana.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banana.adapter.CommentsAdapter
import com.example.banana.databinding.FragmentCommentsIwroteBinding
import com.example.banana.viewModel.CommentsIwroteViewModel


class CommentsIwroteFragment : Fragment() {

    private lateinit var commentsAdapter: CommentsAdapter
    private var _binding : FragmentCommentsIwroteBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CommentsIwroteViewModel



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



        viewModel = ViewModelProvider(this).get(CommentsIwroteViewModel::class.java)

        commentsAdapter = CommentsAdapter()

        binding.rvComments.adapter = commentsAdapter
        binding.rvComments.layoutManager = LinearLayoutManager(context)

        viewModel.commentsList.observe(viewLifecycleOwner) {
            commentsAdapter.updateList(it)
        }
            return view
    }


}