package nl.danielmast.goldfinch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager
import nl.danielmast.goldfinch.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root.apply {
            val sharedPref = PreferenceManager.getDefaultSharedPreferences(activity)
            binding.loginButton.setOnClickListener {
                with(sharedPref.edit()) {
                    val username = binding.usernameEdittext.text.toString()
                    putString(getString(R.string.username_key), username)
                    apply()
                    it.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                }
            }
        }
    }
}