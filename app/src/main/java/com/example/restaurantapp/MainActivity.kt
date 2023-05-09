package com.example.restaurantapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.restaurantapp.db.MealDataBase
import com.example.restaurantapp.db.UserDataBase
import com.example.restaurantapp.ui.Home.HomeViewModel
import com.example.restaurantapp.ui.Home.HomeViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val viewModel: HomeViewModel by lazy {
        val mealDataBase = MealDataBase.getInstance(this)
        val homeViewModelProvider = HomeViewModelFactory(mealDataBase)
        ViewModelProvider(this,homeViewModelProvider)[HomeViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.btm_nav)
        val navController = Navigation.findNavController(this,R.id.host_fragment)

        NavigationUI.setupWithNavController(bottomNavigation,navController)
    }
}