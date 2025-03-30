package com.example.project


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.lifecycleScope
import com.example.project.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

private lateinit var binding: ActivityLoginBinding
private lateinit var log_btn: Button
private lateinit var username: TextView
private lateinit var password: TextView
private lateinit var sign_up_btn: Button
private lateinit var back_btn: FloatingActionButton
private lateinit var users : List<User>


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = binding.usernameTv
        password = binding.passwordTv
        log_btn = binding.loginBtn
        sign_up_btn = binding.signUpBtn
        back_btn = binding.backBtn

        val appDatabase = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app-database"
        ).build()

        lifecycleScope.launch {
            val userDao = appDatabase.userDao()
            users = userDao.getAll()
        }


        back_btn.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }

        sign_up_btn.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }

        log_btn.setOnClickListener {
            val user = username.text.toString()
            val pswd = password.text.toString()
            if(validateCredentials(user, pswd)){
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("Username", username.text.toString())
                intent.putExtra("Loged", true)
                startActivity(intent)
            }
            else {
                val toast = Toast.makeText(this,  "Wrong username or password!", Toast.LENGTH_LONG)
                toast.show()
            }
        }
    }


    fun validateCredentials(username: String, password: String): Boolean {
        for(usr in users) {
            if(usr.username == username && usr.password == password){
                return true
            }
        }
        return false
    }
}