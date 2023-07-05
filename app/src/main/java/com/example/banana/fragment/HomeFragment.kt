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
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.banana.CreateQRActivity
import com.example.banana.R
import com.example.banana.auth.LoginRepository
import com.example.banana.data.ResponseGetQRCode
import com.example.banana.databinding.FragmentHomeBinding
import com.example.banana.retrofit.API
import com.example.banana.retrofit.RetrofitInstance
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var retAPI : API
    fun newInstance() : HomeFragment{
        return HomeFragment()
    }

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retAPI = RetrofitInstance.retrofitInstance().create(API::class.java)


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

        binding.imageView.setImageResource(R.drawable.card011)
        binding.imageView2.setImageResource(R.drawable.card012)


        //card 01 버튼
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

        //card 02 버튼
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

            binding.imageView.setImageResource(R.drawable.card031)
            binding.imageView2.setImageResource(R.drawable.card032)

        }

        getQRCode()

        //qr코드 버튼
        binding.btnQr.setOnClickListener {

                val intent = Intent(context, CreateQRActivity::class.java)
                intent.putExtra("QrUrl", imageString)
                Log.d("urllll2", imageString.toString())

                startActivity(intent,)




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

        //검색 버튼
        binding.btnSearch.setOnClickListener {
           val search = SearchFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameArea,search)
                addToBackStack(null)
                commit()
            }
        }

        //알람 버튼
        binding.btnAlarm.setOnClickListener {

        }
    }


    //--------------------------------함수----------------------------

    //qr코드 호출 함수
    var imageString = ""
    val accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjg3OTM3MjQ3LCJleHAiOjE2OTA1MjkyNDd9.aiKbUg52Uj0rSvQTumCd_pfvc_SOlk6C4xKcaN1tZbE"
    private  fun getQRCode() {
        retAPI
            .getQRCode(accessToken)
            .enqueue(object : retrofit2.Callback<ResponseGetQRCode>{
            override fun onResponse(call: Call<ResponseGetQRCode>, response: Response<ResponseGetQRCode>) {
                if (response.isSuccessful) {



                     imageString = response.body()?.address.toString()
//                    var toBitmap = BitmapFactory.decodeByteArray(image,0,image!!.size)
//                    var bitmap = BitmapFactory.decodeStream(imageString)
//                    val imageBytes = Base64.decode(imageString,0)
//                    val image = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.size)

                    Log.d("urllll", imageString.toString())

                    Log.d("getQr Response : ", "success")

                } else {
                    Log.d("getQr Response : ", "Code: ${response.code()} , Message: ${response.message()} , Success: ${response.isSuccessful}")
                }
            }
            override fun onFailure(call: Call<ResponseGetQRCode>, t: Throwable) {
                Log.d("getQr Response : ", "Fail 2")
            }

        })
    }


    //방명록 호출 함수
    fun VisitorComments() {
        val visitorComments = VisitorCommentsFragment()
        fragmentManager?.beginTransaction()?.apply {
            replace(R.id.frameArea,visitorComments)
            addToBackStack(null)
            commit()
        }
    }





}