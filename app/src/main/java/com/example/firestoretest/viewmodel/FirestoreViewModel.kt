package com.example.firestoretest.viewmodel

import androidx.lifecycle.ViewModel
import com.example.firestoretest.domain.FirestoreUseCase

class FirestoreViewModel: ViewModel() {

    val firestoreUseCase = FirestoreUseCase()

    fun login(email: String, password: String) {
        firestoreUseCase.login(email, password)
    }

    fun registerUser(email: String, password: String) {
        firestoreUseCase.registerUser(email, password)
    }

    fun saveUser(name: String, lastName: String, email: String, phoneNumber: String, password: String) {
        firestoreUseCase.saveUser(name, lastName, email, phoneNumber, password)
    }
}