package com.artemla.pokedex.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.artemla.pokedex.R
import com.artemla.pokedex.databinding.ActivityMainBinding
import com.artemla.pokedex.domain.utils.PreferencesUtils
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        val preferences = PreferencesUtils.getInstance(context = applicationContext)
        viewModel.fetchDataFromApi()
        startIntro(preferences.isFirstLaunch())
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavbar()
    }

    private fun setNavbar() {
        val navView: BottomNavigationView = binding.navView

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_region,
                R.id.navigation_favourite,
                R.id.navigation_profile
            )
        )
        binding.mainToolbar.setupWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.itemIconTintList = null

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_home || destination.id == R.id.navigation_pokemon) {
                binding.mainActionbar.visibility = View.GONE
            } else {
                binding.mainActionbar.visibility = View.VISIBLE
            }
        }


    }

    private fun startIntro(firstLaunch: Boolean) {
        if (firstLaunch) {
            intent = Intent(applicationContext, PokedexAppIntro::class.java)
            startActivity(intent)
        }
    }
}