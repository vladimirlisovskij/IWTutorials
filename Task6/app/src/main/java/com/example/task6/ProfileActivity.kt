package com.example.task6

import android.os.Bundle
import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ProfileActivity : AppCompatActivity() {
    private var avatarIV: ImageView? = null
    private var nameTV: TextView? = null
    private var phoneTV: TextView? = null
    private var emailTV: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        avatarIV = findViewById(R.id.profileAvatarTV)
        nameTV = findViewById(R.id.profileNameTV)
        phoneTV = findViewById(R.id.profilePhoneTV)
        emailTV = findViewById(R.id.profileEmailTV)
        val intent = intent
        val data: Container = intent.getParcelableExtra<Parcelable>(MainActivity.FORM_KEY) as Container
        Glide.with(avatarIV!!.context)
                .load(data.avatarHref)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(avatarIV!!)
        nameTV!!.text = data.name
        phoneTV!!.text = data.phone
        emailTV!!.text = data.email
    }
}