package nl.danielmast.goldfinch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*

class ExploreFragment(val userId: Int): Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadUser(userId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_explore, container, false).apply {
        findViewById<Button>(R.id.like_button).setOnClickListener {
            loadUser((1..3).random())
        }

        findViewById<Button>(R.id.dont_like_button).setOnClickListener {
            loadUser((1..3).random())
        }
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
}