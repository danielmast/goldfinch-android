package nl.danielmast.goldfinch.goldfinchandroid

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*

class UserFragment(val userId: Int): Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadUser(userId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    private fun loadUser(userId: Int) {
        val service = RETROFIT.create(APIService::class.java)
        val job = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + job)

        uiScope.launch {
            val response = service.getUsers()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val user = response.body()!![userId]
                    view?.findViewById<TextView>(R.id.user_name)?.apply {
                        text = user.name
                    }

                    view?.findViewById<TextView>(R.id.user_gender)?.apply {
                        text = user.gender.toString()
                    }

                    view?.findViewById<TextView>(R.id.user_orientation)?.apply {
                        text = user.orientation.toString()
                    }

                    view?.findViewById<TextView>(R.id.user_text)?.apply {
                        text = user.text
                    }
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
    }
}