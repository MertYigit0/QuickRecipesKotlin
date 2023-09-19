package com.mertyigit0.quickrecipeskotlin.model

import com.google.gson.annotations.SerializedName


data class MealResponse(
    @SerializedName("meals")
    val meals: List<MealModel>

)


data class MealModel(
    @SerializedName("strMeal")
    val strMeal: String?,
    @SerializedName("strMealThumb")
    val strMealThumb: String?,
    @SerializedName("idMeal")
    val idMeal: String?,

)
