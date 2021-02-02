package com.example.valuedbycommunity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var singInButton: Button
    private lateinit var gotoRegistrationButton: Button
    private lateinit var forgotPasswordButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputEmail = findViewById(R.id.emailEditTextView)
        inputPassword = findViewById(R.id.passwordEditTextView)
        singInButton = findViewById(R.id.signInButton)
        gotoRegistrationButton = findViewById(R.id.gotoRegistrationButton)
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton)

        mAuth = FirebaseAuth.getInstance()

        singInButton.setOnClickListener {

            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "ცარიელია!!!", Toast.LENGTH_SHORT).show()
            }else{

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        startActivity(Intent(this, GeneralPageActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }

        gotoRegistrationButton.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        forgotPasswordButton.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }


    }
}