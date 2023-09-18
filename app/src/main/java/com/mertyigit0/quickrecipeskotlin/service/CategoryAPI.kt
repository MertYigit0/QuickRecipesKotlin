package com.mertyigit0.quickrecipeskotlin.service

import com.mertyigit0.quickrecipeskotlin.model.CategoriesModel
import io.reactivex.Single
import retrofit2.http.GET

interface CategoryAPI {

    //https://www.themealdb.com/api/json/v1/1/categories.php


    @GET("api/json/v1/1/categories.php")
    fun getCategories():Single<List<CategoriesModel>>




}