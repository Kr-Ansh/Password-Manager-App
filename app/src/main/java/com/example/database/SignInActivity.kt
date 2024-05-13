package com.example.database

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    companion object{
        const val KEY1 = "com.example.Database.SignInActivity.name"
        const val KEY2 = "com.example.Database.SignInActivity.pass"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val login = findViewById<Button>(R.id.btnSignIn)
        val btn = findViewById<Button>(R.id.btnSignUp)
        val username = findViewById<EditText>(R.id.userName)

        btn.setOnClickListener {
            intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            if (username.text.toString().isNotEmpty()){
                readData(username.text.toString())
            }
            else{
                Toast.makeText(this, "Please enter your username", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun readData(uniqueId : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.child(uniqueId).get().addOnSuccessListener {
            if(it.exists()){
                val pass = it.child("password").value.toString()
                val name = it.child("name").value.toString()

                val newIntent = Intent(this, WelcomeActivity::class.java)
                newIntent.putExtra(KEY1, name)
                newIntent.putExtra(KEY2, pass)
                startActivity(newIntent)
            }
            else{
                Toast.makeText(this, "User does not exist\nPlease Sign up", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Failed, Error from our side", Toast.LENGTH_SHORT).show()
        }
    }
}