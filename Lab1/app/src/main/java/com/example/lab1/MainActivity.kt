package com.example.lab1

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import java.util.*

private lateinit var languageSpinner: Spinner
private lateinit var textView: TextView
private lateinit var languages: Array<String>

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        languageSpinner = findViewById(R.id.languageSpinner)
        textView = findViewById(R.id.textView)

        languages = resources.getStringArray(R.array.languages)

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        languageSpinner.adapter = spinnerAdapter



        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                val locale = when (languages[position]) {
                    "German" -> Locale("de")
                    "Spanish" -> Locale("es")
                    "Russian" -> Locale("ru")
                    else -> Locale.getDefault()
                }
                val config = Configuration(resources.configuration)
                config.setLocale(locale)
                resources.updateConfiguration(config, resources.displayMetrics)

                textView.text = resources.getString(R.string.textView)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }

    override fun onStart() {
        super.onStart()
        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val locale = when (languages[position]) {
                    "German" -> Locale("de")
                    "Spanish" -> Locale("es")
                    "Russian" -> Locale("ru")
                    else -> Locale.getDefault()
                }
                val config = Configuration(resources.configuration)
                config.setLocale(locale)
                resources.updateConfiguration(config, resources.displayMetrics)

                textView.text = resources.getString(R.string.textView)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}