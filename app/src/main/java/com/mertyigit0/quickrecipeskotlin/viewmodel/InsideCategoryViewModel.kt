package com.mertyigit0.quickrecipeskotlin.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertyigit0.quickrecipeskotlin.model.CategoriesModel
import com.mertyigit0.quickrecipeskotlin.model.MealModel
import com.mertyigit0.quickrecipeskotlin.service.APIDatabase
import com.mertyigit0.quickrecipeskotlin.service.MealApiService
import com.mertyigit0.quickrecipeskotlin.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class InsideCategoryViewModel(application: Application) : BaseViewModel(application) {

    val meals = MutableLiveData<List<MealModel>>()
    val mealError = MutableLiveData<Boolean>()

    private  val mealApiService = MealApiService()
    private  val disposable = CompositeDisposable()

    private var selectedCategory: String? = null

    private  var customSharedPreferences = CustomSharedPreferences(getApplication())
    private  var refreshTime = 10_000L * 60 * 1_000_000_000L



    fun refreshData(){

        val updateTime = customSharedPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            getDataFromSQLite()

        } else {
            getDataFromAPI()
        }

    }

    @SuppressLint("SuspiciousIndentation")

    private fun getDataFromSQLite() {
        viewModelScope.launch {
            selectedCategory?.let { categoryName ->
                val dao = APIDatabase(getApplication()).mealDao()
                val existingMeals = dao.getMealsByCategory(categoryName)

                if (existingMeals.isNotEmpty()) {
                    // Veriler mevcutsa göster
                    showMeals(existingMeals)
                    Toast.makeText(getApplication(), "SQLite inside", Toast.LENGTH_SHORT).show()
                } else {
                    // Veriler daha önce çekilmedi veya boş, API'den çek
                    getDataFromAPI()
                }
            }
        }
    }







    fun setSelectedCategory(category: String) {
        selectedCategory = category
    }



    private fun getDataFromAPI() {
        val categoryName = selectedCategory ?: return
        disposable.add(
            mealApiService.getData(categoryName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<MealModel>>() {
                    override fun onSuccess(t: List<MealModel>) {
                        // Kategori bilgisini yemeklere ekleyin
                        t.forEach { it.category = categoryName }
                        storeInSQLite(t)
                        Toast.makeText(getApplication(), "API category inside", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        val errorMessage = "Hata oluştu: ${e.message}"
                        println(errorMessage)
                        mealError.value = true
                    }
                })
        )
    }


private fun showMeals(mealList : List<MealModel>){
    meals.value = mealList
    mealError.value = false

}



    private fun storeInSQLite(list: List<MealModel>) {
        launch {
            val dao = APIDatabase(getApplication()).mealDao()

            // Mevcut verileri silmek yerine yeni verileri ekleyin
            val insertIds = dao.insertALL(*list.toTypedArray())

            // insertALL işlemi sonucunda oluşturulan yeni veri kimliklerini alın
            var i = 0
            while (i < list.size) {
                list[i].uuid = insertIds[i].toInt()
                i++
            }

            // Verileri göster
            showMeals(list)
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }











    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }


}