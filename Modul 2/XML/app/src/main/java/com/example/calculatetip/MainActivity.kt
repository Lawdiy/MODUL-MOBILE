package com.example.calculatetip

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.calculatetip.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedTipPercent: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayTip(0.0)

        setupDropdown()

        binding.billAmountEditText.doAfterTextChanged {
            calculateAndDisplayTip()
        }

        binding.roundUpSwitch.setOnCheckedChangeListener { _, _ ->
            calculateAndDisplayTip()
        }
    }

    private fun setupDropdown() {
        val options = listOf("15%", "18%", "20%")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, options)
        binding.tipOptionsAutoComplete.setAdapter(adapter)

        binding.tipOptionsAutoComplete.setOnItemClickListener { _, _, position, _ ->
            val selectedText = options[position]
            selectedTipPercent = selectedText.replace("%", "").toDoubleOrNull() ?: 0.0
            calculateAndDisplayTip()
        }
    }

    private fun calculateAndDisplayTip() {
        val amountText = binding.billAmountEditText.text.toString()
        val amount = amountText.toDoubleOrNull() ?: 0.0

        if (amount == 0.0 || selectedTipPercent == 0.0) {
            displayTip(0.0)
            return
        }

        var tip = (selectedTipPercent / 100) * amount

        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance(Locale.US).format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}