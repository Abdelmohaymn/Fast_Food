package com.example.fastfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.fastfood.fragments.RecipeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(),NavController.OnDestinationChangedListener {

    private lateinit var bottomNav:BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var navHostFragment:NavHostFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.btm_navigation)
        navController = Navigation.findNavController(this,R.id.host_fragment)
        NavigationUI.setupWithNavController(bottomNav,navController)
        navController.addOnDestinationChangedListener(this)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.host_fragment) as NavHostFragment
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        when(destination.id){
            R.id.homeFragment,R.id.favoritesFragment,R.id.categoriesFragment
                 -> bottomNav.visibility = View.VISIBLE
            else -> bottomNav.visibility = View.GONE
        }
    }

    override fun onBackPressed() {
        /*if (navController.currentDestination?.id == R.id.recipeFragment) {
            val sourceFragmentId = (navHostFragment.childFragmentManager
                .primaryNavigationFragment as? RecipeFragment)?.args?.sourceId

            if (sourceFragmentId != null) {
                navController.navigateUp()
                return
            }
        }*/
        if(navController.currentDestination?.id==R.id.homeFragment)super.onBackPressed()
        navController.navigateUp()
    }

}