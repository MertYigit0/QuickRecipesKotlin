package com.mertyigit0.quickrecipeskotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginCheckViewModel : ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean>
        get() = _isLoggedIn

    init {
        // Oturum durumunu dinlemek için FirebaseAuth'a bir dinleyici ekliyoruz.
        FirebaseAuth.getInstance().addAuthStateListener { firebaseAuth ->
            _isLoggedIn.value = firebaseAuth.currentUser != null
        }
    }

    companion object {
        private var instance: LoginCheckViewModel? = null

        fun getInstance(): LoginCheckViewModel {
            if (instance == null) {
                instance = LoginCheckViewModel()
            }
            return instance as LoginCheckViewModel
        }
    }
}
