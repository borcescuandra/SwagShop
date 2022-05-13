package com.example.shopswag

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.shopswag.controller.MainActivity
import com.example.shopswag.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

//viewBinding
private lateinit var binding: ActivityLoginBinding

//action bar
private lateinit var actionBar: ActionBar

//progress dialog
private lateinit var progressDialog: ProgressDialog

//FirebaseAuth
private lateinit var firebaseAuth: FirebaseAuth

private var email = ""
private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityLoginBinding.inflate(layoutInflater)
    setContentView(binding.root)

    //configure action bar
    actionBar = supportActionBar!!
    actionBar.title = "Login"

    //configure progress dialog
    progressDialog = ProgressDialog(this)
    progressDialog.setTitle("Please wait!")
    progressDialog.setMessage("Logging in...")
    progressDialog.setCanceledOnTouchOutside(false)

    //init firebaseAuth
    firebaseAuth = FirebaseAuth.getInstance()
    checkUser()

    //handle click, open SignUpActivity
    binding.noAccount.setOnClickListener {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    //handle click, begin login
    binding.loginbtn.setOnClickListener {
        //before logging in, validate data
        validateData()
    }

}

private fun validateData() {
    email = binding.email.text.toString().trim()
    password = binding.password.text.toString().trim()

    //validate data
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        //invalid email format
        binding.email.error = "Invalid email!"
    } else if (TextUtils.isEmpty(password)) {
        //no password entered
        binding.password.error = "Please enter a password!"
    } else {
        //data is validated, begin login
        loginUser()
    }
}

private fun loginUser() {
    //show progress
    progressDialog.show()
    firebaseAuth.signInWithEmailAndPassword(email, password)
        .addOnSuccessListener {
            //login succes
            progressDialog.dismiss()
            val firebaseUser = firebaseAuth.currentUser
            val email = firebaseUser!!.email
            Toast.makeText(this, "Logged in as $email", Toast.LENGTH_SHORT).show()

            //open AboutUs activity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        .addOnFailureListener { e ->
            //login failed
            progressDialog.dismiss()
            Toast.makeText(this, "Login failed due to ${e.message}", Toast.LENGTH_SHORT).show()
        }
}

private fun checkUser() {
    // 4) Check user type

    val firebaseUser = firebaseAuth.currentUser
    if (firebaseUser != null) {
        //user is already logged in
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
}