package com.example.firestoretest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.firestoretest.LoginActivity
import com.example.firestoretest.R
import com.example.firestoretest.databinding.ActivityRegisterBinding
import com.example.firestoretest.models.User
import com.example.firestoretest.viewmodel.FirestoreViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.shashank.sony.fancytoastlib.FancyToast

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: FirestoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        viewModel = ViewModelProviders.of(this).get(FirestoreViewModel::class.java)

        binding.buttonRegisterUser.setOnClickListener {
            registerUser()
        }

        binding.textLogin.setOnClickListener {
            goLoginActivityFromRegisterActivity()
        }
    }

    /*private fun registerUser() {
        val name = binding.registerUserName.text.toString().trim()
        val email = binding.registerUserEmail.text.toString().trim()
        if (name.isNotEmpty() && email.isNotEmpty()) {
            validateRegisterDetails()
            viewModel.registerUser(name, email)
        } else {
            FancyToast.makeText(this, "Por favor llene todos los campos", FancyToast.ERROR,
            FancyToast.LENGTH_SHORT, false).show()
        }
    }*/

    private fun registerUser() {
        if (validateRegisterDetails()) {
            val name: String = binding.registerUserName.text.toString().trim { it <= ' ' }
            val lastName: String = binding.registerUserLastname.text.toString().trim { it <= ' ' }
            val email: String = binding.registerUserEmail.text.toString().trim { it <= ' ' }
            val phoneNumber: String = binding.registerUserPhoneNumber.text.toString().trim { it <= ' ' }
            val password: String = binding.registerUserPassword.text.toString().trim { it <= ' ' }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        viewModel.saveUser(name, lastName, email, phoneNumber, password)
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        goLoginActivityFromRegisterActivity()
                    }
                }
        }
    }

    private fun validateRegisterDetails(): Boolean {
        val names = binding.registerUserName.text.toString()
        val lastNames = binding.registerUserLastname.text.toString()
        val email = binding.registerUserEmail.text.toString()
        val phoneNumber = binding.registerUserPhoneNumber.text.toString()
        val password = binding.registerUserPassword.text.toString()
        return when {
            TextUtils.isEmpty(names.trim { it <= ' ' }) -> {
                FancyToast.makeText(this, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR, false).show()
                false
            }

            TextUtils.isEmpty(lastNames.trim { it <= ' ' }) -> {
                FancyToast.makeText(this, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR, false).show()
                false
            }

            TextUtils.isEmpty(email.trim { it <= ' ' }) -> {
                FancyToast.makeText(this, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR, false).show()
                false
            }

            TextUtils.isEmpty(phoneNumber.trim { it <= ' ' }) -> {
                FancyToast.makeText(this, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR, false).show()
                false
            }

            TextUtils.isEmpty(password.trim { it <= ' ' }) -> {
                FancyToast.makeText(this, resources.getString(R.string.fill_all_fields), FancyToast.LENGTH_SHORT,
                    FancyToast.ERROR, false).show()
                false
            }

            else -> {
                true
            }
        }
    }

    /*private fun saveUser() {
        var name = binding.registerUserName.text.toString().trim()
        var lastName = binding.registerUserLastname.text.toString().trim()
        var email = binding.registerUserEmail.text.toString().trim()
        var phoneNumber = binding.registerUserPhoneNumber.text.toString().trim()
        var password = binding.registerUserPassword.text.toString().trim()
        if (name.isNotEmpty() &&
                lastName.isNotEmpty() &&
                email.isNotEmpty() &&
                phoneNumber.isNotEmpty() &&
                password.isNotEmpty()) {
            viewModel.saveUser(name, lastName, email, phoneNumber, password)
            FancyToast.makeText(this, "Tu usuario ha sido creado", FancyToast.SUCCESS,
            FancyToast.LENGTH_SHORT, false).show()
            goLoginActivityFromRegisterActivity()
        } else {
            FancyToast.makeText(this, "El usuario no ha sido creado", FancyToast.ERROR,
                FancyToast.LENGTH_SHORT, false).show()
        }
    }*/

    private fun goLoginActivityFromRegisterActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}