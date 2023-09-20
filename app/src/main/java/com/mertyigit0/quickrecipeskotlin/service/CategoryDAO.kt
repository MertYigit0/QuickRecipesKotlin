package com.mertyigit0.quickrecipeskotlin.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mertyigit0.quickrecipeskotlin.model.CategoriesModel
import com.mertyigit0.quickrecipeskotlin.model.MealModel

@Dao
interface CategoryDAO {

    @Insert
    suspend fun  insertAll(vararg  categories : CategoriesModel):List<Long>

    @Query("SELECT * FROM categoriesmodel")
    suspend fun  getAllCategories() : List<CategoriesModel>

    @Query("SELECT * FROM CategoriesModel WHERE strCategory = :categoryName")
    suspend fun getCategory(categoryName: String): CategoriesModel

   @Query("DELETE FROM categoriesmodel")
   suspend fun deleteAllCategories()


}