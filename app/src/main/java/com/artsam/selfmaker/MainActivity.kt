package com.artsam.selfmaker

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.artsam.selfmaker.data.source.remote.AuthViewModel
import com.artsam.selfmaker.databinding.ActivityMainBinding
import com.artsam.selfmaker.utils.log

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val authViewModel by viewModels<AuthViewModel>()
    private lateinit var drawerNavViewHeader: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SelfMaker_NoActionBar)
        binding = ActivityMainBinding.inflate(layoutInflater) // start using ViewBinding
        setContentView(binding.root)

        setUI()
    }

    private fun setUI() {
        setSupportActionBar(binding.appBarMain.toolbar)

        //----------------------------------------------------------------------------------------------
        // one navController for both drawer and bottom navigation
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_map,
                R.id.navigation_veda,
                R.id.navigation_focus,
                R.id.navigation_sadhana,
                R.id.navigation_statistics,
                R.id.navigation_sign_in
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.drawerNavView.setupWithNavController(navController)
        binding.appBarMain.contentMain.bottomNavView.setupWithNavController(navController)
        //----------------------------------------------------------------------------------------------

        drawerNavViewHeader = binding.drawerNavView.getHeaderView(0)
        drawerNavViewHeader.setOnClickListener(this)

        binding.appBarMain.fab.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        if (v != null) {
            log { "onClick(): " + v.id }
            when (v.id) {
                R.id.fab -> Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                R.id.ll_nav_header_root -> Snackbar.make(v, "Wow", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
        }
    }

    override fun onResume() {
        log { "onResume() " + authViewModel.javaClass.getName() + "@" + Integer.toHexString(authViewModel.hashCode()) }
        observeAuthenticationState()
        super.onResume()
    }

    private fun observeAuthenticationState() {
        authViewModel.authenticationState.observe(this) { authenticationState ->
            setDrawerNavViewHeader(authenticationState)
        }
    }

    private fun setDrawerNavViewHeader(authenticationState: AuthViewModel.AuthenticationState) {
        when (authenticationState) {
            AuthViewModel.AuthenticationState.AUTHENTICATED -> {
                // title is Children of nav_header
                drawerNavViewHeader.findViewById<TextView>(R.id.tv_nav_header_title).text = "Вася Пупкин"
            }
            else -> {
                drawerNavViewHeader.invalidate()
            }
        }
    }
}