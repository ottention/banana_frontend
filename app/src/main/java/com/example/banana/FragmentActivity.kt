package com.example.banana

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.banana.fragment.ChartFragment
import com.example.banana.fragment.HomeFragment
import com.example.banana.fragment.WalletFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class FragmentActivity : AppCompatActivity() {

    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val transaction = manager.beginTransaction()
        val Homefragment = HomeFragment().newInstance()
        val ChartFragment = ChartFragment().newInstance()
        val WalletFragment = WalletFragment().newInstance()
        transaction.replace(R.id.frameArea, Homefragment)
        transaction.addToBackStack(null)
        transaction.commit()


        val bottom_nav_menu = findViewById<BottomNavigationView>(R.id.bottom_nav_menu)
        bottom_nav_menu.setOnItemSelectedListener { item ->
            when(item.itemId)  {
                R.id.tab1 -> {
                    val transaction = manager.beginTransaction()
                    transaction.replace(R.id.frameArea, ChartFragment)
                    transaction.commit()
                    true
                }
                R.id.tab2 -> {
                    val transaction = manager.beginTransaction()
                    transaction.replace(R.id.frameArea, Homefragment)
                    transaction.commit()
                    true
                }
                R.id.tab3 -> {
                    val transaction = manager.beginTransaction()
                    transaction.replace(R.id.frameArea, WalletFragment)
                    transaction.commit()
                    true
                }
                R.id.tab4 -> {
                    val intent = Intent(this, MakeActivity::class.java)
                    startActivity(intent)

                    true
                }
                else -> false
        }

        }
    }
}