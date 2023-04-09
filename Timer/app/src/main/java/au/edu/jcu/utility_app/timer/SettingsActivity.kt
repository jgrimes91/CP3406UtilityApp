package au.edu.jcu.utility_app.timer

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import au.edu.jcu.utility_app.timer.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener { saveSettings() }


        binding.setTimeEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }
        binding.setBreakEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }
    }


    /**
     * Saves changes and goes back to MainActivity.
     */
    private fun saveSettings() {

        val stringInSetTime = binding.setTimeEditText.text.toString()
        val newTime = stringInSetTime.toLongOrNull()

        val stringInBreakTime = binding.setBreakEditText.text.toString()
        val newBreakTime = stringInBreakTime.toLongOrNull()

        var sound = false
        if (binding.playSound.isChecked) {
            sound = true
        }

        if (newTime == null || newTime == 0L || newBreakTime == null || newBreakTime == 0L) {
            setErrorTextField(true)
        } else {
            setErrorTextField(false)
            val intent = Intent()
            intent.putExtra("newTime", newTime)
            intent.putExtra("newBreak", newBreakTime)
            intent.putExtra("sound", sound)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    /**
     * Sets and resets the text field error status.
     */
    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.workTimeContainer.isErrorEnabled = true
            binding.workTimeContainer.error = "Invalid time"
            binding.breakTimeContainer.isErrorEnabled = true
            binding.breakTimeContainer.error = "Invalid time"
        } else {
            binding.workTimeContainer.isErrorEnabled = false
            binding.setTimeEditText.text = null
            binding.breakTimeContainer.isErrorEnabled = false
            binding.setBreakEditText.text = null
        }
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide keyboard
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false

    }
}