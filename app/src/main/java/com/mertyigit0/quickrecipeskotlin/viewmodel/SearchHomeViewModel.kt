package com.mertyigit0.quickrecipeskotlin.viewmodel

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
    private  var refreshTime = 1000 *10 * 60 * 1000 *1000 *1000L //10k minute

    fun refreshData(){
/*
        val category = CategoriesModel("1","Beef", "https://www.themealdb.com/images/category/beef.png" ,"Beef is ths")
        val categor1 = CategoriesModel("2","chicken", "https://www.themealdb.com/images/category/beef.png" ,"chickcihicken")

    val categoryList = arrayListOf<CategoriesModel>(category,categor1)
        categories.value= categoryList
        categoryError.value = false
 */


        val updateTime = customSharedPreferences.getTime()
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){
            getDataFromSQLite()
        }else{
            getDataFromAPI()
        }


    }

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

            // Dönen kimlik sütunlarını doğrudan CategoriesModel nesnelerine atayın
            for (i in insertedIds.indices) {
                list[i].strCategory = insertedIds[i].toString()
            }

            showCategories(list)
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }








}