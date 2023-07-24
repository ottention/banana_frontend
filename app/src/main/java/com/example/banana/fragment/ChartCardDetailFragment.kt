package com.example.banana.fragment

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banana.R
import com.example.banana.adapter.otherCardCommentAdapter
import com.example.banana.data.ccomment
import com.example.banana.data.comment
import com.example.banana.databinding.FragmentChartCardDetailBinding
import com.example.banana.editTextDialog
import com.example.banana.viewModel.CommentDetailViewModel
import com.example.banana.viewModel.likeUnlikeViewModel
import com.google.android.material.internal.ViewUtils.hideKeyboard


class ChartCardDetailFragment : Fragment() {

    private var _binding : FragmentChartCardDetailBinding? = null
    lateinit var commentList : ArrayList<comment>
    lateinit var rvAdapter: otherCardCommentAdapter
    private val binding get() = _binding!!
    private lateinit var commentViewModel: CommentDetailViewModel
    private lateinit var likeViewModel : likeUnlikeViewModel


    var imm : InputMethodManager? = null
    var isGeustbookLike : Boolean = false;


    fun newInstance() : ChartCardDetailFragment{
        return ChartCardDetailFragment()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChartCardDetailBinding.inflate(inflater, container, false)
        commentViewModel = ViewModelProvider(this).get(CommentDetailViewModel::class.java)
        likeViewModel = ViewModelProvider(this).get(likeUnlikeViewModel::class.java)

        var c : comment = comment(0L, "", "", true, "")
        commentList = arrayListOf(c)
        commentViewModel.getCommentToSpCard(1)
        commentViewModel.commentsList.observe(viewLifecycleOwner){
            commentList = it
            makeUI(commentList)
        }

        if (commentList.size != 0){
            isGeustbookLike = commentList[0].isGuestBookLike
        }

        val view = binding.root

        return view

    }

    fun makeUI(commentList : ArrayList<comment>){
        rvAdapter = otherCardCommentAdapter(context!!, commentList, {updateComment(it)}, {deleteComment(it)})
        binding.commentRvArea.layoutManager = LinearLayoutManager(context)
        binding.commentRvArea.adapter = rvAdapter


        Log.d("TAG", commentList.toString())
    }

    fun updateComment(comment: comment){
        binding.commentArea.setText(comment.content)
        showSoftKeyboard(binding.commentArea);
        commentViewModel.removeComment(comment.guestBookId)
        commentViewModel.commentsList.observe(viewLifecycleOwner) {
            rvAdapter.updateList(it)
        }
    }

    fun deleteComment(comment: comment){
        commentViewModel.removeComment(comment.guestBookId)
        commentViewModel.commentsList.observe(viewLifecycleOwner) {
            rvAdapter.updateList(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(isGeustbookLike)
            binding.btnHeart.background = resources.getDrawable(R.drawable.favorite_selected)
        else
            binding.btnHeart.background = resources.getDrawable(R.drawable.favorite_unselected)

        binding.btnAddComment.setOnClickListener {
            var text = binding.commentArea.text.toString()
            var comment : ccomment = ccomment(text)

            if(text.length > 0) {
                commentViewModel.addComment(comment);
            }

            commentViewModel.commentsList.observe(viewLifecycleOwner) {
                rvAdapter.updateList(it)
            }

            hideKeyboard(binding.commentArea!!);

        }

        binding.btnHeart.setOnClickListener {
            if(isGeustbookLike){ // 좋아했던 것을 클릭 -> 좋아요 취소이므로
                binding.btnHeart.background = resources.getDrawable(R.drawable.favorite_unselected) // 좋아요 취소
                likeViewModel.unlike(1);
                isGeustbookLike = false
            }else {
                binding.btnHeart.background = resources.getDrawable(R.drawable.favorite_selected) // 좋아요
                likeViewModel.like(1);
                isGeustbookLike = true
            }
            likeViewModel.likeInfo.observe(viewLifecycleOwner){
                binding.numberOfLike.setText(it.likeCount.toString())
            }
        }

    }

    fun hideKeyboard(v : View){
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }

    fun showSoftKeyboard(editText: EditText) {
        if (editText.requestFocus()) {
            val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
        }
    }

}

