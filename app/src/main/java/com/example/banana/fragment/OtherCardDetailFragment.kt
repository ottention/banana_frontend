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
import com.example.banana.R
import com.example.banana.addNotebottomDialog
import com.example.banana.addTagbottomDialog
import com.example.banana.changeMainCardDialog
import com.example.banana.databinding.FragmentAlarmBinding
import com.example.banana.databinding.FragmentOtherCardDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OtherCardDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OtherCardDetailFragment : Fragment() {

    lateinit var fragmentActivity: FragmentActivity
    private var _binding : FragmentOtherCardDetailBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentActivity = context as FragmentActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOtherCardDetailBinding.inflate(inflater,container,false)
        var onClickListener = View.OnClickListener {
            if(it is Button) {
                val bottomSheet: addNotebottomDialog = addNotebottomDialog(fragmentActivity, it.text.toString())
                bottomSheet.listener = object: addNotebottomDialog.addNoteListner {
                    override fun save(note : String) {
                        if(it is Button && (note !=null || note != "")){
                            it.text = note
                            it.background = resources.getDrawable(R.drawable.bg_cotent_note)
                        }
                    }
                }
                bottomSheet.show(parentFragmentManager, bottomSheet.tag)
            }
        }

        binding.btnAddNote1.setOnClickListener(onClickListener)
        binding.btnAddNote2.setOnClickListener(onClickListener)

        val view = binding.root
        return view
    }


}

