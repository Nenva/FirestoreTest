package com.example.firestoretest.data.repository

import android.content.Context
import com.example.firestoretest.models.User
import com.example.firestoretest.ui.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.shashank.sony.fancytoastlib.FancyToast

class FirebaseRepository {

    val database = FirebaseFirestore.getInstance()
    var auth = FirebaseAuth.getInstance()

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // Notification login successfully
                    auth.currentUser
                } else {
                    // Notification uploading unsuccessfully
                }
            }
    }

    fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    val user = User(firebaseUser.uid)
                    auth.signInWithEmailAndPassword(email, password)
                } else {

                }
            }
    }

    fun saveUserData(name: String, lastName: String, email: String, phoneNumber: String, password: String) {
        val user: HashMap<String, Any> = hashMapOf(
            "name" to name,
            "lastName" to lastName,
            "email" to email,
            "phoneNumber" to phoneNumber,
            "password" to password
        )
        database.collection("users")
            .add(user)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    // Notification uploading successfully
                    auth.currentUser
                } else {
                    // Notification uploading unsuccessfully
                }
            }
    }



}