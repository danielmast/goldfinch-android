package nl.danielmast.goldfinch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*
import nl.danielmast.goldfinch.databinding.FragmentExploreBinding

class ExploreFragment(val userId: Int): Fragment() {

    private lateinit var binding: FragmentExploreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadUser(userId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root.apply {

            binding.likeButton.setOnClickListener {
                loadUser((1..3).random())
            }

            binding.dontLikeButton.setOnClickListener {
                loadUser((1..3).random())
            }
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
}