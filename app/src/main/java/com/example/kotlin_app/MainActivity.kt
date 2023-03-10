package com.example.kotlin_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.kotlin_app.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchButton.setOnClickListener {
            val countryName = binding.searchEditText.text.toString()
            //is executed not in the main thread, but in a background thread, while waiting for a response, the main thread will not be blocked
            lifecycleScope.launch {
//                //suspend method for long
                try {
                    val countries = restCountriesApi.getCountryByName(countryName)
                    val country = countries[0]
                    binding.countryTextView.text = country.name
                    binding.capitalTextViewValue.text = country.capital
                    binding.populationTextViewValue.text = country.population.toString()
                    binding.squareTextViewValue.text = country.area.toString()
                    binding.languageTextViewValue.text = country.languages.toString()
                } catch (e: Exception) {
                    // Network or server error, error handling
                    Toast.makeText(this@MainActivity, "Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}