package com.artsam.selfmaker

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(), View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val authViewModel by viewModels<AuthViewModel>()
    private lateinit var drawerNavViewHeader: View

    companion object {
        const val TAG = "LoginFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }

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
        binding.drawerNavView.setNavigationItemSelectedListener(this)

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        log { "onClick(): " + item.itemId }
        when (item.itemId) {
            R.id.navigation_sign_in -> {
                launchSignInFlow()
                return true
            }
        }
        return false
    }

    private fun launchSignInFlow() {
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
        )

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            Log.i(
                TAG,
                "Successfully signed in user " +
                        "${FirebaseAuth.getInstance().currentUser?.displayName}!"
            )
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            Log.i(TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
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