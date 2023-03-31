package au.edu.jcu.utilityapp.eyecare

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import au.edu.jcu.utilityapp.eyecare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.settingsBtn.setOnClickListener { changeSettings() }
    }

    private fun changeSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
}