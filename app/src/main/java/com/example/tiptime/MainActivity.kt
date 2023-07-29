package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip(){
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        //chi phi
        val cost = stringInTextField.toDoubleOrNull()
        if(cost == null){
            binding.tipResult.text = ""
            return
        }
        val selectedId = binding.tipOptions.checkedRadioButtonId   //check xem Radiobutton nao dc chon
        // tien tip
        val tipPercent = when(selectedId){
            R.id.options_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip =  tipPercent * cost
        val roundUp = binding.roundUpSwitch.isChecked

        //check xem nut switch lam tron co dc bam hay ko
        if(roundUp){
            tip = ceil(tip)
        }
        //dinh dang so duoi dang don vi tien te
       // NumberFormat.getCurrencyInstance()
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
    binding.tipResult.text = getString(R.string.tip_amount,formattedTip)

    }
}