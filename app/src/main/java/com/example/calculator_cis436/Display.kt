package com.example.calculator_cis436

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculator_cis436.databinding.FragmentDisplayBinding
import kotlin.math.sqrt

/**
 * A simple [Fragment] subclass.
 * Use the [Display.newInstance] factory method to
 * create an instance of this fragment.
 */
class Display : Fragment() {
    private lateinit var binding: FragmentDisplayBinding

    private var num1: String = ""
    private var num2: String = ""
    private var operation: String = ""
    private var result: Double? = null
    private var last: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDisplayBinding.inflate(inflater, container, false)

        this.displayOperation()

        return binding.root
    }

    //display current sequence of operations and numbers
    fun displayOperation() {
        var textString= ""

        if(operation == "√") {
            textString += "√"
        }

        if(this.num1 != "") textString += num1

        if(operation != "" && operation != "√") {
            textString += " $operation "
            if(this.num2 != "") textString += num2
        }

        binding.displayText.text = textString
    }

    //display result
    fun displayResult() {
        var textString= ""
        if(result != null) {
            textString = result.toString()
            if(result!!.compareTo(result!!.toInt()) == 0) textString = textString.substring(0, textString.length - 2)
        }
        binding.displayText.text = textString
    }

    // display passed in message - unused/for testing purposes only
    fun displayMessage(message: String) {
        binding.displayText.text = message
    }


    //calculate result
    fun calculate(): Boolean {
        val num1Val = num1.toDoubleOrNull()
        num1Val ?: return false
        val num2Val = num2.toDoubleOrNull()
        num2Val ?: return false

        var success = true
        when(operation) {
            "+" -> {result = num1Val + num2Val}
            "-" -> {result = num1Val - num2Val}
            "*" -> {result = num1Val * num2Val}
            "/" -> {if(num2Val == 0.0) success = false else result = num1Val / num2Val}
            "%" -> {result = num1Val % num2Val}
            "√" -> {result = sqrt(num1Val)}
            else -> {
                success = false
            }
        }
        return success
    }


    //clear all
    fun clearAll() {
        this.num1 = ""
        this.num2 = ""
        this.operation = ""
        this.result = null
        this.last = ""
    }

    //clear last
    fun clearEntity() {
        when(last) {
            "num1" -> {num1 = num1.substring(0, num1.length - 1)}
            "num2" -> {num2 = num2.substring(0, num2.length - 1)}
            "operation" -> {operation = ""}
            "sqrt" -> {operation = ""; num2 = ""}
        }
        last = ""
    }


    //add to operation
    fun addCharacter(character: String, isOperation: Boolean) {
        if(isOperation) { //add to operation
            if(operation == "" || num2 == "") {
                if(character == "√") {
                    operation = character
                    last = "sqrt"
                    num2 = "0"
                    return
                }
                if(num1 == "") return
                operation = character
                last = "operation"
            }
            return
        }

        if(operation == "" || operation == "√") { //add to num1
            //+/-
            if(character == "+/-") {
                num1 = if(num1 == "" || num1.substring(0, 1) != "-") "-$num1" else num1.substring(1)
                last = ""
                return
            }
            //number or dot
            num1 += character
            last = "num1"
        } else  { //add to num2
            //+/-
            if(character == "+/-") {
                num2 = if(num2 == "" || num2.substring(0, 1) != "-") "-$num2" else num2.substring(1)
                last = ""
                return
            }
            //number or dot
            num2 += character
            last = "num2"
        }
        return
    }

    companion object {
        @JvmStatic
        fun newInstance(): Display {
            return Display()
        }
    }
}