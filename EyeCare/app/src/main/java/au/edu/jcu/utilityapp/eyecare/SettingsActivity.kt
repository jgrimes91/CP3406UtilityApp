package au.edu.jcu.utilityapp.eyecare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import au.edu.jcu.utilityapp.eyecare.databinding.ActivityMainBinding

class SettingsActivity : AppCompatActivity() {

    var intervalTimer: EyeTimer = EyeTimer()
    var breakTimer: EyeTimer = EyeTimer()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val saveButton:Button = findViewById(R.id.save_btn)
        saveButton.setOnClickListener {saveSettings()
        }
    }



    /**
     * Saves any changes to preferences made in settings menu and returns to MainActivity.
     */
    private fun saveSettings() {



        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}