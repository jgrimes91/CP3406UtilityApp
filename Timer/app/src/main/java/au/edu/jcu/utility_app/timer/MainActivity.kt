package au.edu.jcu.utility_app.timer

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import au.edu.jcu.utility_app.timer.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var timer: CountDownTimer
    private lateinit var mediaPlayer: MediaPlayer

    // Default values
    private var startTime: Long = 1_200_000
    private var breakTime: Long = 20_000

    // Countdown timer values
    private var timeRemaining: Long = startTime
    private val msInterval: Long = 1_000

    // Timer state
    private var isRunning = false
    private var currentState = 0

    private var playSound = true

    private var activityLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            Log.d(ContentValues.TAG, "onActivityResult: created!")
            if (result.resultCode == Activity.RESULT_OK) {
                val intent: Intent? = result.data

                if (intent != null) {
                    // New work and break times entered by user
                    val newTime = intent.getLongExtra(
                        "newTime",
                        1_200_00
                    ) * 60_000       // Convert minutes to ms
                    val newBreak = intent.getLongExtra(
                        "newBreak",
                        20_000
                    ) * 1_000        // Convert seconds to ms
                    startTime = newTime
                    timeRemaining = newTime
                    breakTime = newBreak

                    // Sound
                    val sound = intent.getBooleanExtra("sound", true)
                    playSound = sound
                    updateDisplay()
                }

                // Update UI
                val startText = "Start"
                binding.startBtn.text = startText
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener { onStartTimer() }
        binding.resetBtn.setOnClickListener { resetTimer() }
        binding.settingsBtn.setOnClickListener { goToSettingsActivity() }
        binding.resetBtn.isEnabled = false

        updateDisplay()
    }

    /**
     * Navigate to the settings activity.
     */
    private fun goToSettingsActivity() {
        val intent = Intent(this, SettingsActivity::class.java)
        intent.putExtra("isRunning", isRunning)
        activityLauncher.launch(intent)
    }

    /**
     * Reset the timer and redisplay the text.
     */
    private fun resetTimer() {
        timeRemaining = startTime
        updateDisplay()
        resetInterface()
    }
    
    /**
     * Check the timer status, and the choose action.
     */
    private fun onStartTimer() {
        if (isRunning) {
            pauseTimer()
        } else {
            startTimer()
        }
    }

    /**
     * Pause the timer.
     */
    private fun pauseTimer() {
        timer.cancel()
        isRunning = false
        updateDisplay()

        // Update UI
        val resumeText = "Resume"
        binding.startBtn.text = resumeText
        binding.resetBtn.isEnabled = true
        binding.settingsBtn.visibility = View.VISIBLE
    }

    /**
     * Start the timer.
     */
    private fun startTimer() {
        timer = object : CountDownTimer(timeRemaining, msInterval) {
            override fun onTick(msRemaining: Long) {
                timeRemaining = msRemaining
                updateDisplay()
            }

            override fun onFinish() {
                isRunning = false

                if (playSound) {
                    playSound()
                }

                displayTimerCompleteDialog()
            }
        }.start()

        isRunning = true

        // Update UI
        val pauseText = "Pause"
        binding.startBtn.text = pauseText
        binding.resetBtn.isEnabled = false
        binding.settingsBtn.visibility = View.INVISIBLE
    }

    /**
     * Initialise and play sound.
     */
    private fun playSound() {
        if (!this::mediaPlayer.isInitialized) {
            mediaPlayer = MediaPlayer.create(this, R.raw.soft_notification)
        }
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            mediaPlayer.seekTo(0)
        }
        mediaPlayer.start()
    }

    /**
     * Checks the current state and changes the timer length.
     */
    private fun nextTimer() {
        timer.cancel()

        if (currentState == 0) {
            timeRemaining = breakTime
            currentState = 1
        } else if (currentState == 1) {
            timeRemaining = startTime
            currentState = 0
        }
        updateDisplay()
        resetInterface()
    }

    /**
     * Reset user interface.
     */

    private fun resetInterface() {
        val startText = "Start"
        binding.startBtn.text = startText
        binding.resetBtn.isEnabled = false
        binding.settingsBtn.visibility = View.VISIBLE
    }

    /**
     * When timer is complete, prompts user if they would like to start timer again.
     */
    private fun displayTimerCompleteDialog() {
        if (currentState == 0) {
            MaterialAlertDialogBuilder(this)
                .setTitle("Timer complete!")
                .setMessage("Do you want to take a break?")
                .setCancelable(false)
                .setNegativeButton("Change time") { _, _ -> goToSettingsActivity() }
                .setPositiveButton("Take a break") { _, _ ->
                    nextTimer()
                }.show()
        } else {
            MaterialAlertDialogBuilder(this)
                .setTitle("Break complete!")
                .setMessage("Do you want to get back to work?")
                .setCancelable(false)
                .setNegativeButton("Change time") { _, _ -> goToSettingsActivity() }
                .setPositiveButton("Back to work") { _, _ ->
                    nextTimer()
                }.show()
        }
    }

    /**
     * Update the display.
     */
    private fun updateDisplay() {
        val minutes: Long = timeRemaining / 1000 / 60
        val seconds: Long = timeRemaining / 1000 % 60

        val timeFormatted: String = "%02d:%02d".format(minutes, seconds)
        binding.timerText.text = timeFormatted
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isRunning", isRunning)
        outState.putLong("timeRemaining", timeRemaining)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        isRunning = savedInstanceState.getBoolean("isRunning")
        timeRemaining = savedInstanceState.getLong("timeRemaining")

        if (isRunning) {
            startTimer()
        }
        updateDisplay()
    }

    override fun onDestroy() {
        if (this::mediaPlayer.isInitialized) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
        super.onDestroy()
    }
}