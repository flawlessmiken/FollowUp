package com.flawlessconcepts.sufollowup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.flawlessconcepts.sufollowup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = binding.drawerLayout
//
//        val navController = this.findNavController(R.id.myNavHostFragment)
//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
//        NavigationUI.setupWithNavController(binding.navView, navController)



        binding.includeAppBar.includeContent.bottomNav.itemIconSize
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment),
            binding.drawerLayout
        )
        val navController = this.findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, naveDestination, _ ->
            when (naveDestination.id) {
                R.id.homeFragment, R.id.lessonListFragment, R.id.favouriteFragment2, R.id.userFragment
                ->
                    binding.includeAppBar.includeContent.bottomNav.visibility = View.VISIBLE
                else -> binding.includeAppBar.includeContent.bottomNav.visibility = View.GONE
            }
        }

        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupWithNavController(
            binding.includeAppBar.includeContent.bottomNav,
            navController
        )

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item!!,
            findNavController(R.id.nav_host_fragment)
        )
                || super.onOptionsItemSelected(item)
    }
}