package com.mertyigit0.quickrecipeskotlin.service

import com.mertyigit0.quickrecipeskotlin.model.MealResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MealAPI {

    @GET("api/json/v1/1/filter.php")
    fun getMealsByCategory(@Query("c") category: String): Single<MealResponse>
}
