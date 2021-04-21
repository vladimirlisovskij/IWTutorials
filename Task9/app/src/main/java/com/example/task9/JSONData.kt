package com.example.task9

import com.google.gson.annotations.SerializedName

class JSONData {
    inner class Weather {
        val main: String? = null
        val description: String? = null
    }
    inner class Wind {
        val speed: Double? = null
    }
    inner class Main {
        var temp: Double? = null
        var humidity: Long? = null
    }
    inner class Sys {
        val sunrise: Long? = null
        val sunset: Long? = null
    }

    val weather: List<Weather>? = null
    val wind: Wind? = null
    var sys: Sys? = null
    val main: Main? = null
}