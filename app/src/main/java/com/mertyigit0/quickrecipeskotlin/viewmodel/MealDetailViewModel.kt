package com.mertyigit0.quickrecipeskotlin.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mertyigit0.quickrecipeskotlin.model.MealDetailModel
import com.mertyigit0.quickrecipeskotlin.service.APIDatabase
import com.mertyigit0.quickrecipeskotlin.service.MealDetailApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MealDetailViewModel(application: Application) : BaseViewModel(application) {

    val mealDetailLiveData = MutableLiveData<MealDetailModel>()

    private val mealDetailApiService = MealDetailApiService()
    private val disposable = CompositeDisposable()

    fun refreshData() {
        // Örnek bir meal ID kullanılabilir, gerçek bir ID ile değiştirilmelidir.
        getData("52959")
    }

    fun getData(idmeal: String) {
        disposable.add(
            mealDetailApiService.getData(idmeal)
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
