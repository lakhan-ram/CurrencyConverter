package com.example.currencyconverter.view.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.currencyconverter.R
import com.example.currencyconverter.viewmodel.CurrencyRateViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var spinnerFromUnit: Spinner
    private lateinit var spinnerToUnit: Spinner
    private lateinit var editTextNumberFrom: EditText
    private lateinit var editTextNumberTo: EditText
    private lateinit var btnConvert: Button
    private lateinit var viewModel: CurrencyRateViewModel

    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        spinnerFromUnit = findViewById(R.id.spinnerFromUnit)
        spinnerToUnit = findViewById(R.id.spinnerToUnit)
        editTextNumberFrom = findViewById(R.id.editTextNumberFrom)
        editTextNumberTo = findViewById(R.id.editTextNumberTo)
        btnConvert = findViewById(R.id.btnConvert)


        viewModel = CurrencyRateViewModel()

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, viewModel.supportedRates)
        spinnerFromUnit.adapter = adapter
        spinnerToUnit.adapter = adapter

        viewModel.exchangeRate.observe(this) { exchangeRate ->
            exchangeRate?.let {
                val rates = it.rates
                val targetCurrency = spinnerToUnit.selectedItem.toString()
                rates[targetCurrency]?.let { targetRate ->
                    val amount = editTextNumberFrom.text.toString().toDoubleOrNull()
                    if (amount != null) {
                        val convertedAmount = amount * targetRate
                        val roundedAmount = String.format("%.2f", convertedAmount)
                        editTextNumberTo.setText(roundedAmount)
                    }
                }
            }
        }

        btnConvert.setOnClickListener {
            val baseCurrency = spinnerFromUnit.selectedItem.toString()
            viewModel.getExchangeRate(baseCurrency)
        }
    }
}