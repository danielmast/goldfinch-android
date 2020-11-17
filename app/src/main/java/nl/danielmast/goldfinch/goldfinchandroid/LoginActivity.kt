package nl.danielmast.goldfinch.goldfinchandroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)

        findViewById<Button>(R.id.login_button).setOnClickListener {
            with(sharedPref.edit()) {
                val username = findViewById<EditText>(R.id.edittext_username).text.toString()
                putString(getString(R.string.username_key), username)
                apply()
                finish()
                applicationContext.startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        }
    }
}