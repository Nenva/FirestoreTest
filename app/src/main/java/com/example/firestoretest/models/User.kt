package com.example.firestoretest.models

data class User(
    val id: String = "",
    var names: String = "",
    var lastNames: String = "",
    var email: String = "",
    var phoneNumber: String = "",
    var password: String = ""
)