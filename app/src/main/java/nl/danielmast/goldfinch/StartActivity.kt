package nl.danielmast.goldfinch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        finish()

        if (getUsername() == null) {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        } else {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }

    private fun getUsername(): String? = PreferenceManager.getDefaultSharedPreferences(this)
        .getString(getString(R.string.username_key), null)
}