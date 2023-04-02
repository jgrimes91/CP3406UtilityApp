package au.edu.jcu.utilityapp.eyecare

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import au.edu.jcu.utilityapp.eyecare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var eyeTimer: CountDownTimer



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.startBtn.setOnClickListener { startTimer() }
        binding.stopBtn.setOnClickListener { stopTimer() }
        binding.settingsBtn.setOnClickListener { changeSettings() }


    }


    private fun stopTimer() {
        val blankTime = "00:00"
        binding.timerTv.text = blankTime
        eyeTimer.cancel()
    }

    private fun startTimer() {
        intervalTimer()
    }

    private fun intervalTimer() {
        val timerLength: Long = 20 * 60_000
        eyeTimer = object : CountDownTimer(timerLength, 1000) {
            override fun onTick(msToFinised: Long) {
                val minutes = msToFinised / 1000 / 60
                val seconds = msToFinised / 1000 % 60
                displayTimer(minutes, seconds)
            }

            override fun onFinish() {
                eyeTimer.cancel()
                // Add a snackbar?
            }
        }
        eyeTimer.start()
    }


    private fun displayTimer(minutes: Long, seconds: Long) {
        val formattedTime = "%02d:%02d".format(minutes, seconds)
        binding.timerTv.text = formattedTime

    }

    /**
     * Move to SettingsActivity to change settings.
     */
    private fun changeSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }
}