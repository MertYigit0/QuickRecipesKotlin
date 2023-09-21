package com.mertyigit0.quickrecipeskotlin.service

import com.mertyigit0.quickrecipeskotlin.model.MealDetailModel
import com.mertyigit0.quickrecipeskotlin.model.MealModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MealDetailApiService {



    private val BASE_URL = "https://www.themealdb.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(MealDetailAPI::class.java)


    fun getData(idMeal :String): Single<List<MealDetailModel>> {
        return api.getMealDeatilbyID(idMeal)
            .map { response -> response.mealDetails }
        //return Single.just(emptyList())
    }

}