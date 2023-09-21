package com.mertyigit0.quickrecipeskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertyigit0.quickrecipeskotlin.model.MealDetailModel
import com.mertyigit0.quickrecipeskotlin.service.APIDatabase
import kotlinx.coroutines.launch
import java.util.UUID


class MealDetailViewModel(application: Application) :BaseViewModel(application) {

    val mealDetailLiveData = MutableLiveData<MealDetailModel?>()

    fun getData(idmeal: String){
        launch{
            val dao = APIDatabase(getApplication()).mealDetailDao()
            val mealdetail = dao.getMealById(idmeal)
            mealDetailLiveData.value = mealdetail
        }
        

    }





}