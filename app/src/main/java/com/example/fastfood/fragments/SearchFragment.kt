package com.example.fastfood.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.example.fastfood.R
import com.example.fastfood.adapters.AutoCompleteAdapter
import com.example.fastfood.adapters.SearchRecipesAdapter
import com.example.fastfood.databinding.FragmentSearchBinding
import com.example.fastfood.domain.models.MyMiniRecipe
import com.example.fastfood.model.autoComplete.AutoCompleteResponseItem
import com.example.fastfood.viewModel.viewsm.SearchViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class SearchFragment : Fragment(), AutoCompleteAdapter.ItemsInteraction,
    SearchRecipesAdapter.ItemsInteraction {

    private lateinit var binding:FragmentSearchBinding
    private val viewModel:SearchViewModel by viewModels()
    private lateinit var searchDisposable: Disposable
    val status = MutableLiveData(true)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search,container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.fragment = this

        val adapter = AutoCompleteAdapter(emptyList(),this)
        binding.recyclerSearch.adapter = adapter
        val adapter2 = SearchRecipesAdapter(emptyList(),this)
        binding.recyclerSearchRecipes.adapter = adapter2

        handleSearch()

        return binding.root
    }

    private fun handleSearch(){
        val observable = Observable.create<String> { emitter ->
            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.toString()?.let { emitter.onNext(it)
                        if(!it.isNullOrBlank())status.postValue(true) }
                }
                override fun afterTextChanged(s: Editable?) {
                    binding.recyclerSearch.visibility=View.VISIBLE
                    binding.recyclerSearchRecipes.visibility=View.GONE
                }
            }

            binding.etSearch.addTextChangedListener(textWatcher)
            emitter.setCancellable {
                binding.etSearch.removeTextChangedListener(textWatcher)
            }
            binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // User clicked the "OK" button on the keyboard
                    status.postValue(true)
                    viewModel.getRecipesFromSearch(binding.etSearch.text.toString())
                    return@setOnEditorActionListener true
                }
                false
            }
        }
        searchDisposable = observable
            .debounce(300, TimeUnit.MILLISECONDS) // Add a delay to wait for user input
            .subscribeOn(Schedulers.io()) // Perform the search operation on a background thread
            .observeOn(AndroidSchedulers.mainThread()) // Observe the results on the main thread
            .subscribe { query ->
                viewModel.getSeggestions(query)
            }

    }

    override fun onClickOnSearchResItem(item: MyMiniRecipe) {
        val action = SearchFragmentDirections.actionSearchFragmentToRecipeFragment(null,item)
        findNavController().navigate(action)
    }

    override fun onClickOnAutoComItem(query: String) {
        moveEditTextCursor(query)
        viewModel.getRecipesFromSearch(query)
    }

    override fun onClickOnTopLeft(query: String) {
        moveEditTextCursor(query)
    }

    override fun onDestroy() {
        super.onDestroy()
        searchDisposable.dispose()
    }

    private fun moveEditTextCursor(query:String){
        binding.etSearch.setText(query)
        binding.etSearch.setSelection(binding.etSearch.text.length)
    }
}

