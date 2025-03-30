package com.example.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.fragments.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private  lateinit var btn: Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstFragment = FragmentOne()
        val secondFragment = FragmentTwo()

        btn = binding.FragmentsBtn


        btn.setOnClickListener {
            firstFragment.arguments = intent.extras
            supportFragmentManager.beginTransaction().replace(R.id.LinearLayout1, firstFragment).commit()
        }

       binding.FragmentsBtn.setOnLongClickListener {
           secondFragment.arguments = intent.extras
           supportFragmentManager.beginTransaction().replace(R.id.LinearLayout1, secondFragment).commit()
           true
       }
    }
}