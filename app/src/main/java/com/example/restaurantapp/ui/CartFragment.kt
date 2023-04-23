package com.example.restaurantapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.restaurantapp.MainActivity
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentCartBinding
import com.example.restaurantapp.ui.Home.HomeViewModel


class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var cartAdapter: CartAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        observeCart()
    }

    private fun prepareRecyclerView() {
        cartAdapter = CartAdapter()
        binding.rvCart.apply {
            layoutManager = GridLayoutManager(context,1,GridLayoutManager.VERTICAL,false)
            adapter = cartAdapter
        }
    }

    private fun observeCart() {
        viewModel.observeCartLiveData().observe(viewLifecycleOwner, Observer { mealsList ->
            mealsList.forEach {
                cartAdapter.differ.submitList(mealsList)
            }
        })
    }

}