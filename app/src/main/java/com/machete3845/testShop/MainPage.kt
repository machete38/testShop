package com.machete3845.testShop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.machete3845.data.InternalDB
import com.machete3845.data.UserData
import com.machete3845.testShop.fragments.CatalogueFragment
import com.machete3845.testShop.fragments.PlaceholderFragment
import com.machete3845.testShop.fragments.ProfileFragment

class MainPage : AppCompatActivity() {

    lateinit var bottonNav: BottomNavigationView
    lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        bottonNav = findViewById(R.id.bottom_nv)
        tv = findViewById(R.id.tv_title)

        bottonNav.setOnItemSelectedListener(listener)

        initializeFragment(Fragment(), getString(R.string.main))

    }

    fun initializeFragment(fragment: Fragment, text: String = "") {
        if (text != "")
        {
            tv.text = text
            val arguments = Bundle()
            arguments.putString("text",text)
            fragment.arguments = arguments
        }
        val fragmentTransaction: FragmentTransaction =
            supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_main, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private val listener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        menuItem.isChecked = true
        when (menuItem.itemId) {
            R.id.menu_home -> {
                initializeFragment(PlaceholderFragment(),getString(R.string.main))
            }
            R.id.menu_catalogue -> {
                initializeFragment(CatalogueFragment(),getString(R.string.catalogue))
            }
            R.id.menu_cart -> {
                initializeFragment(PlaceholderFragment(),getString(R.string.cart))
            }
            R.id.menu_promo -> {
                initializeFragment(PlaceholderFragment(),getString(R.string.promos))
            }
            R.id.menu_account -> {
                initializeFragment(ProfileFragment(),getString(R.string.profile))
            }
        }
        false
    }
}