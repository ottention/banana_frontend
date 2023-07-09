package com.example.banana.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.banana.R
import com.example.banana.adapter.WalletAdapter
import com.example.banana.data.WalletData
import com.example.banana.databinding.FragmentWalletBinding


class WalletFragment : Fragment() {

    private lateinit var walletAdapter: WalletAdapter
    private var _binding : FragmentWalletBinding? = null
    private val binding get() = _binding!!
    private var walletData = ArrayList<WalletData>()

    fun newInstance() : WalletFragment{
        return WalletFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWalletBinding.inflate(inflater, container, false)
        val view = binding.root



        //기본 명함 지갑 설정
        defaultWallet()
        binding.recentMarkedCard1.setOnClickListener {
            Toast.makeText(context, "즐겨찾기 ->명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
        }
        binding.recentMarkedCard2.setOnClickListener {
            Toast.makeText(context, "즐겨찾기 ->명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
        }

        //전체 명함 버튼
        binding.btnAll.setOnClickListener{
            allCard()
        }

        //즐겨 찾기 버튼
        binding.btnMarked.setOnClickListener {
            markedCard()
        }

        //저장 폴더 버튼
        binding.btnFolder.setOnClickListener {
            folder()
        }


        //검색 버튼
        binding.btnSearch.setOnClickListener {
           search()
        }

        //알람 버튼
        binding.btnAlarm.setOnClickListener {
            alarm()
        }

        return view
    }


    //----------------------------------------함수-----------------------------------------


    // wallet 기본 설정
    fun defaultWallet() {
        binding.walletText.setText("최근 저장된 명함")
        binding.walletText.visibility = View.VISIBLE
        binding.btnAll.setBackgroundResource(R.drawable.border_underline)
        binding.btnMarked.setBackgroundColor(Color.parseColor("#ffffff"))
        binding.btnFolder.setBackgroundColor(Color.parseColor("#ffffff"))
        walletData.clear()

        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))

        walletAdapter = WalletAdapter(walletData)
        binding.rvWallet.adapter = walletAdapter
        binding.rvWallet.layoutManager = GridLayoutManager(context,2)

        walletAdapter.itemClick = object : WalletAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                Toast.makeText(context, "전체 -> 명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //전체 명함 보기
    fun allCard() {
        binding.walletText.setText("최근 저장된 명함")
        binding.walletText.visibility = View.VISIBLE
        binding.linearLayoutMarked.visibility = View.VISIBLE
        binding.markedText.visibility = View.VISIBLE
        binding.btnAll.setBackgroundResource(R.drawable.border_underline)
        binding.btnMarked.setBackgroundColor(Color.parseColor("#ffffff"))
        binding.btnFolder.setBackgroundColor(Color.parseColor("#ffffff"))
        walletData.clear()

        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))



        walletAdapter = WalletAdapter(walletData)
        binding.rvWallet.adapter = walletAdapter
        binding.rvWallet.layoutManager = GridLayoutManager(context,2)

        walletAdapter.itemClick = object : WalletAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {


                Toast.makeText(context, "전체 -> 명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //즐겨찾기 명함 보기
    fun markedCard(){
        binding.walletText.visibility = View.GONE
        binding.linearLayoutMarked.visibility = View.GONE
        binding.markedText.visibility = View.INVISIBLE
        binding.btnMarked.setBackgroundResource(R.drawable.border_underline)
        binding.btnAll.setBackgroundColor(Color.parseColor("#ffffff"))
        binding.btnFolder.setBackgroundColor(Color.parseColor("#ffffff"))
        walletData.clear()

        walletData.add(WalletData(R.drawable.marked_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.marked_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.marked_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.marked_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.marked_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.marked_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.marked_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.marked_card,"[잇타] 한윤이(디자이너)"))



        walletAdapter = WalletAdapter(walletData)
        binding.rvWallet.adapter = walletAdapter
        binding.rvWallet.layoutManager = GridLayoutManager(context,2)

        walletAdapter.itemClick = object : WalletAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {


                Toast.makeText(context, "즐겨찾기 ->명함 상세페이지 이동",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //명함 폴더 보기
    fun folder() {
        binding.walletText.setText("저장 카테고리")
        binding.walletText.visibility = View.VISIBLE
        binding.linearLayoutMarked.visibility = View.VISIBLE
        binding.markedText.visibility = View.VISIBLE
        binding.btnFolder.setBackgroundResource(R.drawable.border_underline)
        binding.btnMarked.setBackgroundColor(Color.parseColor("#ffffff"))
        binding.btnAll.setBackgroundColor(Color.parseColor("#ffffff"))
        walletData.clear()

        walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함"))
        walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함"))
        walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함"))
        walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함"))
        walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함"))
        walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함"))
        walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함"))
        walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함"))


        walletAdapter = WalletAdapter(walletData)
        binding.rvWallet.adapter = walletAdapter
        binding.rvWallet.layoutManager = GridLayoutManager(context,2)

        walletAdapter.itemClick = object : WalletAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {

                cardInFolder()

            }
        }
    }

    //명함 폴더 클릭 시
    fun cardInFolder() {
        binding.walletText.setText("[대외활동] 잇타 멤버들 명함")
        binding.walletText.visibility = View.VISIBLE
        binding.markedText.visibility = View.GONE
        binding.linearLayoutMarked.visibility = View.GONE
        binding.btnFolder.setBackgroundResource(R.drawable.border_underline)
        binding.btnMarked.setBackgroundColor(Color.parseColor("#ffffff"))
        binding.btnAll.setBackgroundColor(Color.parseColor("#ffffff"))
        walletData.clear()

        walletData.add(WalletData(R.drawable.marked_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))
        walletData.add(WalletData(R.drawable.default_card,"[잇타] 한윤이(디자이너)"))



        walletAdapter = WalletAdapter(walletData)
        binding.rvWallet.adapter = walletAdapter
        binding.rvWallet.layoutManager = GridLayoutManager(context,2)

    }

    fun alarm() {
        Toast.makeText(context, "alarm", Toast.LENGTH_SHORT).show()
    }

    fun search() {
        Toast.makeText(context, "search", Toast.LENGTH_SHORT).show()
    }


}