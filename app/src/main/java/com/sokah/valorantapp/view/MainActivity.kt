package com.sokah.valorantapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.sokah.valorantapp.R
import com.sokah.valorantapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)
        
        navController = findNavController(R.id.navHost)
        binding.bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.agentDetailsFragment) {
                binding.bottomNavigationView.visibility = View.GONE
            } else {
                binding.bottomNavigationView.visibility = View.VISIBLE
            }
            val appBarConfiguration = AppBarConfiguration
                .Builder(
                    R.id.agentsFragment,
                    R.id.weaponListFragment,
                    )
                .build()
            NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration)


        }

    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp()
    }
}