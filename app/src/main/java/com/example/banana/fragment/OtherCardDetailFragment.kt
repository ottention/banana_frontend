package com.example.banana.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.banana.R
import com.example.banana.addNotebottomDialog
import com.example.banana.addTagbottomDialog
import com.example.banana.changeMainCardDialog
import com.example.banana.data.ResponseNote
import com.example.banana.data.note
import com.example.banana.databinding.FragmentAlarmBinding
import com.example.banana.databinding.FragmentOtherCardDetailBinding
import com.example.banana.viewModel.DetailCardDataViewModel
import com.example.banana.viewModel.MyCardCommentsViewModel

class OtherCardDetailFragment : Fragment() {

    lateinit var fragmentActivity: FragmentActivity
    private var _binding: FragmentOtherCardDetailBinding? = null
    private lateinit var viewModel: DetailCardDataViewModel
    private var memoDataList: ArrayList<ResponseNote> = arrayListOf()
    private var cardId: Long = 1

    private val binding get() = _binding!!
    fun newInstance(): OtherCardDetailFragment {
        return OtherCardDetailFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentActivity = context as FragmentActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailCardDataViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.MemoData.observe(viewLifecycleOwner) {
            memoDataList.clear()
            memoDataList = it
        }

        var btns = listOf<Button>(binding.btnAddNote1, binding.btnAddNote2)

        _binding = FragmentOtherCardDetailBinding.inflate(inflater, container, false)

        // 새로 추가하기
        var onaddNoteListner = View.OnClickListener {
            if (it is Button) {
                val bottomSheet: addNotebottomDialog =
                    addNotebottomDialog(fragmentActivity, it.text.toString())
                bottomSheet.listener = object : addNotebottomDialog.addNoteListner {
                    override fun save(note: String) {
                        if (it is Button && (note != null || note != "")) {
                            it.text = note
                            it.background = resources.getDrawable(R.drawable.bg_cotent_note)
                            var note: note = note(it.text.toString())
                            viewModel.addNote(cardId, note)
                        }

                        viewModel.MemoData.observe(viewLifecycleOwner) {
                            memoDataList.clear()
                            memoDataList = it
                        }
                    }
                }
                bottomSheet.show(parentFragmentManager, bottomSheet.tag)
            }
        }

        if (memoDataList.size == 1) {
            binding.btnAddNote1.text = memoDataList[1].content
            binding.btnAddNote1.background = resources.getDrawable(R.drawable.bg_cotent_note)

        } else if (memoDataList.size == 2) {
            binding.btnAddNote1.text = memoDataList[1].content
            binding.btnAddNote1.background = resources.getDrawable(R.drawable.bg_cotent_note)
            binding.btnAddNote2.text = memoDataList[1].content
            binding.btnAddNote2.background = resources.getDrawable(R.drawable.bg_cotent_note)
        }

        for (i in 0..btns.size - 1) {
            if (btns[i].text.toString().length == 0) {
                btns[i].setOnClickListener(onaddNoteListner)
            } else {
                btns[i].setOnClickListener {
                    View.OnClickListener {
                        if (it is Button) {
                            val bottomSheet: addNotebottomDialog =
                                addNotebottomDialog(fragmentActivity, it.text.toString())
                            bottomSheet.listener = object : addNotebottomDialog.addNoteListner {
                                override fun save(note: String) {
                                    if (it is Button && (note != null || note != "")) {
                                        it.text = note
                                        it.background =
                                            resources.getDrawable(R.drawable.bg_cotent_note)
                                        var Newnote = note(it.text.toString())
                                        viewModel.updateNote(cardId, memoDataList[i].id, Newnote)
                                    }

                                    viewModel.MemoData.observe(viewLifecycleOwner) {
                                        memoDataList.clear()
                                        memoDataList = it
                                    }
                                }
                            }
                            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
                        }
                    }
                }

            }
        }

        val view = binding.root
        return view
    }


}


