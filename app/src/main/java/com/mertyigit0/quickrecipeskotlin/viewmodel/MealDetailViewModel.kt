package com.mertyigit0.quickrecipeskotlin.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mertyigit0.quickrecipeskotlin.model.MealDetailModel
import com.mertyigit0.quickrecipeskotlin.model.MealModel
import com.mertyigit0.quickrecipeskotlin.service.APIDatabase
import com.mertyigit0.quickrecipeskotlin.service.MealDetailApiService
import com.mertyigit0.quickrecipeskotlin.util.CustomSharedPreferences
import com.mertyigit0.quickrecipeskotlin.view.MealDetailFragmentArgs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MealDetailViewModel(application: Application) : BaseViewModel(application) {

    val mealDetailLiveData = MutableLiveData<MealDetailModel>()

    private val mealDetailApiService = MealDetailApiService()
    private val disposable = CompositeDisposable()

    private var selectedMeal: String? = null


    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10_000L * 60 * 1_000_000_000L


    fun refreshData(){

    val updateTime = customSharedPreferences.getTime()
    if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime)
    {
       // getDataFromSQLite()
        getDataFromAPI()

    } else
    {
        getDataFromAPI()
    }
}


        fun setSelectedMeal(meal: String) {
            selectedMeal = meal

        }

    fun getDataFromSQLite() {
        val mealId = selectedMeal ?: return

        viewModelScope.launch {
            selectedMeal?.let {
                mealId->
                val dao = APIDatabase(getApplication()).mealDetailDao()
                val mealDetail = dao.getMealById(mealId)

                if (mealDetail != null) {
                    // Veri SQLite'dan geldi, göster
                    showMeals(mealDetail)
                    Toast.makeText(getApplication(), "Sqlite detail", Toast.LENGTH_SHORT).show()

                } else {
                    // Veri daha önce çekilmedi veya boş, API'den çek
                    getDataFromAPI()
                }
            }
            }

    }



    fun getDataFromAPI() {

            val mealName = selectedMeal ?: return

            disposable.add(
                mealDetailApiService.getData(mealName)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<MealDetailModel>() {
                        override fun onSuccess(t: MealDetailModel) {
                            mealDetailLiveData.value = t
                            storeInSQLite(t)
                            Toast.makeText(getApplication(), "API detail", Toast.LENGTH_SHORT).show()
                        }

                        override fun onError(e: Throwable) {
                            val errorMessage = "Hata oluştu: ${e.message}"
                            println(errorMessage)
                        }
                    })
            )
        }

    private fun showMeals(mealList : MealDetailModel){
        mealDetailLiveData.value  = mealList




    }
    private fun storeInSQLite(mealDetail: MealDetailModel) {
        launch {
            val dao = APIDatabase(getApplication()).mealDetailDao()

            // Mevcut verileri silmek yerine yeni verileri ekleyin
            dao.insertMealDetail(mealDetail)

            // Veriyi göster
            showMeals(mealDetail)
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }







    }

