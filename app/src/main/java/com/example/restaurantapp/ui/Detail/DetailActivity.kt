package com.example.restaurantapp.ui.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.ActivityDetailBinding
import com.example.restaurantapp.model.Meal
import com.example.restaurantapp.ui.Home.HomeFragment

class DetailActivity : AppCompatActivity() {

    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private val viewModel: DetailViewModel by viewModels()


    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getMealInformationFromIntent()
        setInformationInViews()
        loadingCase()
        viewModel.getMealDetail(mealId)
        observeDetailsLiveData()

    }

    private fun observeDetailsLiveData() {
        viewModel.observeMealDetailLiveData().observe(this,object: Observer<Meal>{
            override fun onChanged(t: Meal?) {
                onResponseCase()
                val meal = t
                binding.tvInstructionSteps.text = meal!!.strInstructions
            }
        })
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)

        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.buttonFavoritesUp.visibility = View.INVISIBLE
        binding.tvInstructions.visibility = View.INVISIBLE
        binding.btnAddtocart.visibility = View.INVISIBLE
    }

    private fun onResponseCase(){
        binding.progressBar.visibility = View.INVISIBLE
        binding.buttonFavoritesUp.visibility = View.VISIBLE
        binding.tvInstructions.visibility = View.VISIBLE
        binding.btnAddtocart.visibility = View.VISIBLE

    }
}