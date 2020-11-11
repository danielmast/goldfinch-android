package nl.danielmast.goldfinch.goldfinchandroid

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getUser("1")
    }

    private fun getUser(userId: String) {
        val service = RETROFIT.create(APIService::class.java)
        val job = Job()
        val uiScope = CoroutineScope(Dispatchers.Main + job)

        uiScope.launch {
            val response = service.getUser(userId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    findViewById<TextView>(R.id.user_name)?.apply {
                        text = response.body()?.name
                    }

                    findViewById<TextView>(R.id.user_gender)?.apply {
                        text = response.body()?.gender.toString()
                    }

                    findViewById<TextView>(R.id.user_orientation)?.apply {
                        text = response.body()?.orientation.toString()
                    }

                    findViewById<TextView>(R.id.user_text)?.apply {
                        text = response.body()?.text
                    }
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
    }
}