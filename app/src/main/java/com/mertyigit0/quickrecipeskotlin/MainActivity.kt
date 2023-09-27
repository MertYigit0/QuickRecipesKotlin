package com.mertyigit0.quickrecipeskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navController = Navigation.findNavController(this, R.id.fragmentContainerView)
        NavigationUI.setupWithNavController(bottomNavigationView,navController)






    }

    override fun onSupportNavigateUp(): Boolean {
        // ActionBar'da geri tuşunu kullanarak doğru şekilde geri gitmek için NavController'ı kullanın
        return NavigationUI.navigateUp(navController, null) || super.onSupportNavigateUp()
    }
}
