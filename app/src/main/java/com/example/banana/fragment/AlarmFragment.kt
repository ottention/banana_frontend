package com.example.banana.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banana.adapter.AlarmAdapter
import com.example.banana.R
import com.example.banana.data.AlarmData
import com.example.banana.databinding.FragmentAlarmBinding
import com.example.banana.viewModel.AlarmViewModel
import com.example.banana.viewModel.SearchViewModel


class AlarmFragment : Fragment() {


    private lateinit var alarmAdapter: AlarmAdapter
    private var _binding : FragmentAlarmBinding?= null
    private val binding get() = _binding!!
    private var alarmData = ArrayList<AlarmData>()
    private lateinit var viewModel : AlarmViewModel
    fun newInstance() : AlarmFragment {
        return AlarmFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAlarmBinding.inflate(inflater,container,false)
        val view = binding.root

                alarmData.add(AlarmData("23.03.03","000님이 방명록에 좋아요를 눌렀습니다."))
        alarmData.add(AlarmData("23.03.02","001님이 방명록에 좋아요를 눌렀습니다."))
        alarmData.add(AlarmData("23.03.01","002님이 방명록에 좋아요를 눌렀습니다."))
        alarmData.add(AlarmData("23.03.02","003님이 방명록에 좋아요를 눌렀습니다."))
        alarmData.add(AlarmData("23.02.20","004님이 방명록에 좋아요를 눌렀습니다."))
        alarmData.add(AlarmData("23.02.20","005님이 방명록에 좋아요를 눌렀습니다."))
        alarmData.add(AlarmData("23.02.20","006님이 방명록에 좋아요를 눌렀습니다."))
        alarmData.add(AlarmData("23.02.20","007님이 방명록에 좋아요를 눌렀습니다."))
        alarmData.add(AlarmData("23.02.20","008님이 방명록에 좋아요를 눌렀습니다."))
        viewModel = ViewModelProvider(this).get(AlarmViewModel::class.java)


        viewModel.alarmList.observe(viewLifecycleOwner) {
            alarmAdapter.updateList(it)
        }

        alarmAdapter = AlarmAdapter(alarmData)
        binding.rvAlarm.adapter = alarmAdapter
        binding.rvAlarm.layoutManager = LinearLayoutManager(context)



//            alarmAdapter.itemClick = object : AlarmAdapter.ItemClick {
//                override fun onClick(view: View, position: Int) {
//
//                    Log.d("click", alarmData[position].toString())
//                    alarmData.removeAt(position)
//                    viewModel.alarmList.observe(viewLifecycleOwner) {
//                        alarmAdapter.updateList(it)
//                    }
//                    Log.d("delete", alarmData.toString())
////                    Toast.makeText(context,"방명록 삭제",Toast.LENGTH_SHORT).show()
//
//                }
//
//        }
//        alarmAdapter.itemClick = object : AlarmAdapter.ItemClick {
//            override fun onClick(view: View, position: Int) {
//
//                Log.d("click", alarmData[position].toString())
//                alarmData.removeAt(position)
//                alarmAdapter.updateList(alarmData)
//                Log.d("delete", alarmData.toString())
//                Toast.makeText(context,"방명록 삭제",Toast.LENGTH_SHORT).show()
//
//            }
//        }
        //알람 호출
//        showAlarm()

        //전체 삭제 버튼
        binding.deleteAll.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.alarm_delete_all_dialog,null)

            val mBuilder = AlertDialog.Builder(context!!)
                .setView(mDialogView)

           val dialog =  mBuilder.show()

            val deleteAllYes = mDialogView.findViewById<Button>(R.id.btn_deleteAllYes)
            val deleteAllNo = mDialogView.findViewById<Button>(R.id.btn_deleteAllNo)

            deleteAllYes.setOnClickListener {
                alarmData.clear()


                    alarmAdapter.updateList(alarmData)


                Log.d("deleteAll", alarmData.toString())
                dialog.dismiss()
            }

            deleteAllNo.setOnClickListener {
                dialog.dismiss()
            }



        }

        //검색 버튼
        binding.btnSearch.setOnClickListener {
            val search = SearchFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameArea,search)
//                addToBackStack(null)
                commit()
            }
        }

        //뒤로가기 버튼
        binding.btnBack.setOnClickListener {
            val home = HomeFragment()
            fragmentManager?.beginTransaction()?.apply {
                replace(R.id.frameArea,home)
//                addToBackStack(null)
                commit()
            }

        }

        return view
    }

    //--------------------------------함수----------------------

    fun showAlarm() {
//        alarmData.add(AlarmData("23.03.03","000님이 방명록에 좋아요를 눌렀습니다."))
//        alarmData.add(AlarmData("23.03.02","001님이 방명록에 좋아요를 눌렀습니다."))
//        alarmData.add(AlarmData("23.03.01","002님이 방명록에 좋아요를 눌렀습니다."))
//        alarmData.add(AlarmData("23.03.02","003님이 방명록에 좋아요를 눌렀습니다."))
//        alarmData.add(AlarmData("23.02.20","004님이 방명록에 좋아요를 눌렀습니다."))
//        alarmData.add(AlarmData("23.02.20","005님이 방명록에 좋아요를 눌렀습니다."))
//        alarmData.add(AlarmData("23.02.20","006님이 방명록에 좋아요를 눌렀습니다."))
//        alarmData.add(AlarmData("23.02.20","007님이 방명록에 좋아요를 눌렀습니다."))
//        alarmData.add(AlarmData("23.02.20","008님이 방명록에 좋아요를 눌렀습니다."))


        alarmAdapter = AlarmAdapter(alarmData)
        binding.rvAlarm.adapter = alarmAdapter
        binding.rvAlarm.layoutManager = LinearLayoutManager(context)


        alarmAdapter.itemClick = object : AlarmAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {

                Toast.makeText(context,"방명록 삭제",Toast.LENGTH_SHORT).show()

            }
        }
    }


}