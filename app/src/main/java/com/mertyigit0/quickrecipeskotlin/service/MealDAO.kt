package com.mertyigit0.quickrecipeskotlin.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mertyigit0.quickrecipeskotlin.model.MealModel

@Dao
interface MealDAO {
    @Insert
    suspend fun insertALL(vararg meals: MealModel):List<Long>

    @Query("SELECT * FROM meals")
    suspend fun getAllMeals(): List<MealModel>

    @Query("SELECT * FROM meals WHERE idMeal = :mealId")
    suspend fun getMealById(mealId: String): MealModel


    @Query("DELETE FROM meals")
    suspend fun deleteAllMeal()

}