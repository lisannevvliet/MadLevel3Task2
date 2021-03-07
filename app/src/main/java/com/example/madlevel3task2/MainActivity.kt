package com.example.madlevel3task2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.madlevel3task2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // Navigate to the AddPortalFragment when the floating action button (FAB) is clicked.
        binding.fab.setOnClickListener { view -> navController.navigate(R.id.action_portalsFragment_to_addPortalFragment) }

        fabToggler()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Hide the floating action button (FAB) in the AddPortalFragment.
    private fun fabToggler() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id in arrayOf(R.id.addPortalFragment)) {
                binding.fab.hide()
            } else {
                binding.fab.show()
            }
        }
    }
}