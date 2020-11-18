package nl.danielmast.goldfinch.goldfinchandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
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
    .baseUrl("http://<HOST>:8080/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

class MainActivity : FragmentActivity() {
    private lateinit var tabLayout: TabLayout
    protected lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.pager)
        viewPager.adapter = CollectionAdapter(this)

        tabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(position) {
                0 -> getString(R.string.tab_explore)
                1 -> getString(R.string.tab_matches)
                2 -> getString(R.string.tab_profile)
                else -> TODO()
            }
        }.attach()
    }
}

class CollectionAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> ExploreFragment((1..3).random())
        1 -> MatchesFragment()
        2 -> UserFragment()
        else -> TODO()
    }
}