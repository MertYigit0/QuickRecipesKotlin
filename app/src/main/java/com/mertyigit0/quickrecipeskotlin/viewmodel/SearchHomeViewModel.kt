package com.mertyigit0.quickrecipeskotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertyigit0.quickrecipeskotlin.model.CategoriesModel

class SearchHomeViewModel : ViewModel() {

    val categories = MutableLiveData<List<CategoriesModel>>()
    val countryError = MutableLiveData<Boolean>()
}