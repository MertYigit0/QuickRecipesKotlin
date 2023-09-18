package com.mertyigit0.quickrecipeskotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertyigit0.quickrecipeskotlin.model.CategoriesModel

class SearchHomeViewModel : ViewModel() {

    val categories = MutableLiveData<List<CategoriesModel>>()
    val categoryError = MutableLiveData<Boolean>()


    fun refreshData(){
        val category = CategoriesModel(1,"Beef", "https://www.themealdb.com/images/category/beef.png" ,"Beef is ths")
        val categor1 = CategoriesModel(2,"chicken", "https://www.themealdb.com/images/category/beef.png" ,"chickcihicken")

    val categoryList = arrayListOf<CategoriesModel>(category,categor1)
        categories.value= categoryList
        categoryError.value = false


    }



}