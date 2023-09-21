package com.mertyigit0.quickrecipeskotlin.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertyigit0.quickrecipeskotlin.model.CategoriesModel
import com.mertyigit0.quickrecipeskotlin.service.APIDatabase
import com.mertyigit0.quickrecipeskotlin.service.CategoryApiService
import com.mertyigit0.quickrecipeskotlin.util.CustomSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class SearchHomeViewModel(application: Application) :BaseViewModel(application) {

    val categories = MutableLiveData<List<CategoriesModel>>()
    val categoryError = MutableLiveData<Boolean>()

    private val categoryApiService = CategoryApiService()
    private  val disposable = CompositeDisposable()

    private  var customSharedPreferences = CustomSharedPreferences(getApplication())
    private  var refreshTime = 10_000L * 60 * 1_000_000_000L

    fun refreshData() {
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
            val categories = APIDatabase(getApplication()).categoryDao().getAllCategories()

                showCategories(categories)

            Toast.makeText(getApplication(),"SQLite",Toast.LENGTH_LONG).show()
        }
    }

    private  fun getDataFromAPI(){

        disposable.add(
            categoryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object  : DisposableSingleObserver<List<CategoriesModel>>(){
                    override fun onSuccess(t: List<CategoriesModel>) {
                        storeInSQLite(t)
                        Toast.makeText(getApplication(),"API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {

                        val errorMessage = "Hata oluştu: ${e.message}"
                        println(errorMessage)
                        categoryError.value = true

                    }

                })

        )

    }

    private fun showCategories(categoryList : List<CategoriesModel>){
        categories.value = categoryList
        categoryError.value = false
    }
    private fun storeInSQLite(list: List<CategoriesModel>) {
        launch {
            val dao = APIDatabase(getApplication()).categoryDao()
            dao.deleteAllCategories()
            val insertedIds = dao.insertAll(*list.toTypedArray())
            var i = 0
            // Dönen kimlik sütunlarını doğrudan CategoriesModel nesnelerine atayın
            while (i < list.size) {
                list[i].uuid = insertedIds[i].toInt()
                i = i + 1
            }

            showCategories(list)
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }


    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }





}