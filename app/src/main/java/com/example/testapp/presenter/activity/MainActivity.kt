package com.example.testapp.presenter.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testapp.R
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.presenter.fragment.FoodAdapter
import com.example.testapp.presenter.viewmodel.FoodViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var  navHostFragment:NavHostFragment
    private lateinit var navController: NavController
    private var firstBackPress = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottom()

    }


    private fun initBottom() {
        val bottom = binding.bottomNavView
        val iconColor = ContextCompat.getColorStateList(this, R.color.bottom_icon_colors)
        bottom.itemIconTintList = iconColor

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fr_container) as NavHostFragment
        navController = navHostFragment.navController

        bottom.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu -> {
                    navController.navigate(R.id.menuFragment)
                    true
                }
                R.id.profile -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }
                R.id.basket -> {
                    navController.navigate(R.id.basketFragment)
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.menuFragment) {
            if (firstBackPress) {
                firstBackPress = false
            } else {
                super.onBackPressed()
            }
        } else {

            navController.navigate(R.id.menuFragment)
        }
    }

}