package com.mertyigit0.quickrecipeskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertyigit0.quickrecipeskotlin.model.MealDetailModel
import com.mertyigit0.quickrecipeskotlin.service.APIDatabase
import com.mertyigit0.quickrecipeskotlin.service.MealDetailApiService
import com.mertyigit0.quickrecipeskotlin.view.MealDetailFragmentArgs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MealDetailViewModel(application: Application) : BaseViewModel(application) {

    val mealDetailLiveData = MutableLiveData<MealDetailModel>()

    private val mealDetailApiService = MealDetailApiService()
    private val disposable = CompositeDisposable()

    private  var selectedMeal : String? = null




    fun refreshData() {

        getData()
    }

    fun setSelectedMeal(meal: String){
         selectedMeal = meal

    }



    fun getData() {

        val mealName = selectedMeal ?: return

        disposable.add(
            mealDetailApiService.getData(mealName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MealDetailModel>() {
                    override fun onSuccess(t: MealDetailModel) {
                        mealDetailLiveData.value = t
                    }

                    override fun onError(e: Throwable) {
                        val errorMessage = "Hata oluştu: ${e.message}"
                        println(errorMessage)
                    }
                })
        )
    }
}
