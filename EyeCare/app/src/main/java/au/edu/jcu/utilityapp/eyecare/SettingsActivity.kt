package au.edu.jcu.utilityapp.eyecare

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import au.edu.jcu.utilityapp.eyecare.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.saveBtn.setOnClickListener { saveSettings() }


        /**
         * Return back to MainActivity screen
         */


        /**
         * Helper method, if enter key is pressed then close the on-screen keyboard
         *
         * @param view View
         * @param keyCode Keycode
         */

        fun handleKeyEvent(view: View, keyCode: Int): Boolean {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                // Hide the keyboard
                val inputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                return true
            }
            return false
        }

    }

    fun saveSettings() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
