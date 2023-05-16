package com.example.fastfood.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fastfood.R
import com.example.fastfood.State
import com.example.fastfood.adapters.*
import com.example.fastfood.databinding.FragmentRecipeBinding
import com.example.fastfood.model.recipesList.AnalyzedInstruction
import com.example.fastfood.model.recipesList.Equipment
import com.example.fastfood.model.recipesList.ExtendedIngredient
import com.example.fastfood.model.recipesList.Recipe
import com.example.fastfood.model.similarRecipes.SimilarRecipesItem
import com.example.fastfood.viewModel.RecipeViewModel


class RecipeFragment : Fragment(), SimilarRecipeAdapter.ItemsInteraction {

    lateinit var binding: FragmentRecipeBinding
    private val args:RecipeFragmentArgs by navArgs()
    private val viewModel:RecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_recipe,container,false)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel

        if(args.recipe!=null){
            initData(args.recipe!!)
        }else if(args.recipeId!=-1){
            viewModel.getRecipeInfo(args.recipeId)
            viewModel.recipeInfo.observe(viewLifecycleOwner){state->
                if (state is State.Success){
                    initData(state.toData()!!)
                }
            }
        }

        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

    private fun initData(recipe: Recipe){
        viewModel.getSimilarRecipes(recipe.id!!)
        binding.recipe = recipe
        val ingredientAdapter = IngredientAdapter((recipe.extendedIngredients?:emptyList()) as List<ExtendedIngredient>)
        binding.recyclerIngredients.adapter=ingredientAdapter
        val pairOfData = getEquipments(recipe.analyzedInstructions as List<AnalyzedInstruction>?)
        val equipmentAdapter = EquipmentAdapter(pairOfData.first)
        binding.recyclerEquipments.adapter = equipmentAdapter
        val instructionsAdapter = InstructionsAdapter(pairOfData.second)
        binding.recyclerInstructions.adapter = instructionsAdapter
        val similarAdapter = SimilarRecipeAdapter(emptyList(),this)
        binding.recyclerSimilar.adapter = similarAdapter
    }

    private fun getEquipments(list:List<AnalyzedInstruction>?):Pair<List<Equipment>,List<String>>{
        val equipments = mutableSetOf<Equipment>()
        val steps = mutableListOf<String>()

        for(it in list?: emptyList()){
            for(jt in it.steps?: emptyList()){
                for(tt in jt?.equipment?: emptyList()){
                    equipments.add(tt!!)
                }
                steps.add(jt?.step!!)
            }
        }
        return Pair(equipments.toList(),steps)
    }

    override fun onClickOnSimilarRecipeItem(item: SimilarRecipesItem) {
        val action = RecipeFragmentDirections.actionRecipeFragmentSelf(null,item.id!!)
        findNavController().navigate(action)
    }

}