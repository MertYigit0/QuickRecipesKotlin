package com.mertyigit0.quickrecipeskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mertyigit0.quickrecipeskotlin.viewmodel.LoginCheckViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var viewModelLoginCheck: LoginCheckViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navController = Navigation.findNavController(this, R.id.fragmentContainerView)

        // BottomNavigationView ile ilişkilendirme
        NavigationUI.setupWithNavController(bottomNavigationView, navController)






    }

    override fun onResume() {
        super.onResume()
        // MainActivity içinde Singleton ViewModel'i oluştur
        viewModelLoginCheck = LoginCheckViewModel.getInstance()

    }

    override fun onSupportNavigateUp(): Boolean {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }
}
