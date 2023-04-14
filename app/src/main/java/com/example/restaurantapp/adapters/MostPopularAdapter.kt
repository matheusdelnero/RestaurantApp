package com.example.restaurantapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurantapp.databinding.PopularItemsBinding
import com.example.restaurantapp.model.CategoryMeals

class MostPopularAdapter(): RecyclerView.Adapter<MostPopularAdapter.PopularMealViewHolder>() {


    lateinit var onItemClicked:((CategoryMeals) -> Unit)
    private var mealsList = ArrayList<CategoryMeals>()

    fun setMeals(mealsList:ArrayList<CategoryMeals>){
        this.mealsList = mealsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMealViewHolder {
        return PopularMealViewHolder(PopularItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: PopularMealViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imgPopularMealItem)

        holder.itemView.setOnClickListener {
            onItemClicked.invoke(mealsList[position])
        }
    }

    class PopularMealViewHolder( val binding: PopularItemsBinding): RecyclerView.ViewHolder(binding.root)


}