package com.example.banana.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.banana.CreateQRActivity
import com.example.banana.R
import com.example.banana.databinding.FragmentHomeBinding

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
            Log.d("card3","card3")
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

            pop.menuInflater?.inflate(R.menu.social_popup,pop.menu)

            pop.show()

            pop.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.kakaotalk ->
                        Toast.makeText(context,"kakaotalk",Toast.LENGTH_SHORT).show()
                    R.id.instagram ->
                        Toast.makeText(context, "instagram",Toast.LENGTH_SHORT).show()

                }
                false
            }
        }
    }




}