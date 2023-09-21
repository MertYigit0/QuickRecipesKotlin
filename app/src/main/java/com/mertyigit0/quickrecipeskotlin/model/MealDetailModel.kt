package com.mertyigit0.quickrecipeskotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class MealDetailResponse(
    @SerializedName("meals")
    val mealDetails: List<MealDetailModel>
)



@Entity(tableName = "mealdetails")
data class MealDetailModel(

    @SerializedName("idMeal")
    val idMeal: String?,

    @ColumnInfo(name = "strMeal")
    @SerializedName("strMeal")
    val strMeal: String?,

    @ColumnInfo(name = "strDrinkAlternate")
    @SerializedName("strDrinkAlternate")
    val strDrinkAlternate: String?,

    @ColumnInfo(name = "strCategory")
    @SerializedName("strCategory")
    val strCategory: String?,

    @ColumnInfo(name = "strArea")
    @SerializedName("strArea")
    val strArea: String?,

    @ColumnInfo(name = "strInstructions")
    @SerializedName("strInstructions")
    val strInstructions: String?,

    @ColumnInfo(name = "strMealThumb")
    @SerializedName("strMealThumb")
    val strMealThumb: String?,

    @ColumnInfo(name = "strTags")
    @SerializedName("strTags")
    val strTags: String?,

    @ColumnInfo(name = "strYoutube")
    @SerializedName("strYoutube")
    val strYoutube: String?,

    @ColumnInfo(name = "strIngredient1")
    @SerializedName("strIngredient1")
    val strIngredient1: String?,

    @ColumnInfo(name = "strIngredient2")
    @SerializedName("strIngredient2")
    val strIngredient2: String?,

    @ColumnInfo(name = "strIngredient3")
    @SerializedName("strIngredient3")
    val strIngredient3: String?,

    @ColumnInfo(name = "strIngredient4")
    @SerializedName("strIngredient4")
    val strIngredient4: String?,

    @ColumnInfo(name = "strIngredient5")
    @SerializedName("strIngredient5")
    val strIngredient5: String?,

    @ColumnInfo(name = "strIngredient6")
    @SerializedName("strIngredient6")
    val strIngredient6: String?,

    @ColumnInfo(name = "strIngredient7")
    @SerializedName("strIngredient7")
    val strIngredient7: String?,

    @ColumnInfo(name = "strIngredient8")
    @SerializedName("strIngredient8")
    val strIngredient8: String?,

    @ColumnInfo(name = "strIngredient9")
    @SerializedName("strIngredient9")
    val strIngredient9: String?,

    @ColumnInfo(name = "strIngredient10")
    @SerializedName("strIngredient10")
    val strIngredient10: String?,

    @ColumnInfo(name = "strIngredient11")
    @SerializedName("strIngredient11")
    val strIngredient11: String?,

    @ColumnInfo(name = "strIngredient12")
    @SerializedName("strIngredient12")
    val strIngredient12: String?,

    @ColumnInfo(name = "strIngredient13")
    @SerializedName("strIngredient13")
    val strIngredient13: String?,

    @ColumnInfo(name = "strIngredient14")
    @SerializedName("strIngredient14")
    val strIngredient14: String?,

    @ColumnInfo(name = "strIngredient15")
    @SerializedName("strIngredient15")
    val strIngredient15: String?,

    @ColumnInfo(name = "strIngredient16")
    @SerializedName("strIngredient16")
    val strIngredient16: String?,

    @ColumnInfo(name = "strIngredient17")
    @SerializedName("strIngredient17")
    val strIngredient17: String?,

    @ColumnInfo(name = "strIngredient18")
    @SerializedName("strIngredient18")
    val strIngredient18: String?,

    @ColumnInfo(name = "strIngredient19")
    @SerializedName("strIngredient19")
    val strIngredient19: String?,

    @ColumnInfo(name = "strIngredient20")
    @SerializedName("strIngredient20")
    val strIngredient20: String?,

    @ColumnInfo(name = "strMeasure1")
    @SerializedName("strMeasure1")
    val strMeasure1: String?,

    @ColumnInfo(name = "strMeasure2")
    @SerializedName("strMeasure2")
    val strMeasure2: String?,

    @ColumnInfo(name = "strMeasure3")
    @SerializedName("strMeasure3")
    val strMeasure3: String?,

    @ColumnInfo(name = "strMeasure4")
    @SerializedName("strMeasure4")
    val strMeasure4: String?,

    @ColumnInfo(name = "strMeasure5")
    @SerializedName("strMeasure5")
    val strMeasure5: String?,

    @ColumnInfo(name = "strMeasure6")
    @SerializedName("strMeasure6")
    val strMeasure6: String?,

    @ColumnInfo(name = "strMeasure7")
    @SerializedName("strMeasure7")
    val strMeasure7: String?,

    @ColumnInfo(name = "strMeasure8")
    @SerializedName("strMeasure8")
    val strMeasure8: String?,

    @ColumnInfo(name = "strMeasure9")
    @SerializedName("strMeasure9")
    val strMeasure9: String?,

    @ColumnInfo(name = "strMeasure10")
    @SerializedName("strMeasure10")
    val strMeasure10: String?,

    @ColumnInfo(name = "strMeasure11")
    @SerializedName("strMeasure11")
    val strMeasure11: String?,

    @ColumnInfo(name = "strMeasure12")
    @SerializedName("strMeasure12")
    val strMeasure12: String?,

    @ColumnInfo(name = "strMeasure13")
    @SerializedName("strMeasure13")
    val strMeasure13: String?,

    @ColumnInfo(name = "strMeasure14")
    @SerializedName("strMeasure14")
    val strMeasure14: String?,

    @ColumnInfo(name = "strMeasure15")
    @SerializedName("strMeasure15")
    val strMeasure15: String?,

    @ColumnInfo(name = "strMeasure16")
    @SerializedName("strMeasure16")
    val strMeasure16: String?,

    @ColumnInfo(name = "strMeasure17")
    @SerializedName("strMeasure17")
    val strMeasure17: String?,

    @ColumnInfo(name = "strMeasure18")
    @SerializedName("strMeasure18")
    val strMeasure18: String?,

    @ColumnInfo(name = "strMeasure19")
    @SerializedName("strMeasure19")
    val strMeasure19: String?,

    @ColumnInfo(name = "strMeasure20")
    @SerializedName("strMeasure20")
    val strMeasure20: String?,

    @ColumnInfo(name = "strSource")
    @SerializedName("strSource")
    val strSource: String?,

    @ColumnInfo(name = "strImageSource")
    @SerializedName("strImageSource")
    val strImageSource: String? ,

    @ColumnInfo(name = "strCreativeCommonsConfirmed")
    @SerializedName("strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: String? ,

    @ColumnInfo(name = "dateModified")
    @SerializedName("dateModified")
    val dateModified: String?,

    @PrimaryKey ( autoGenerate = true)
    var uuid : Int = 0

)
