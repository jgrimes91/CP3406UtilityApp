package au.edu.jcu.utilityapp.eyecare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                println(it.data?.getStringExtra("value"))
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.timer_tv)

        val settingsButton: Button = findViewById(R.id.settings_btn)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            launcher.launch(intent)
        }
    }
}