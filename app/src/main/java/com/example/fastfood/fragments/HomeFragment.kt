package com.example.fastfood.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.fastfood.R
import com.example.fastfood.adapters.CategoryAdapter
import com.example.fastfood.adapters.PopularAdapter
import com.example.fastfood.databinding.FragmentHomeBinding
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.util.mealTypes
import com.example.fastfood.viewModel.viewsm.HomeViewModel
import com.example.fastfood.viewModel.factories.HomeViewModelFactory


class HomeFragment : Fragment(), PopularAdapter.ItemsInteraction, CategoryAdapter.ItemsInteraction {

    private lateinit var binding:FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels{ HomeViewModelFactory(requireContext()) }
    private var recipeBottomSheetFragment :RecipeBottomSheetFragment?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        binding.lifecycleOwner=this
        binding.fragment=this
        binding.viewModel=viewModel

        val popularAdapter = PopularAdapter(mutableListOf(),this)
        binding.recyclerPopularItems.adapter=popularAdapter

        viewModel.getRandomRecipe()

        val categoryAdapter = CategoryAdapter(mealTypes,this)
        binding.recyclerCategories.adapter=categoryAdapter

        binding.imgRandomMeal.setOnLongClickListener {
            viewModel.randomRecipee.value?.toData()?.let { it1 -> clickOnBottomSheet(it1) }
            true
        }

        binding.imgSearch.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    fun moveToRecipeFragment(recipe: MyRecipe){
        val action = HomeFragmentDirections.actionHomeFragmentToRecipeFragment(recipe)
        findNavController().navigate(action)
    }

    override fun onClickOnPopularItem(item: MyRecipe) {
        moveToRecipeFragment(item)
    }

    override fun onLongClickOnPopularItem(item: MyRecipe):Boolean {
        clickOnBottomSheet(item)
        return true
    }

    override fun onClickOnCategoryItem(item: Pair<String,Int>) {
        val action = HomeFragmentDirections.actionHomeFragmentToCategoryRecipeFragment(item.first)
        findNavController().navigate(action)
    }

    private fun clickOnBottomSheet(item: MyRecipe){
        recipeBottomSheetFragment = RecipeBottomSheetFragment.newInstance(item)
        recipeBottomSheetFragment!!.show(childFragmentManager,"Recipe Info")
    }

    override fun onPause() {
        super.onPause()
        if(recipeBottomSheetFragment!=null){
            if(recipeBottomSheetFragment!!.isVisible){
                recipeBottomSheetFragment!!.dismiss()
            }
        }
    }

}