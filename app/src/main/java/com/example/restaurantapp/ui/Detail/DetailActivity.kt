package com.example.restaurantapp.ui.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.ActivityDetailBinding
import com.example.restaurantapp.db.MealDataBase
import com.example.restaurantapp.model.Meal
import com.example.restaurantapp.ui.Home.HomeFragment

class DetailActivity : AppCompatActivity() {

    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var viewModel: DetailViewModel


    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

         val mealDataBase by lazy {
            Room.databaseBuilder(
                applicationContext,
                MealDataBase::class.java,
                "meal.db"
            ).build()
        }

        val db = MealDataBase.getInstance(this)
        val factory = DetailViewModelFactory(mealDataBase)
        viewModel = ViewModelProvider(this,factory)[DetailViewModel::class.java]


        getMealInformationFromIntent()
        setInformationInViews()
        loadingCase()
        viewModel.getMealDetail(mealId)
        observeDetailsLiveData()
        onFavoriteClick()


    }

    private fun onFavoriteClick() {
        binding.btnAddtocart.setOnClickListener {
            mealToSave?.let {
                viewModel.insertMeal(it)
                Toast.makeText(this,"Produto Adicionado ao Carrinho!",Toast.LENGTH_SHORT).show()
            }
        }
    }


    private var mealToSave:Meal?=null
    private fun observeDetailsLiveData() {
        viewModel.observeMealDetailLiveData().observe(this,object: Observer<Meal>{
            override fun onChanged(t: Meal?) {
                onResponseCase()
                val meal = t
                mealToSave = meal
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