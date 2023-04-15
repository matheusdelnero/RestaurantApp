package com.example.restaurantapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.restaurantapp.R
import com.example.restaurantapp.adapters.CategoryMealsAdapter
import com.example.restaurantapp.databinding.ActivityCategoryMealsBinding
import com.example.restaurantapp.model.MealsByCategory
import com.example.restaurantapp.ui.Home.HomeFragment
import com.example.restaurantapp.ui.Home.HomeFragment.Companion.CATEGORY_NAME
import com.example.restaurantapp.ui.Home.HomeViewModel

class CategoryMealsActivity : AppCompatActivity() {
    lateinit var binding : ActivityCategoryMealsBinding
    private val viewModel: CategoryMealsViewModel by viewModels()
    lateinit var categoryMealsAdapter: CategoryMealsAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)




        prepareRecyclerView()

        viewModel.getMealsByCategory(intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!)

        viewModel.observeMealsLiveData().observe(this, Observer { mealsList ->
            categoryMealsAdapter.setMealsList(mealsList)
            binding.tvCategoryCount.text = mealsList.size.toString()

        })



    }

    private fun prepareRecyclerView() {
        categoryMealsAdapter = CategoryMealsAdapter()
        binding.rvMeals.apply {
            layoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter = categoryMealsAdapter
        }
    }


}