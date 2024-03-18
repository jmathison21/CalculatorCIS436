package com.example.calculator_cis436

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), Buttons.ButtonListener {
    private val operationsList = listOf("+", "-", "*", "/", "%", "√")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onButtonClicked(character: String) {
        val display = supportFragmentManager.findFragmentById(R.id.displayFragment) as Display

        if (character == "CE") {
            display.clearEntity()
            display.displayOperation()
        } else if (character == "C") {
            display.clearAll()
            display.displayOperation()
        } else if (character == "=") {
            val result = display.calculate()
            if (result) {
                display.displayResult()
                display.clearAll()
            }
        } else {
            val isOperation = character in operationsList
            display.addCharacter(character, isOperation)
            display.displayOperation()
        }
    }

}