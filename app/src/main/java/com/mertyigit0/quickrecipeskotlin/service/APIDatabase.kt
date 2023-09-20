package com.mertyigit0.quickrecipeskotlin.service



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mertyigit0.quickrecipeskotlin.model.CategoriesModel
import com.mertyigit0.quickrecipeskotlin.model.MealModel


@Database(entities = arrayOf(CategoriesModel::class,MealModel::class), version = 3)
abstract class APIDatabase : RoomDatabase() {

    abstract fun categoryDao() : CategoryDAO
    abstract  fun mealDao() : MealDAO

    companion object {

        @Volatile
        private var instance: APIDatabase? = null

        private val lock = Any()
        operator fun invoke(context: Context) = instance?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }

        }


        private  fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,APIDatabase::class.java,"APIDatabase"
        ).build()
    }


}