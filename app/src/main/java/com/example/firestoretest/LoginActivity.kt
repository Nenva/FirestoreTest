package com.example.firestoretest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.firestoretest.databinding.ActivityLoginBinding
import com.example.firestoretest.ui.MainActivity
import com.example.firestoretest.ui.RegisterActivity
import com.example.firestoretest.viewmodel.FirestoreViewModel
import com.google.firebase.auth.FirebaseAuth
import com.shashank.sony.fancytoastlib.FancyToast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: FirestoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(FirestoreViewModel::class.java)

        binding.buttonLoginUser.setOnClickListener {
            login()
            goMainActivityFromLoginActivity()
        }

        binding.textRegister.setOnClickListener {
            goRegisterActivityFromLoginActivity()
        }
    }

    private fun login() {
        var email = binding.loginUserEmail.text.toString().trim()
        var password = binding.loginUserPassword.text.toString().trim()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.login(email, password)
            FancyToast.makeText(this, "Has ingresado correctamente", FancyToast.SUCCESS,
                FancyToast.LENGTH_SHORT, false).show()
        } else {
            FancyToast.makeText(this, "Por favor, llenar todos los campos", FancyToast.ERROR,
                FancyToast.LENGTH_SHORT, false).show()
        }
    }

    private fun goRegisterActivityFromLoginActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun goMainActivityFromLoginActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}