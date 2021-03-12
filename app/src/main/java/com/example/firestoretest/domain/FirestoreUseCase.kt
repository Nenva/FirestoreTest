package com.example.firestoretest.domain

import com.example.firestoretest.data.repository.FirebaseRepository

class FirestoreUseCase {

    val repository = FirebaseRepository()

    fun login(email: String, password: String) {
        repository.login(email, password)
    }

    fun registerUser(email: String, password: String) {
        repository.registerUser(email, password)
    }

    fun saveUser(name: String, lastName: String, email: String, phoneNumber: String, password: String) {
        repository.saveUserData(name, lastName, email, phoneNumber, password)
    }
}