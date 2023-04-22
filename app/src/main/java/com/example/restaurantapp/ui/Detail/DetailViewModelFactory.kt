package com.example.restaurantapp.ui.Detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restaurantapp.db.MealDataBase

class DetailViewModelFactory(
    private val mealDataBase: MealDataBase
) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(mealDataBase) as T
    }

}