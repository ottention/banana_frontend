package com.example.banana.fragment

import android.R
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.banana.CreateQRActivity
import com.example.banana.MakeCardActivity
import com.example.banana.cardDetailFragment
import com.example.banana.databinding.FragmentHomeBinding
import kotlin.concurrent.fixedRateTimer


class HomeFragment : Fragment() {

    fun newInstance() : HomeFragment{
        return HomeFragment()
    }

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

        //초기 card 01
        binding.btnCard01.setBackgroundColor(Color.parseColor("#000000"))
        binding.btnCard01.setTextColor(Color.parseColor("#ffffff"))

        binding.imageView.setImageResource(com.example.banana.R.drawable.card011)
        binding.imageView2.setImageResource(com.example.banana.R.drawable.card012)


        //card 01 버튼
        binding.btnCard01.setOnClickListener {
            //버튼 색 변경
            binding.btnCard01.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnCard01.setTextColor(Color.parseColor("#ffffff"))

            binding.btnCard02.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard02.setTextColor(Color.parseColor("#f0f0f0"))

            binding.btnCard03.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard03.setTextColor(Color.parseColor("#f0f0f0"))

            binding.imageView.setImageResource(com.example.banana.R.drawable.card011)
            binding.imageView2.setImageResource(com.example.banana.R.drawable.card012)
        }

        //card 02 버튼
        binding.btnCard02.setOnClickListener {
            //버튼 색 변경
            binding.btnCard02.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnCard02.setTextColor(Color.parseColor("#ffffff"))

            binding.btnCard01.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard01.setTextColor(Color.parseColor("#f0f0f0"))

            binding.btnCard03.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard03.setTextColor(Color.parseColor("#f0f0f0"))

            binding.imageView.setImageResource(com.example.banana.R.drawable.card021)
            binding.imageView2.setImageResource(com.example.banana.R.drawable.card022)
        }

        //card 03 버튼
        binding.btnCard03.setOnClickListener {
            Log.d("card3","card3")
            //버튼 색 변경
            binding.btnCard03.setBackgroundColor(Color.parseColor("#000000"))
            binding.btnCard03.setTextColor(Color.parseColor("#ffffff"))

            binding.btnCard02.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard02.setTextColor(Color.parseColor("#f0f0f0"))

            binding.btnCard01.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnCard01.setTextColor(Color.parseColor("#f0f0f0"))

            binding.imageView.setImageResource(com.example.banana.R.drawable.card031)
            binding.imageView2.setImageResource(com.example.banana.R.drawable.card032)
        }


        binding.imageView.setOnClickListener {

            val detailedCard = cardDetailFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(com.example.banana.R.id.frameArea, detailedCard)
                addToBackStack(null)
                commit()
            }
        }

        binding.imageView2.setOnClickListener {

//            val detailedCard = cardDetailFragment()
//            fragmentManager?.beginTransaction()?.apply {
//                replace(com.example.banana.R.id.frameArea, detailedCard)
//                addToBackStack(null)
//                commit()
//            }

            startActivity(Intent(context, MakeCardActivity::class.java))
        }
        //qr코드 버튼
        binding.btnQr.setOnClickListener {
            val intent = Intent(context, CreateQRActivity::class.java)
            startActivity(intent)
        }



        //소셜 공유 버튼
        binding.btnSocialShare.setOnClickListener {
            val popupBase = binding.btnSocialShare
            Log.d("social","click")
            var pop = PopupMenu(context, popupBase)

            pop.menuInflater?.inflate(com.example.banana.R.menu.social_popup,pop.menu)

            pop.show()

            pop.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    com.example.banana.R.id.kakaotalk ->
                        Toast.makeText(context,"kakaotalk",Toast.LENGTH_SHORT).show()
                    com.example.banana.R.id.instagram ->
                        Toast.makeText(context, "instagram",Toast.LENGTH_SHORT).show()

                }
                false
            }
        }

        //검색 버튼
        binding.btnSearch.setOnClickListener {
            Toast.makeText(context, "search",Toast.LENGTH_SHORT).show()
        }

        //알람 버튼
        binding.btnAlarm.setOnClickListener {
            Toast.makeText(context, "alarm",Toast.LENGTH_SHORT).show()
        }
    }




}