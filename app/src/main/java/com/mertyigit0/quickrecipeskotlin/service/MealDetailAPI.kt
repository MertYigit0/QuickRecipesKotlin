package com.mertyigit0.quickrecipeskotlin.service

import com.mertyigit0.quickrecipeskotlin.model.MealDetailResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDetailAPI {

    @GET("api/json/v1/1/lookup.php")
    fun getMealDeatilbyID(@Query("i")mealId : String):Single<MealDetailResponse>



}