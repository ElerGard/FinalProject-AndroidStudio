package com.example.final_project

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.final_project.ui.FragmentController
import com.example.final_project.ui.battleship.BattleshipFragment
import com.example.final_project.ui.login.LoginFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), FragmentController
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_login, R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.visibility = View.GONE
    }

    override fun changeFragment(id: Int) {
        if (id == 1) {
            val navView: BottomNavigationView = findViewById(R.id.nav_view)
            val img: ImageView = findViewById(R.id.imageView)
            navView.visibility = View.VISIBLE
            img.visibility = View.VISIBLE
            // Don't work. I don't know why

//            val fragment = BattleshipFragment()
//            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
//            ft.replace(R.id.navigation_dashboard, fragment)
//            ft.commit()
        }
    }
}

//class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {
//    //Rest of the code
//    fun changeFragment(id: Int) {
//        if (id == 1) {
//            android.R.attr.fragment = first()
//            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
//            ft.replace(android.R.id.mainFrame, android.R.attr.fragment)
//            ft.commit()
//        } else if (id == 2) {
//            android.R.attr.fragment = second()
//            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
//            ft.replace(android.R.id.mainFrame, android.R.attr.fragment)
//            ft.commit()
//        }
//    }
//}