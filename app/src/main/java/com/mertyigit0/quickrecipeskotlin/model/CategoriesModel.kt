package com.mertyigit0.quickrecipeskotlin.model

import com.google.gson.annotations.SerializedName




data class CategoriesModel(


    @SerializedName("idCategory")
    val idCategory : String? ,
    @SerializedName("strCategory")
    val strCategory : String? ,
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String? ,
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String?


)