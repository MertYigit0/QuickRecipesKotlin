package com.mertyigit0.quickrecipeskotlin.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    private var selectedCategory: String? = null

    private  val mealApiService = MealApiService()
    private  val disposable = CompositeDisposable()

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
    private  fun getDataFromSQLite(){
        launch {

            val meals = APIDatabase(getApplication()).mealDao().getAllMeals()
            showMeals(meals)

            Toast.makeText(getApplication(),"SQLite", Toast.LENGTH_LONG).show()
        }
    }



    fun setSelectedCategory(category: String) {
        selectedCategory = category
    }


    private fun getDataFromAPI() {


        val categoryName = selectedCategory ?: return // Eğer kategori adı null ise, işlemi sonlandır.

        disposable.add(
            mealApiService.getData(categoryName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<MealModel>>() {
                    override fun onSuccess(t: List<MealModel>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(),"API",Toast.LENGTH_LONG).show()
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



    private fun storeInSQLite(list : List<MealModel>){
    launch {
        val dao = APIDatabase(getApplication()).mealDao()
        dao.deleteAllMeal()
        val instertIds = dao.insertALL(*list.toTypedArray())
        var i = 0
        while (i<list.size){
            list[i].uuid = instertIds[i].toInt()
            i = i +1
        }
        showMeals(list)
    }
        customSharedPreferences.saveTime(System.nanoTime())

    }










    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }


}