package com.mertyigit0.quickrecipeskotlin.service

import com.mertyigit0.quickrecipeskotlin.model.CategoriesModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CategoryApiService {

    //https://www.themealdb.com/api/json/v1/1/categories.php



    private val BASE_URL = "https://www.themealdb.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CategoryAPI::class.java)



    fun getData() : Single<List<CategoriesModel>>{
        return  api.getCategories()
    }


}