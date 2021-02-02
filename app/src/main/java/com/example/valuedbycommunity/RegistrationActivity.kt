package com.example.valuedbycommunity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var inputConPassword: EditText
    private lateinit var registrationButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        inputEmail = findViewById(R.id.registrationEmailEditText)
        inputPassword = findViewById(R.id.registrationPasswordEditText)
        inputConPassword = findViewById(R.id.registrationPasswordAgainEditText)
        registrationButton = findViewById(R.id.registrationButton)

        mAuth = FirebaseAuth.getInstance()

        registrationButton.setOnClickListener {

            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            val conPassword = inputConPassword.text.toString()

            if(email.isEmpty() || password.isEmpty() || conPassword.isEmpty() || conPassword!=password){
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }else{

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

                    if(task.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }

                }

            }

        }

    }
}