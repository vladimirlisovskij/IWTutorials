package com.example.task6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment

import com.bumptech.glide.Glide

class Dialog : DialogFragment() {
    private lateinit var avatar: ImageView
    private lateinit var exitBut: Button
    private lateinit var deleteBut: Button
    private lateinit var showBut: Button

    var showListener: ( (View) -> Unit )? = null
    var deleteListener: ( (View) -> Unit)? = null
    var imgHref: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_dialog, null)
        avatar = v.findViewById(R.id.dialogAvatar)
        deleteBut = v.findViewById(R.id.dialogDeleteBut)
        exitBut = v.findViewById(R.id.dialogExitBut)
        showBut = v.findViewById(R.id.dialogProfileBut)
        exitBut.setOnClickListener { _: View? -> dismiss() }
        deleteBut.setOnClickListener(deleteListener)
        showBut.setOnClickListener(showListener)
        Glide.with(avatar.context)
                .load(imgHref)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(avatar)
        return v
    }
}