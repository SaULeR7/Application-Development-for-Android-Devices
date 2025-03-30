package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity()  {
    private lateinit var screen : TextView
    private lateinit var btnC: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btn0: Button
    private lateinit var btnComma: Button
    private lateinit var btnNeg: Button
    private lateinit var btnAdd: Button
    private lateinit var btnSub: Button
    private lateinit var btnDiv: Button
    private lateinit var btnMulti: Button
    private lateinit var btnE: Button
    private lateinit var currentText: String
    private lateinit var opBtn: String
    private var firstValue: Double = 0.0
    private var secondValue: Double = 0.0

    private fun Double.isWhole(): Boolean {
        return this % 1 == 0.0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        screen = findViewById(R.id.display_tv)
        btnC = findViewById(R.id.btn_c)
        btnC.setOnClickListener {
            firstValue = 0.0
            secondValue = 0.0
            currentText = screen.text.toString()
            if (currentText.isNotEmpty() && currentText != "0") {
                screen.text = "0"
            }
        }
        btn1 = findViewById(R.id.btn_one)
        btn1.setOnClickListener {
            currentText = screen.text.toString()
            if (currentText == "0") {
                screen.text = "1"
            } else {
                screen.append("1")
            }
        }
        btn2 = findViewById(R.id.btn_two)
        btn2.setOnClickListener {
            currentText = screen.text.toString()
            if (currentText == "0") {
                screen.text = "2"
            } else {
                screen.append("2")
            }
        }
        btn3 = findViewById(R.id.btn_three)
        btn3.setOnClickListener {
            currentText = screen.text.toString()
            if (currentText == "0") {
                screen.text = "3"
            } else {
                screen.append("3")
            }
        }
        btn4 = findViewById(R.id.btn_four)
        btn4.setOnClickListener {
            currentText = screen.text.toString()
            if (currentText == "0") {
                screen.text = "4"
            } else {
                screen.append("4")
            }
        }
        btn5 = findViewById(R.id.btn_five)
        btn5.setOnClickListener {
            currentText = screen.text.toString()
            if (currentText == "0") {
                screen.text = "5"
            } else {
                screen.append("5")
            }
        }
        btn6 = findViewById(R.id.btn_six)
        btn6.setOnClickListener {
            currentText = screen.text.toString()
            if (currentText == "0") {
                screen.text = "6"
            } else {
                screen.append("6")
            }
        }
        btn7 = findViewById(R.id.btn_seven)
        btn7.setOnClickListener {
            currentText = screen.text.toString()
            if (currentText == "0") {
                screen.text = "7"
            } else {
                screen.append("7")
            }
        }
        btn8 = findViewById(R.id.btn_eight)
        btn8.setOnClickListener {
            currentText = screen.text.toString()
            if (currentText == "0") {
                screen.text = "8"
            } else {
                screen.append("8")
            }
        }
        btn9 = findViewById(R.id.btn_nine)
        btn9.setOnClickListener {
            currentText = screen.text.toString()
            if (currentText == "0") {
                screen.text = "9"
            } else {
                screen.append("9")
            }
        }
        btn0 = findViewById(R.id.btn_zero)
        btn0.setOnClickListener {
            currentText = screen.text.toString()
            if (currentText == "0") {
                screen.text = "0"
            } else {
                screen.append("0")
            }
        }
        btnComma = findViewById(R.id.btn_comma)
        btnComma.setOnClickListener {
            currentText = screen.text.toString()
            if (!currentText.contains(".")) {
                screen.append(".")
            }
        }
        btnNeg = findViewById(R.id.btn_neg)
        btnNeg.setOnClickListener {
            currentText = screen.text.toString()
            if (currentText.isNotEmpty() && currentText != "0") {
                val currentValue = currentText.toDouble()
                val toggledValue = -currentValue
                val result = if (toggledValue.isWhole()) toggledValue.toLong() else toggledValue
                screen.text = result.toString()
            }
        }
        btnAdd = findViewById(R.id.btn_add)
        btnAdd.setOnClickListener {
            firstValue = screen.text.toString().toDouble()
            opBtn = "+"
            screen.text = "0"
        }
        btnSub = findViewById(R.id.btn_sub)
        btnSub.setOnClickListener {
            firstValue = screen.text.toString().toDouble()
            opBtn = "-"
            screen.text = "0"
        }
        btnDiv = findViewById(R.id.btn_div)
        btnDiv.setOnClickListener {
            firstValue = screen.text.toString().toDouble()
            opBtn = "/"
            screen.text = "0"
        }
        btnMulti = findViewById(R.id.btn_multp)
        btnMulti.setOnClickListener {
            firstValue = screen.text.toString().toDouble()
            opBtn = "*"
            screen.text = "0"
        }
        btnE = findViewById(R.id.btn_equal)
        btnE.setOnClickListener {
            secondValue = screen.text.toString().toDouble()
            when (opBtn) {
                "+" -> {
                    val calc = firstValue + secondValue
                    val result1 = if (calc.isWhole()) calc.toLong() else calc
                    screen.text = result1.toString()
                    opBtn = ""
                }
                "-" -> {
                    val calc = firstValue - secondValue
                    val result = if (calc.isWhole()) calc.toLong() else calc
                    screen.text = result.toString()
                    opBtn = ""
                }
                "/" -> {
                    if (secondValue != 0.0) {
                        val calc = firstValue / secondValue
                        val result = if (calc.isWhole()) calc.toLong() else calc
                        screen.text = result.toString()
                    } else {
                        screen.text = "Error"
                    }
                    opBtn = ""
                }
                "*" -> {
                    val calc = firstValue * secondValue
                    val result = if (calc.isWhole()) calc.toLong() else calc
                    screen.text = result.toString()
                    opBtn = ""
                }
            }
        }
    }
}
