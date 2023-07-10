package com.example.filmust

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.filmust.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private var bottomNavigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val login = prefs.getString("userLogin", "")
        ProfileFragment.login = login!!

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bnv_main)

        hideBottomNavigation()

        val controller = (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment)
            .navController

        findViewById<BottomNavigationView>(R.id.bnv_main).apply {
            setupWithNavController(controller)
        }

    }

    override fun onPause() {
        super.onPause()

        val login = ProfileFragment.login
        val prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        prefs.edit().putString("userLogin", login).apply()
    }

    fun hideBottomNavigation() {
        bottomNavigationView?.visibility = View.GONE
    }

    fun showBottomNavigation() {
        bottomNavigationView?.visibility = View.VISIBLE
    }
}
