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
import com.example.fastfood.adapters.FavoritesAdapter
import com.example.fastfood.databinding.FragmentFavoritesBinding
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.viewModel.FavoritesViewModel
import com.example.fastfood.viewModel.FavoritesViewModelFactory


class FavoritesFragment : Fragment(), FavoritesAdapter.ItemsInteraction {

    lateinit var binding:FragmentFavoritesBinding
    private val viewModel:FavoritesViewModel by viewModels{FavoritesViewModelFactory(requireContext())}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_favorites,container,false)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel

        viewModel.getAllFavs()
        val favoritesAdapter = FavoritesAdapter(mutableListOf(),this)
        binding.recyclerFavs.adapter=favoritesAdapter

        return binding.root
    }

    override fun onClickOnFavRecipeItem(item: MyRecipe) {
        val action = FavoritesFragmentDirections.actionFavoritesFragmentToRecipeFragment(item,null)
        findNavController().navigate(action)
    }

}