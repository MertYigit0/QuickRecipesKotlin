package com.mertyigit0.quickrecipeskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertyigit0.quickrecipeskotlin.model.CategoriesModel
import com.mertyigit0.quickrecipeskotlin.model.MealModel
import com.mertyigit0.quickrecipeskotlin.service.MealApiService
import com.mertyigit0.quickrecipeskotlin.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class InsideCategoryViewModel(application: Application) : BaseViewModel(application) {

    val meals = MutableLiveData<List<MealModel>>()
    val mealError = MutableLiveData<Boolean>()
    private var selectedCategory: String? = null

    private  val mealApiService = MealApiService()
    private  val disposable = CompositeDisposable()





    fun refreshData(){

        getDataFromAPI()

    }
    fun setSelectedCategory(category: String) {
        selectedCategory = category
    }


    private fun getDataFromAPI() {
        val categoryName = "Seafood"
            //selectedCategory ?: return // Eğer kategori adı null ise, işlemi sonlandır.

        disposable.add(
            mealApiService.getData(categoryName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<MealModel>>() {
                    override fun onSuccess(t: List<MealModel>) {
                        meals.value = t
                        mealError.value = false
                    }

                    override fun onError(e: Throwable) {
                        val errorMessage = "Hata oluştu: ${e.message}"
                        println(errorMessage)
                        mealError.value = true
                    }
                })
        )
    }


}