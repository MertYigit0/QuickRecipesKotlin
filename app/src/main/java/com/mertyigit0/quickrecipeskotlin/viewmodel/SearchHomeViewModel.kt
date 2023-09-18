package com.mertyigit0.quickrecipeskotlin.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertyigit0.quickrecipeskotlin.model.CategoriesModel
import com.mertyigit0.quickrecipeskotlin.service.CategoryApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchHomeViewModel : ViewModel() {

    val categories = MutableLiveData<List<CategoriesModel>>()
    val categoryError = MutableLiveData<Boolean>()

    private val categoryApiService = CategoryApiService()
    private  val disposable = CompositeDisposable()



    fun refreshData(){
/*
        val category = CategoriesModel("1","Beef", "https://www.themealdb.com/images/category/beef.png" ,"Beef is ths")
        val categor1 = CategoriesModel("2","chicken", "https://www.themealdb.com/images/category/beef.png" ,"chickcihicken")
        val category3 = CategoriesModel("1","Beef", "https://www.themealdb.com/images/category/beef.png" ,"Beef is ths")
        val categor4 = CategoriesModel("2","chicken", "https://www.themealdb.com/images/category/beef.png" ,"chickcihicken")
        val category2 = CategoriesModel("1","Beef", "https://www.themealdb.com/images/category/beef.png" ,"Beef is ths")
        val categor5 = CategoriesModel("2","chicken", "https://www.themealdb.com/images/category/beef.png" ,"chickcihicken")
        val category6 = CategoriesModel("1","Beef", "https://www.themealdb.com/images/category/beef.png" ,"Beef is ths")
        val categor7 = CategoriesModel("2","chicken", "https://www.themealdb.com/images/category/beef.png" ,"chickcihicken")

    val categoryList = arrayListOf<CategoriesModel>(category,categor1,category2,categor5,categor4,category3,categor7,category6)
        categories.value= categoryList
        categoryError.value = false

 */



        getDataFromAPI()

    }

    private  fun getDataFromAPI(){

        disposable.add(
            categoryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object  : DisposableSingleObserver<List<CategoriesModel>>(){
                    override fun onSuccess(t: List<CategoriesModel>) {
                       categories.value = t
                        categoryError.value = false

                    }

                    override fun onError(e: Throwable) {

                        val errorMessage = "Hata oluştu: ${e.message}"
                        println(errorMessage)
                        categoryError.value = true

                    }

                })




        )



    }







}