package nl.danielmast.goldfinch.goldfinchandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        finish()

        if (getUsername() == null) {
            applicationContext.startActivity(Intent(applicationContext, LoginActivity::class.java))
        } else {
            applicationContext.startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }

    private fun getUsername(): String? = PreferenceManager.getDefaultSharedPreferences(this)
        .getString(getString(R.string.username_key), null)
}