package com.example.lab_ui_calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.get
import com.example.lab_ui_calc.databinding.ActivityMainBinding
import androidx.lifecycle.ViewModelProvider

private lateinit var binding: ActivityMainBinding
private lateinit var resetBtn: Button
private lateinit var equalsBtn: Button
private lateinit var firstNum: TextView
private lateinit var secondNum: TextView
private lateinit var answerTV: TextView
private lateinit var rg : RadioGroup

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firstNum = binding.firstNum
        secondNum = binding.secondNum
        resetBtn = binding.resetBtn
        equalsBtn = binding.equalsBtn
        answerTV = binding.answerTv
        rg = binding.rg
        answerTV.text = viewModel.answer.toString()
        resetBtn.setOnClickListener{
            firstNum.text = ""
            secondNum.text = ""
            viewModel.answer = 0.0
            answerTV.text = viewModel.answer.toString()
        }
        equalsBtn.setOnClickListener {
            val selectedId = rg.checkedRadioButtonId
            val fNum = firstNum.text.toString().toDouble()
            val sNum = secondNum.text.toString().toDouble()

            if(selectedId == binding.addRb.id){
                viewModel.answer = fNum + sNum
                answerTV.text = viewModel.answer.toString()
            }
            else if(selectedId == binding.subRb.id){
                viewModel.answer = fNum - sNum
                answerTV.text = viewModel.answer.toString()
            }
            else if(selectedId == binding.multpRb.id){
                viewModel.answer = fNum * sNum
                answerTV.text = viewModel.answer.toString()
            }
            else if(selectedId == binding.divRb.id){
                if(sNum != 0.0) {
                    viewModel.answer = fNum / sNum
                    answerTV.text = viewModel.answer.toString()
                }
                else {
                    answerTV.text = "Error"
                }
            }
        }
    }
}