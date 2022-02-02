package com.shashank.dagger2cwm.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.shashank.dagger2cwm.BaseActivity
import com.shashank.dagger2cwm.R
import com.shashank.dagger2cwm.ui.main.posts.PostsFragment
import com.shashank.dagger2cwm.ui.main.profile.ProfileFragment

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        initNavigation()
    }

    private fun initNavigation() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView, navController)
        navigationView.setNavigationItemSelectedListener(this)
    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.logout -> {
                sessionManager.logOut()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {

            R.id.nav_profile -> {
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.profileScreen)
            }

            R.id.nav_posts -> {
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.postsScreen)
            }
        }

        item.isChecked = true
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}