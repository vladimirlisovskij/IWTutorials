package com.example.task9

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var tempTV: TextView
    private lateinit var humTV: TextView
    private lateinit var wMain: TextView
    private lateinit var wDesc: TextView
    private lateinit var windTV: TextView
    private lateinit var ssetTV: TextView
    private lateinit var sriseTV: TextView

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

        getServerApi().getMessage().enqueue(object : Callback<JSONData> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<JSONData>, response: Response<JSONData>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "OK", Toast.LENGTH_LONG).show()
                    val jDoc = response.body()
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

                } else {
                    Toast.makeText(this@MainActivity, response.errorBody().toString(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<JSONData>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getServerApi(): ServerApi {
        return getRetrofit().create(ServerApi::class.java)
    }

    private fun getRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
        client.connectTimeout(30, TimeUnit.SECONDS)
        client.writeTimeout(60, TimeUnit.SECONDS)
        client.readTimeout(60, TimeUnit.SECONDS)


        return Retrofit.Builder()
            .baseUrl(ServerApi.Urls.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}