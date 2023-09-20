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
    suspend fun insertMeal(meal: MealModel)

    @Update
    suspend fun updateMeal(meal: MealModel)

    @Delete
    suspend fun deleteMeal(meal: MealModel)

    @Query("SELECT * FROM meals")
    suspend fun getAllMeals(): List<MealModel>

    @Query("SELECT * FROM meals WHERE idMeal = :mealId")
    suspend fun getMealById(mealId: String): MealModel?
}