package com.mertyigit0.quickrecipeskotlin.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.mertyigit0.quickrecipeskotlin.model.MealDetailModel
import com.mertyigit0.quickrecipeskotlin.model.MealModel
import com.mertyigit0.quickrecipeskotlin.service.APIDatabase
import com.mertyigit0.quickrecipeskotlin.service.MealDetailApiService
import com.mertyigit0.quickrecipeskotlin.util.CustomSharedPreferences
import com.mertyigit0.quickrecipeskotlin.view.MealDetailFragmentArgs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MealDetailViewModel(application: Application) : BaseViewModel(application) {

    val mealDetailLiveData = MutableLiveData<MealDetailModel>()

    private val mealDetailApiService = MealDetailApiService()
    private val disposable = CompositeDisposable()

    private var selectedMeal: String? = null


    private var customSharedPreferences = CustomSharedPreferences(getApplication())
    private var refreshTime = 10_000L * 60 * 1_000_000_000L



    private val auth = FirebaseAuth.getInstance()
    // Firestore referansı
    private val db = FirebaseFirestore.getInstance()



    fun refreshData(){

    val updateTime = customSharedPreferences.getTime()
    if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime)
    {
        getDataFromSQLite()

    } else
    {
        getDataFromAPI()
    }
}


        fun setSelectedMeal(meal: String) {
            selectedMeal = meal

        }

    private fun getDataFromSQLite() {
        viewModelScope.launch {
            selectedMeal?.let { mealId ->
                val dao = APIDatabase(getApplication()).mealDetailDao()
                val mealDetail = dao.getMealById(mealId)

                if (mealDetail != null) {
                    // Veri SQLite'dan geldi, göster
                    showMeals(mealDetail)
                    Toast.makeText(getApplication(), "Sqlite detail", Toast.LENGTH_SHORT).show()
                } else {
                    // Veri daha önce çekilmedi veya boş, API'den çek
                    getDataFromAPI()
                }
            }
        }
    }







    private fun getDataFromAPI() {
        val mealName = selectedMeal ?: return

        disposable.add(
            mealDetailApiService.getData(mealName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MealDetailModel>() {
                    override fun onSuccess(t: MealDetailModel) {
                        //mealDetailLiveData.value = t
                        storeInSQLite(t) // API'den gelen veriyi SQLite'a kaydet
                        Toast.makeText(getApplication(), "API detail", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(e: Throwable) {
                        val errorMessage = "Hata oluştu: ${e.message}"
                        println(errorMessage)
                    }
                })
        )
    }


    private fun showMeals(mealList : MealDetailModel){
        mealDetailLiveData.value  = mealList




    }
    private fun storeInSQLite(mealDetail: MealDetailModel) {
        launch {
            val dao = APIDatabase(getApplication()).mealDetailDao()

            // Mevcut verileri silmek yerine yeni verileri ekleyin
            dao.insertMealDetail(mealDetail)

            // Veriyi göster
            showMeals(mealDetail)
        }
        customSharedPreferences.saveTime(System.nanoTime())
    }


    override fun onCleared() {
        super.onCleared()

        disposable.clear()
    }

    fun addFavoriteMeal(idMeal: String) {
        // Mevcut kullanıcının UID'sini al
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        // Kullanıcı UID'si varsa işleme devam et
        userId?.let {
            // Firestore referansını al
            val db = FirebaseFirestore.getInstance()

            // "users" koleksiyonu referansı
            val usersCollection = db.collection("users")

            // Kullanıcının UID'siyle belirtilen bir doküman referansı
            val userDocument = usersCollection.document(it)

            // "favorites" koleksiyonu referansı
            val favoritesCollection = userDocument.collection("favorites")

            // Kullanıcının dokümanını al ve varlık durumunu kontrol et
            userDocument.get().addOnSuccessListener { userDocumentSnapshot ->
                if (userDocumentSnapshot.exists()) {
                    // Doküman varsa, "favorites" koleksiyonunu kontrol et
                    checkAndAddFavorite(favoritesCollection, idMeal)
                } else {
                    // Doküman yoksa, oluştur ve "favorites" koleksiyonunu ekleyip idMeal'i eklemeyi dene
                    usersCollection.document(it)
                        .set(mapOf<String, Any>()) // Boş bir doküman oluştur
                        .addOnSuccessListener {
                            // Doküman başarıyla oluşturuldu, şimdi "favorites" koleksiyonunu ekleyip idMeal'i eklemeyi dene
                            checkAndAddFavorite(favoritesCollection, idMeal)
                        }
                        .addOnFailureListener { e ->
                            // Hata durumunda buraya düşer
                            println("Doküman oluşturulurken hata oluştu: $e")
                        }
                }
            }
        }
    }

    // "favorites" koleksiyonunu kontrol et ve idMeal'i ekleyip eklemediğini kontrol et
    private fun checkAndAddFavorite(favoritesCollection: CollectionReference, idMeal: String) {
        // "favorites" koleksiyonu içinde belirtilen bir doküman referansı
        val favoriteDocument = favoritesCollection.document("favoriteDocument")

        // Koleksiyon içinde belirtilen bir dokümanın varlığını kontrol et
        favoriteDocument.get().addOnSuccessListener { favoriteDocumentSnapshot ->
            if (favoriteDocumentSnapshot.exists()) {
                // Doküman varsa, idMeal'i koleksiyona ekle
                favoriteDocument.update("idMeals", FieldValue.arrayUnion(idMeal))
                    .addOnSuccessListener {
                        // Başarılı bir şekilde güncellendi
                        println("Favorilere başarıyla eklendi: $idMeal")
                    }
                    .addOnFailureListener { e ->
                        // Hata durumunda buraya düşer
                        println("Favorilere eklenirken hata oluştu: $e")
                    }
            } else {
                // Doküman yoksa, oluştur ve idMeal'i ekle
                favoriteDocument.set(mapOf("idMeals" to arrayListOf(idMeal)))
                    .addOnSuccessListener {
                        // Başarılı bir şekilde oluşturuldu
                        println("Favorilere başarıyla eklendi: $idMeal")
                    }
                    .addOnFailureListener { e ->
                        // Hata durumunda buraya düşer
                        println("Favorilere eklenirken hata oluştu: $e")
                    }
            }
        }
    }





}

