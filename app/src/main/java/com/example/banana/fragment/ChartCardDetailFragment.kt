package com.example.banana.fragment
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.banana.data.ccomment
import com.example.banana.databinding.FragmentChatCardDetailBinding
import com.example.banana.viewModel.CommentDetailViewModel


class ChartCardDetailFragment : Fragment() {

    private var _binding : FragmentChatCardDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CommentDetailViewModel
    var imm : InputMethodManager? = null

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

        _binding = FragmentChatCardDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(CommentDetailViewModel::class.java)

        binding.btnAddComment.setOnClickListener {
            var text = binding.commentArea.text.toString()
            var comment : ccomment = ccomment(text)

            if(text.length > 0) {
                viewModel.addChart(comment);
            }
            viewModel.commentId.observe(viewLifecycleOwner) {
                Log.d("comment Id", it.toString())
            }

            hideKeyboard(binding.commentArea!!);

        }
        val view = binding.root

        return view

    }

    fun hideKeyboard(v : View){
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }


}

