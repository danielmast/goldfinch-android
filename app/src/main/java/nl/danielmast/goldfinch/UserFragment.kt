package nl.danielmast.goldfinch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import kotlinx.coroutines.*
import nl.danielmast.goldfinch.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun loadUser() {
        val service = RETROFIT.create(APIService::class.java)
        val job = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + job)

        uiScope.launch {
            val response = service.getUserByUsername(getUsername()!!)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val user = response.body()!!
                    binding.userNameText.apply {
                        text = user.name
                    }

                    binding.userGenderText.apply {
                        text = user.gender.toString()
                    }

                    binding.userOrientationText.apply {
                        text = user.orientation.toString()
                    }

                    binding.userTextText.apply {
                        text = user.text
                    }
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
    }

    private fun getUsername(): String? = PreferenceManager.getDefaultSharedPreferences(activity)
        .getString(getString(R.string.username_key), null)
}