package com.example.fastfood.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fastfood.R
import com.example.fastfood.adapters.CategoryAdapter
import com.example.fastfood.adapters.PopularAdapter
import com.example.fastfood.databinding.FragmentHomeBinding
import com.example.fastfood.model.recipesList.Recipe
import com.example.fastfood.util.mealTypes
import com.example.fastfood.viewModel.HomeViewModel



class HomeFragment : Fragment(), PopularAdapter.ItemsInteraction, CategoryAdapter.ItemsInteraction {

    private lateinit var binding:FragmentHomeBinding
    private val viewModel:HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        binding.lifecycleOwner=this
        binding.fragment=this
        binding.viewModel=viewModel

        viewModel.getRandomRecipe()
        val popularAdapter = PopularAdapter(mutableListOf(),this)
        binding.recyclerPopularItems.adapter=popularAdapter
        val categoryAdapter = CategoryAdapter(mealTypes,this)
        binding.recyclerCategories.adapter=categoryAdapter

        return binding.root
    }

    fun moveToRecipeFragment(recipe: Recipe){
        val action = HomeFragmentDirections.actionHomeFragmentToRecipeFragment(recipe)
        findNavController().navigate(action)
    }

    override fun onClickOnPopularItem(item: Recipe) {
        moveToRecipeFragment(item)
    }

    override fun onClickOnCategoryItem(item: String) {
        val action = HomeFragmentDirections.actionHomeFragmentToCategoryRecipeFragment(item)
        findNavController().navigate(action)
    }

}