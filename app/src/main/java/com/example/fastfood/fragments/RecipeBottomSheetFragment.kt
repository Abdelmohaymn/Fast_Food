package com.example.fastfood.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fastfood.R
import com.example.fastfood.databinding.FragmentRecipeBottomSheetBinding
import com.example.fastfood.domain.models.MyRecipe
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class RecipeBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding:FragmentRecipeBottomSheetBinding
    private var recipe:MyRecipe?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipe = it.getSerializable("recipe") as MyRecipe?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_recipe_bottom_sheet,container,false)
        binding.lifecycleOwner=this

        binding.item = recipe

        binding.bottomSheetLayout.setOnClickListener{
            val action = HomeFragmentDirections.actionHomeFragmentToRecipeFragment(recipe)
            findNavController().navigate(action)
        }

        return binding.root
    }

    companion object{
        @JvmStatic
        fun newInstance(recipe:MyRecipe) = RecipeBottomSheetFragment().apply {
            arguments = Bundle().apply {
                putSerializable("recipe",recipe)
            }
        }
    }


}