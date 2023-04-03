package au.edu.jcu.utilityapp.eyecare

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import au.edu.jcu.utilityapp.eyecare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var timer: TimerManager = TimerManager(1_200_000) // Default value, 20 minutes to ms
    var breakTimer: TimerManager = TimerManager(20_000) // Default value, 20 seconds to ms

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            timer.timerTick()
            updateDisplay()
            // How to go to break timer?
        }
        binding.stopBtn.setOnClickListener {
            timer.stopTimer()
        }
        binding.settingsBtn.setOnClickListener { changeSettings() }
    }

    private fun updateDisplay() {
        // polling - handler
        // look at lifecycles
        // look at prac 6


    }

    /**
     * Move to SettingsActivity to change settings.
     */
    private fun changeSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
}