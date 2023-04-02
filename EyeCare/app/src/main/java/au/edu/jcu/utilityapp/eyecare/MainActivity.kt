package au.edu.jcu.utilityapp.eyecare

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import au.edu.jcu.utilityapp.eyecare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var isRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {  }
        binding.cancelBtn.setOnClickListener { }
        binding.resetBtn.setOnClickListener {  }
        binding.settingsBtn.setOnClickListener { changeSettings() }

    }



    /**
     * Move to SettingsActivity to change settings.
     */
    private fun changeSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
}