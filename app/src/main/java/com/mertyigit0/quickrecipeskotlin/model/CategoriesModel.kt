package com.mertyigit0.quickrecipeskotlin.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class CategoriesResponse(
    @SerializedName("categories")
    val categories: List<CategoriesModel>
)

@Entity
data class CategoriesModel(


    @ColumnInfo(name = "idCategory") // name ile sütun adını belirtin
    @SerializedName("idCategory")
    var idCategory : String? ,


    @ColumnInfo(name = "strCategory")
    @SerializedName("strCategory")
    var strCategory : String ,

    @ColumnInfo(name = "strCategoryThumb")
    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String? ,

    @ColumnInfo(name = "strCategoryDescription")
    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String?,


    @PrimaryKey ( autoGenerate = true)
        var uuid : Int = 0

)