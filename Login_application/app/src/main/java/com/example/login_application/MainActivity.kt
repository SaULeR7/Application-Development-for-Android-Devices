package com.example.login_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.login_application.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private lateinit var log_btn: Button
private lateinit var textView: TextView
private lateinit var username: TextView
private lateinit var password: TextView
var users = mapOf("user1" to "1111",
    "user2" to "2222",
    "user3" to "3333")


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        textView = binding.textView
        username = binding.usernameTv
        password = binding.passwordTv
        log_btn = binding.loginBtn

        log_btn.setOnClickListener {
            val user = username.text.toString().toLowerCase()
            val pswd = password.text.toString()
            textView.visibility = View.VISIBLE
            if(validateCredentials(user, pswd)){
                textView.setText("Loged in!!!")
            }
            else {
                textView.setText("Invalid.")
            }
        }
    }

}

fun validateCredentials(username: String, password: String): Boolean {
    for(usr in users) {
        if(usr.key == username && usr.value == password){
            return true
        }
    }
    return false
}