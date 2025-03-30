package com.example.project


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.project.databinding.ActivitySignupBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

private lateinit var binding: ActivitySignupBinding
private lateinit var sign_btn: Button
private lateinit var username: TextView
private lateinit var password: TextView
private lateinit var back_btn: FloatingActionButton

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


         val appDatabase = Room.databaseBuilder(
             applicationContext,
             AppDatabase::class.java,
             "app-database"
         ).build()


        username = binding.usernameTextview
        password = binding.passwordTextview
        sign_btn = binding.signupBtn
        back_btn = binding.backBtn

        sign_btn.setOnClickListener {
            lifecycleScope.launch {
                val userDao = appDatabase.userDao()
                 userDao.insertUser(User(username.text.toString(), password.text.toString()))
             }
            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        back_btn.setOnClickListener {
            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}