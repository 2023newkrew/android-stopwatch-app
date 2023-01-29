package com.survivalcoding.stopwatch.presentation.view

import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.survivalcoding.stopwatch.R
import com.survivalcoding.stopwatch.di.StopWatchApplication
import com.survivalcoding.stopwatch.di.component.MainActivityComponent

class MainActivity : AppCompatActivity() {
    lateinit var mainComponent: MainActivityComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        mainComponent = (applicationContext as StopWatchApplication).appComponent.mainActivityComponent().create()
        mainComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set action bar color
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.black)))

        // set navigation bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.dark_gray)
        }

        val navView: BottomNavigationView = findViewById(R.id.navigation)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.alarm, R.id.clock, R.id.timer, R.id.stopwatch, R.id.bedtime))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}