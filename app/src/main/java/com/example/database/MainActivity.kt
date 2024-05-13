package com.example.database

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var dataBase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btnSignUp)
        val username = findViewById<TextInputEditText>(R.id.userName)
        val password = findViewById<TextInputEditText>(R.id.userPassword)
        val btnLogin = findViewById<Button>(R.id.btnSignIn)

        btnLogin.setOnClickListener {
            intent = Intent(applicationContext, SignInActivity::class.java)
            startActivity(intent)
        }

        btn.setOnClickListener {
            val name = username.text.toString()
            val pass = password.text.toString()

            val user = User(name, pass)

            dataBase = FirebaseDatabase.getInstance().getReference("Users")
            if(name.isEmpty() || pass.isEmpty()){
                Toast.makeText(this, "Empty Fields", Toast.LENGTH_SHORT).show()
            }else {
                dataBase.child(name).setValue(user).addOnSuccessListener {
                    username.text?.clear()
                    password.text?.clear()
                    Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}