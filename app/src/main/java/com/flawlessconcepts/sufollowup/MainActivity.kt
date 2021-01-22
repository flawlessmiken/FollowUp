package com.flawlessconcepts.sufollowup

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.flawlessconcepts.sufollowup.database.FollowUpDatabase
import com.flawlessconcepts.sufollowup.database.FollowUpDatabaseDao
import com.flawlessconcepts.sufollowup.database.FollowUpItem
import com.flawlessconcepts.sufollowup.databinding.ActivityMainBinding
import com.flawlessconcepts.sufollowup.utils.createLessonsFromJson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )

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
        /// Populate Database with Lessons if Database is Empty
        pupulateDataBase()

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


    private fun pupulateDataBase() {

        var isE: String = "bbbb"

        val dataSource = FollowUpDatabase.getInstance(application).followUpDatabaseDao
        GlobalScope.launch {

            if (isDatabaseEmpty(dataSource)) {
                runOnUiThread(Runnable {
                    Toast.makeText(applicationContext, "Empty", Toast.LENGTH_SHORT).show()
                })
                val lessons = createLessonsFromJson(applicationContext)
                writeToDatabase(lessons, dataSource)
//                lessons.forEachIndexed { index, lesson ->
//
//
//                }

            }


        }
    }

    private suspend fun isDatabaseEmpty(datasource: FollowUpDatabaseDao): Boolean {
        return datasource.getAnyLesson() == null
    }

    private suspend fun writeToDatabase(lesson: List<FollowUpItem>, datasource: FollowUpDatabaseDao) {
        val addLessons = datasource.addLessons(lesson)
        runOnUiThread(Runnable {
            if (addLessons != null) {
                Toast.makeText(applicationContext, "Writing to database" +addLessons.size, Toast.LENGTH_SHORT).show()
            }
        })
    }
}