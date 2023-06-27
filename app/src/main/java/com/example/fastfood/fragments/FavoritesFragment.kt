package com.example.fastfood.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.fastfood.R
import com.example.fastfood.adapters.FavoritesAdapter
import com.example.fastfood.databinding.FragmentFavoritesBinding
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.viewModel.viewsm.FavoritesViewModel
import com.example.fastfood.viewModel.factories.FavoritesViewModelFactory
import com.google.android.material.snackbar.Snackbar


class FavoritesFragment : Fragment(), FavoritesAdapter.ItemsInteraction {

    lateinit var binding:FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels{ FavoritesViewModelFactory(requireContext()) }

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

        swapEffectForDelete(favoritesAdapter)

        return binding.root
    }

    private fun swapEffectForDelete(adapter: FavoritesAdapter){
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                val list:MutableList<MyRecipe> = adapter.getList() as MutableList<MyRecipe>
                val item = list[pos]
                var delete=true
                list.removeAt(pos)
                adapter.notifyItemRemoved(pos)
                //adapter.setList(list)
                val snackbar = Snackbar.make(requireView(),"Recipe has been deleted",Snackbar.LENGTH_LONG).setAction(
                    "Undo"
                ) {
                    delete = false
                    list.add(pos, item)
                    adapter.notifyItemInserted(pos)
                    //adapter.setList(list)
                }
                snackbar.addCallback(object : Snackbar.Callback() {
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)
                        if (delete) {
                            viewModel.deleteFavRecipe(item)
                        }
                    }
                })
                snackbar.show()
            }
        }

        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.recyclerFavs)
    }

    override fun onClickOnFavRecipeItem(item: MyRecipe) {
        val action = FavoritesFragmentDirections.actionFavoritesFragmentToRecipeFragment(item,null)
        findNavController().navigate(action)
    }

}