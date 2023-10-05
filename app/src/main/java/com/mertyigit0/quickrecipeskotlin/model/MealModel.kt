package com.mertyigit0.quickrecipeskotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class MealResponse(
    @SerializedName("meals")
    val meals: List<MealModel>

)


@Entity(tableName = "meals")
data class MealModel(

    @ColumnInfo(name = "idMeal")
    @SerializedName("idMeal")
    val idMeal: String,

    @ColumnInfo(name = "strMeal")
    @SerializedName("strMeal")
    val strMeal: String?,

    @ColumnInfo(name = "strMealThumb")
    @SerializedName("strMealThumb")
    val strMealThumb: String?,

    @PrimaryKey ( autoGenerate = true)
    var uuid : Int = 0,

    @ColumnInfo(name = "category")
    @SerializedName("category")
    var category: String?,

    @ColumnInfo(name = "favorite")
    @SerializedName("favorite")
    var favorite: Boolean?


)
