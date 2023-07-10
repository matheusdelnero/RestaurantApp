package com.example.restaurantapp.ui.Cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.restaurantapp.databinding.NewMealItemBinding
import com.example.restaurantapp.model.Meal

class CartAdapter: RecyclerView.Adapter<CartAdapter.CartAdapterViewHolder>() {


    inner class CartAdapterViewHolder(val binding: NewMealItemBinding) : RecyclerView.ViewHolder(binding.root)



    private val diffUtil = object : DiffUtil.ItemCallback<Meal>(){
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return  oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapterViewHolder {
        return  CartAdapterViewHolder(
            NewMealItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CartAdapterViewHolder, position: Int) {
        val meal = differ.currentList[position]
        Glide.with(holder.itemView).load(meal.strMealThumb).into(holder.binding.imageView)
        holder.binding.textView.text = meal.strMeal

        // Funcionalidade Quantidade produtos

        val valor = holder.binding.textView2.text.toString()
        var numero : Int = valor.toInt()

        // Botão Adicionar

        holder.binding.imageView4.setOnClickListener {

            numero += 1
            holder.binding.textView2.text = numero.toString()

        }

        //Botão Diminuir

        holder.binding.imageView3.setOnClickListener {

            numero -= 1
            holder.binding.textView2.text = numero.toString()

        }
    }


}