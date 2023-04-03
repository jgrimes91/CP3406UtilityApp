package au.edu.jcu.utilityapp.eyecare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import au.edu.jcu.utilityapp.eyecare.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener { saveSettings() }

    }

    private fun saveSettings() {
//        val intervalText = binding.intervalInput.text.toString()
//        val newIntervalTime = intervalText.toLong() * 60_000        // Converts minutes to ms
//
//        val breakText = binding.breakInput.text.toString()
//        val newBreakTime = breakText.toLong() * 1_000               // Converts seconds to ms

        // Pass objects back to the main activity

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
