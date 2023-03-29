package au.edu.jcu.utilityapp.eyecare

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                println(it.data?.getStringExtra("value"))
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val saveButton:Button = findViewById(R.id.save_btn)
        saveButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            launcher.launch(intent)
        }
    }
}