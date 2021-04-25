package com.example.task9

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity() {

    private lateinit var tempTV: TextView
    private lateinit var humTV: TextView
    private lateinit var wMain: TextView
    private lateinit var wDesc: TextView
    private lateinit var windTV: TextView
    private lateinit var ssetTV: TextView
    private lateinit var sriseTV: TextView

    private val scope: CoroutineScope = CoroutineScope(SupervisorJob())
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        runOnUiThread(
                Runnable {
                    Toast.makeText(this@MainActivity, throwable.localizedMessage?:"BUG", Toast.LENGTH_SHORT).show()
                }
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempTV = findViewById(R.id.tempTV)
        humTV = findViewById(R.id.humTV)
        wMain = findViewById(R.id.wMainTV)
        wDesc = findViewById(R.id.wDescTV)
        windTV = findViewById(R.id.windTV)
        ssetTV = findViewById(R.id.ssetTV)
        sriseTV = findViewById(R.id.sriseTV)

        val job = scope.launch(Dispatchers.IO + exceptionHandler) {
            val mes = getServerApi().getMessage();
            if (mes.isSuccessful) {
                withContext(Dispatchers.Main) {
                    val jDoc = mes.body();
                    jDoc?.let {
                        tempTV.text = "temp: " + jDoc.main?.temp.toString()
                        humTV.text = "humidity: " + jDoc.main?.humidity.toString()

                        wMain.text = "weather: " + jDoc.weather?.elementAt(0)?.main;
                        wDesc.text = "weather desc: " + jDoc.weather?.elementAt(0)?.description;

                        windTV.text = "wind speed: " + jDoc.wind?.speed.toString()

                        val sdf = SimpleDateFormat("h:mm a", Locale.ENGLISH)
                        sdf.timeZone = TimeZone.getTimeZone("UTC")

                        ssetTV.text = "sunset: " + jDoc.sys?.sunset?.let {
                            sdf.format(Date(it * 1000))
                        }

                        sriseTV.text = "sunrise: " + jDoc.sys?.sunrise?.let {
                            sdf.format(Date(it * 1000))
                        }
                    }
                }
            }
        }
    }

    private fun getServerApi(): ServerApi {
        return getRetrofit().create(ServerApi::class.java)
    }

    private fun getRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(ServerApi.Urls.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}