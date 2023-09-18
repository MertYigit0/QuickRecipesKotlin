package com.mertyigit0.quickrecipeskotlin.service

import com.mertyigit0.quickrecipeskotlin.model.CategoriesModel
import com.mertyigit0.quickrecipeskotlin.model.CategoriesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface CategoryAPI {
    @GET("api/json/v1/1/categories.php")
    fun getCategories(): Single<CategoriesResponse>
}