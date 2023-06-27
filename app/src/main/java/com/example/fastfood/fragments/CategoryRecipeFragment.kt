package com.example.fastfood.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fastfood.R
import com.example.fastfood.adapters.CategoryRecipeAdapter
import com.example.fastfood.databinding.FragmentCategoryRecipeBinding
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.viewModel.factories.CategoryRecipeFactory
import com.example.fastfood.viewModel.viewsm.CategoryRecipeViewModel


class CategoryRecipeFragment : Fragment(), CategoryRecipeAdapter.ItemsInteraction {

    private lateinit var binding:FragmentCategoryRecipeBinding
    private val args:CategoryRecipeFragmentArgs by navArgs()
    private val viewModel: CategoryRecipeViewModel by viewModels{ CategoryRecipeFactory(args.categoryType) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_category_recipe,container,false)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel
        binding.categoryType = args.categoryType

        val adapter = CategoryRecipeAdapter(emptyList(),this)
        binding.recyclerCategoryRecipes.adapter=adapter

        return binding.root
    }

    override fun onClickOnCategoryRecipeItem(item: MyRecipe) {
        val action = CategoryRecipeFragmentDirections.actionCategoryRecipeFragmentToRecipeFragment(item)
        findNavController().navigate(action)
    }

}