package com.mertyigit0.quickrecipeskotlin.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.mertyigit0.quickrecipeskotlin.model.MealDetailModel
import com.mertyigit0.quickrecipeskotlin.model.MealModel

@Dao
interface MealDetailDAO {

    @Insert
    suspend fun  insertMealDetail(mealdetail : MealDetailModel)
    @Update
    suspend fun  updateMealDetail(mealdetail : MealDetailModel)
    @Delete
    suspend fun  deleteMealDetail(mealdetail : MealDetailModel)

    @Query("SELECT * FROM meals")
    suspend fun getAllMeals(): List<MealDetailModel>

    @Query("SELECT * FROM mealdetails WHERE idMeal = :mealId")
    suspend fun getMealById(mealId: String): MealDetailModel?






}