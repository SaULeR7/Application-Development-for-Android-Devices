package com.example.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

lateinit var addButton: Button
lateinit var numberTextView : TextView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModelFactory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.buttonAddOne)
        numberTextView = findViewById(R.id.textViewNumber)

        viewModelFactory= MainActivityViewModelFactory(25)
        var viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)

        val resultObserver = Observer<Int> {
                result -> numberTextView.text = result.toString()
        }
        viewModel.getNumber().observe(this, resultObserver)

        // numberTextView.text = viewModel.number.toString()

        addButton.setOnClickListener {
            viewModel.addNumber()
            // numberTextView.text = viewModel.number.toString()
        }
    }

}