package nl.danielmast.goldfinch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login, container, false).apply {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
        findViewById<Button>(R.id.login_button).setOnClickListener {
            with(sharedPref.edit()) {
                val username = findViewById<EditText>(R.id.username_edittext).text.toString()
                putString(getString(R.string.username_key), username)
                apply()
                it.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }
        }
    }
}