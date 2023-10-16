package com.example.kalkulatori

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kalkulatori.R

class MainActivity : AppCompatActivity() {

    private lateinit var inputTextView: TextView
    private lateinit var outputTextView: TextView

    private var currentInput = StringBuilder()
    private var currentOperator: Char? = null
    private var firstOperand: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputTextView = findViewById(R.id.input)
        outputTextView = findViewById(R.id.output)
    }

    fun onButtonClick(view: View) {
        if (view is Button) {
            val buttonText = view.text.toString()
            currentInput.append(buttonText)
            inputTextView.text = currentInput.toString()
        }
    }

    fun onOperatorClick(view: View) {
        if (view is Button) {
            if (currentInput.isNotEmpty()) {
                firstOperand = currentInput.toString().toDouble()
                currentInput.clear()
                currentOperator = view.text.toString().single()
            }
        }
    }

    fun onEqualsClick(view: View) {
        if (currentOperator != null && currentInput.isNotEmpty()) {
            val secondOperand = currentInput.toString().toDouble()
            val result = when (currentOperator) {
                '+' -> firstOperand!! + secondOperand
                '-' -> firstOperand!! - secondOperand
                '*' -> firstOperand!! * secondOperand
                '/' -> firstOperand!! / secondOperand
                else -> 0.0
            }
            outputTextView.text = result.toString()
            currentInput.clear()
            currentOperator = null
        }
    }

    

    fun onClearClick(view: View) {
        currentInput.clear()
        currentOperator = null
        firstOperand = null
        inputTextView.text = ""
        outputTextView.text = ""
    }
}
