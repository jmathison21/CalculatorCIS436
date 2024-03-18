package com.example.calculator_cis436

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculator_cis436.databinding.FragmentButtonsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [Buttons.newInstance] factory method to
 * create an instance of this fragment.
 */
class Buttons : Fragment() {
    private lateinit var binding: FragmentButtonsBinding

    private lateinit var activityCallback: ButtonListener

    interface ButtonListener {
        fun onButtonClicked(character: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentButtonsBinding.inflate(inflater, container, false)

        //inner button listeners
        binding.button0.setOnClickListener {activityCallback.onButtonClicked("0")}
        binding.button1.setOnClickListener {activityCallback.onButtonClicked("1")}
        binding.button2.setOnClickListener {activityCallback.onButtonClicked("2")}
        binding.button3.setOnClickListener {activityCallback.onButtonClicked("3")}
        binding.button4.setOnClickListener {activityCallback.onButtonClicked("4")}
        binding.button5.setOnClickListener {activityCallback.onButtonClicked("5")}
        binding.button6.setOnClickListener {activityCallback.onButtonClicked("6")}
        binding.button7.setOnClickListener {activityCallback.onButtonClicked("7")}
        binding.button8.setOnClickListener {activityCallback.onButtonClicked("8")}
        binding.button9.setOnClickListener {activityCallback.onButtonClicked("9")}
        binding.buttonDot.setOnClickListener {activityCallback.onButtonClicked(".")}
        binding.buttonNegative.setOnClickListener {activityCallback.onButtonClicked("+/-")}

        //outer button listeners
        binding.buttonModulus.setOnClickListener {activityCallback.onButtonClicked("%")}
        binding.buttonSQRT.setOnClickListener {activityCallback.onButtonClicked("√")}
        binding.buttonCE.setOnClickListener {activityCallback.onButtonClicked("CE")}
        binding.buttonClear.setOnClickListener {activityCallback.onButtonClicked("C")}
        binding.buttonPlus.setOnClickListener {activityCallback.onButtonClicked("+")}
        binding.buttonMinus.setOnClickListener {activityCallback.onButtonClicked("-")}
        binding.buttonMultiply.setOnClickListener {activityCallback.onButtonClicked("*")}
        binding.buttonDivide.setOnClickListener {activityCallback.onButtonClicked("/")}
        binding.buttonEquals.setOnClickListener {activityCallback.onButtonClicked("=")}



        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            activityCallback = context as ButtonListener
        }
        catch(e : ClassCastException) {
            throw ClassCastException(context.toString() +
                    " must implement ButtonListener")
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(): Buttons {
            return Buttons()
        }
    }
}