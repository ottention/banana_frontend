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
import com.example.banana.WalletAdapter
import com.example.banana.data.WalletData
import com.example.banana.databinding.FragmentHomeBinding
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

        //전체 명함 버튼
        binding.btnAll.setOnClickListener{
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
        }

        //즐겨 찾기 버튼
        binding.btnMarked.setOnClickListener {
            binding.walletText.visibility = View.INVISIBLE
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

        }

        //저장 폴더 버튼
        binding.btnFolder.setOnClickListener {
            binding.walletText.setText("저장 카테고리")
            binding.walletText.visibility = View.VISIBLE
            binding.btnFolder.setBackgroundResource(R.drawable.border_underline)
            binding.btnMarked.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.btnAll.setBackgroundColor(Color.parseColor("#ffffff"))
            walletData.clear()

            walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함)"))
            walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함)"))
            walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함)"))
            walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함)"))
            walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함)"))
            walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함)"))
            walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함)"))
            walletData.add(WalletData(R.drawable.wallet_folder,"[대외활동] 잇타 멤버들 명함)"))

            walletAdapter = WalletAdapter(walletData)
            binding.rvWallet.adapter = walletAdapter
            binding.rvWallet.layoutManager = GridLayoutManager(context,2)

        }

        //검색 버튼
        binding.btnSearch.setOnClickListener {
            Toast.makeText(context, "search", Toast.LENGTH_SHORT).show()
        }

        //알람 버튼
        binding.btnAlarm.setOnClickListener {
            Toast.makeText(context, "alarm", Toast.LENGTH_SHORT).show()
        }

        return view
    }


}