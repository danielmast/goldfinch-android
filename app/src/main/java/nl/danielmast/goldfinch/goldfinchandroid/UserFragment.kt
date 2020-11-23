package nl.danielmast.goldfinch.goldfinchandroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import kotlinx.coroutines.*

class UserFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
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
                    view?.findViewById<TextView>(R.id.user_name_text)?.apply {
                        text = user.name
                    }

                    view?.findViewById<TextView>(R.id.user_gender_text)?.apply {
                        text = user.gender.toString()
                    }

                    view?.findViewById<TextView>(R.id.user_orientation_text)?.apply {
                        text = user.orientation.toString()
                    }

                    view?.findViewById<TextView>(R.id.user_text_text)?.apply {
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