package nl.danielmast.goldfinch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.preference.PreferenceManager

class StartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_start, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (getUsername() == null) {
            view.findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        } else {
            view.findNavController().navigate(R.id.action_startFragment_to_mainFragment)
        }
    }

    private fun getUsername(): String? = PreferenceManager.getDefaultSharedPreferences(activity)
        .getString(getString(R.string.username_key), null)
}