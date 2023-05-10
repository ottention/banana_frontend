package com.example.banana.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.banana.R
import com.example.banana.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCard01.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnCard01.setTextColor(Color.parseColor("#ffffff"))

        binding.imageView.setImageResource(R.drawable.card011)
        binding.imageView2.setImageResource(R.drawable.card012)

        binding.btnCard01.setOnClickListener {
            //버튼 색 변경
            binding.btnCard01.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnCard01.setTextColor(Color.parseColor("#ffffff"))

            binding.btnCard02.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard02.setTextColor(Color.parseColor("#f0f0f0"))

            binding.btnCard03.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard03.setTextColor(Color.parseColor("#f0f0f0"))

            binding.imageView.setImageResource(R.drawable.card011)
            binding.imageView2.setImageResource(R.drawable.card012)
        }

        binding.btnCard02.setOnClickListener {
            //버튼 색 변경
            binding.btnCard02.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnCard02.setTextColor(Color.parseColor("#ffffff"))

            binding.btnCard01.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard01.setTextColor(Color.parseColor("#f0f0f0"))

            binding.btnCard03.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard03.setTextColor(Color.parseColor("#f0f0f0"))

            binding.imageView.setImageResource(R.drawable.card021)
            binding.imageView2.setImageResource(R.drawable.card022)
        }

        binding.btnCard03.setOnClickListener {
            //버튼 색 변경
            binding.btnCard03.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnCard03.setTextColor(Color.parseColor("#ffffff"))

            binding.btnCard02.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard02.setTextColor(Color.parseColor("#f0f0f0"))

            binding.btnCard01.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard01.setTextColor(Color.parseColor("#f0f0f0"))

            binding.imageView.setImageResource(R.drawable.card031)
            binding.imageView2.setImageResource(R.drawable.card032)
        }
    }


}