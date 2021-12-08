package com.example.myapplicationn5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextRepeatPassword: EditText
    private lateinit var buttonSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        submitListener()
    }

    private fun init() {
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextRepeatPassword = findViewById(R.id.editTextRepeatPassword)
        buttonSubmit = findViewById(R.id.buttonSubmit)
    }

    private fun submitListener() {
        buttonSubmit.setOnClickListener {

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val repeatpassword = editTextRepeatPassword.text.toString()


            if (email.isEmpty() || password.isEmpty() || repeatpassword.isEmpty()) {
                Toast.makeText(this, "შენ რა დანარჩენ გრაფებს მე მიტოვებ შესავსებად?", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            if (email.contains("@")) {
                if (password == repeatpassword) {
                    if(!password.isDigitsOnly()){
                        if (password.length >= 9) {
                            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(this,
                                            "ყოჩაღ კაი ბიჭი ხარ შენ!",
                                            Toast.LENGTH_LONG).show()
                                    } else {
                                        Toast.makeText(this,
                                            "შეცდომა! თავიდან სცადე", Toast.LENGTH_SHORT).show()
                                    }
                                }
                        }else{
                            Toast.makeText(this,"პაროლი 9-ზე ნაკლები სიმბოლოსგან შედგება დროზე გაასწორე!", Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(this, "რით ვერ დაიმახსოვრე მარტო ციფრები არ შეიძლება პაროლში!", Toast.LENGTH_LONG).show()
                    }
                } else{
                    Toast.makeText(this, "რა არის პაროლი უკვე დაგავიწყდა?", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "იმეილი ძაღლუკის გარეშე გაგაიგია?", Toast.LENGTH_LONG).show()
            }
        }
    }
}