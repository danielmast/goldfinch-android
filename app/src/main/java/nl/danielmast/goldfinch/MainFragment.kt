package nl.danielmast.goldfinch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import nl.danielmast.goldfinch.databinding.FragmentMainBinding
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val okHttpClient = OkHttpClient.Builder()
    .connectTimeout(1, TimeUnit.MINUTES)
    .readTimeout(30, TimeUnit.SECONDS)
    .writeTimeout(15, TimeUnit.SECONDS)
    .build()

val RETROFIT: Retrofit = Retrofit.Builder()
    .baseUrl("http://10.0.2.2:8080/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.pager.adapter = CollectionAdapter(this@MainFragment)

        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = when(position) {
                0 -> getString(R.string.tab_explore)
                1 -> getString(R.string.tab_matches)
                2 -> getString(R.string.tab_profile)
                else -> TODO()
            }
        }.attach()
    }
}

class CollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> ExploreFragment((1..3).random())
        1 -> MatchesFragment()
        2 -> UserFragment()
        else -> TODO()
    }
}