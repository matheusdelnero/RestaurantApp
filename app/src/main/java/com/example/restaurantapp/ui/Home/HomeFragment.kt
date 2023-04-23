package com.example.restaurantapp.ui.Home


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.restaurantapp.MainActivity
import com.example.restaurantapp.adapters.CategoriesAdapter
import com.example.restaurantapp.adapters.MostPopularAdapter
import com.example.restaurantapp.databinding.FragmentHomeBinding
import com.example.restaurantapp.model.MealsByCategory
import com.example.restaurantapp.model.Meal
import com.example.restaurantapp.ui.CategoryMealsActivity
import com.example.restaurantapp.ui.Detail.DetailActivity


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    //private val viewModel: HomeViewModel by viewModels()
    private lateinit var randomMeal: Meal
    private lateinit var popularItensAdapter: MostPopularAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    companion object {
        const val MEAL_ID = "com.example.restaurantapp.ui.idMeal"
        const val MEAL_NAME = "com.example.restaurantapp.ui.nameMeal"
        const val MEAL_THUMB = "com.example.restaurantapp.ui.thumbMeal"
        const val CATEGORY_NAME = "com.example.restaurantapp.ui.Home.categoryName"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        popularItensAdapter = MostPopularAdapter()




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preparePopularItensRecycler()

        viewModel.getRandomMeal()
        observeRandomMeal()
        onRandomMealClick()

        viewModel.getPopularItens()
        observePopularItensLiveData()
        onPopularItemClicked()

        prepareCategoriesRecycler()
        viewModel.getCategories()
        observeCategoriesLiveData()
        onCategoryClick()





    }

    private fun onCategoryClick() {
        categoriesAdapter.onItemClick = {category ->
            val intent = Intent(activity,CategoryMealsActivity::class.java)
            intent.putExtra(CATEGORY_NAME,category.strCategory)
            startActivity(intent)

        }
    }

    private fun prepareCategoriesRecycler() {
        categoriesAdapter = CategoriesAdapter()
        binding.recViewCategories.apply {
            layoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter = categoriesAdapter
        }
    }

    private fun observeCategoriesLiveData() {
        viewModel.observeCategoriesLivedata().observe(viewLifecycleOwner, Observer { categories ->
                categoriesAdapter.setCategoriesList(categories)

        })
    }

    private fun onPopularItemClicked() {
        popularItensAdapter.onItemClicked = {meal ->
            val intent = Intent(activity,DetailActivity::class.java)
            intent.putExtra(MEAL_ID,meal.idMeal)
            intent.putExtra(MEAL_NAME,meal.strMeal)
            intent.putExtra(MEAL_THUMB,meal.strMealThumb)
            startActivity(intent)

        }
    }

    private fun preparePopularItensRecycler() {
        binding.recyclerMealsPopular.apply {
            layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            adapter = popularItensAdapter
        }
    }

    private fun observePopularItensLiveData() {
        viewModel.observePopularItensLiveData().observe(viewLifecycleOwner
        ) { mealList ->
            popularItensAdapter.setMeals(mealsList = mealList as ArrayList<MealsByCategory>)

        }
    }

    private fun onRandomMealClick() {
        binding.randomMealCard.setOnClickListener {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_NAME,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observeRandomMeal() {
        viewModel.observeRandomMealLiveData().observe(viewLifecycleOwner
        ) { meal ->
            Glide.with(this@HomeFragment)
                .load(meal.strMealThumb)
                .into(binding.imgRandomMeal)

            this.randomMeal = meal
        }
    }


}