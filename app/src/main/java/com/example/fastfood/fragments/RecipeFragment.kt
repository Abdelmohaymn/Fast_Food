package com.example.fastfood.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fastfood.R
import com.example.fastfood.State
import com.example.fastfood.adapters.*
import com.example.fastfood.databinding.FragmentRecipeBinding
import com.example.fastfood.domain.models.MyMiniRecipe
import com.example.fastfood.domain.models.MyRecipe
import com.example.fastfood.model.recipesList.AnalyzedInstruction
import com.example.fastfood.model.recipesList.Equipment
import com.example.fastfood.model.recipesList.ExtendedIngredient
import com.example.fastfood.model.similarRecipes.SimilarRecipesItem
import com.example.fastfood.viewModel.viewsm.RecipeViewModel
import com.example.fastfood.viewModel.factories.RecipeViewModelFactory


class RecipeFragment : Fragment(), SimilarRecipeAdapter.ItemsInteraction {

    private lateinit var binding: FragmentRecipeBinding
    private val args:RecipeFragmentArgs by navArgs()
    private val viewModel: RecipeViewModel by viewModels{ RecipeViewModelFactory(requireContext()) }
    private var currentRecipe:MyRecipe? = null
    val isSaved = MutableLiveData(false)
    val isChecked = MutableLiveData(false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_recipe,container,false)
        binding.lifecycleOwner=this
        binding.viewModel=viewModel
        binding.fragment=this

        ////////////////////////////

        init()

        binding.floatingFavButton.setOnClickListener {
            floatingButton()
        }

        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }

    private fun init(){
        if(args.recipe!=null){
            currentRecipe = args.recipe!!
            if(viewModel.getRecipeById(currentRecipe?.id!!)!=null)isSaved.postValue(true)
            initData(currentRecipe!!)
            //Log.d("imageCat",currentRecipe!!.image!!)
        }else if(args.miniRecipe!=null){
            viewModel.getRecipeInfo(args.miniRecipe!!.id!!)
            binding.miniRecipe = args.miniRecipe
            currentRecipe = viewModel.getRecipeById(args.miniRecipe?.id!!)
            if(currentRecipe!=null){
                isSaved.postValue(true)
                initData(currentRecipe!!)
            }else{
                viewModel.recipeInfo.observe(viewLifecycleOwner){state->
                    if (state is State.Success){
                        currentRecipe = state.toData()!!
                        initData(currentRecipe!!)
                    }
                }
            }
        }
    }

    private fun initData(recipe: MyRecipe){
        viewModel.getSimilarRecipes(recipe.id!!)
        binding.recipe = recipe
        val ingredientAdapter = IngredientAdapter((recipe.extendedIngredients?:emptyList())as List<ExtendedIngredient>)
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

    private fun floatingButton(){
        if(currentRecipe!=null){
            if(!isSaved.value!!){
                viewModel.insertFavRecipe(currentRecipe!!)
            }else{
                viewModel.deleteFavRecipe(currentRecipe!!)
            }
            isSaved.postValue(!isSaved.value!!)
        }
    }

    override fun onClickOnSimilarRecipeItem(item: MyMiniRecipe) {
        val action = RecipeFragmentDirections.actionRecipeFragmentSelf(null,item)
        findNavController().navigate(action)
    }

}