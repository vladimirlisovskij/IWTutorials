package com.example.task6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>(){
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        private val avatarIV: ImageView = itemView.findViewById(R.id.avatarTV)
        private val nameTV: TextView = itemView.findViewById(R.id.nameTV)
        private val phoneTV: TextView = itemView.findViewById(R.id.phoneTV)
        private val emailTV: TextView = itemView.findViewById(R.id.emailTV)

        fun setData(data: Container) {
            Glide.with(avatarIV.context)
                    .load(data.imageLink)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(avatarIV)
            nameTV.text = data.name
            phoneTV.text = data.phone
            emailTV.text = data.email
        }

        init {
            itemView.setOnClickListener { _ ->
                containerList?.let {
                    onItemClick?.invoke(it[adapterPosition], adapterPosition)
                }
            }
        }
    }

    var containerList: ArrayList<Container>? = null
        set (dataFormContainerList: ArrayList<Container>?) {
            notifyDataSetChanged()
            field = dataFormContainerList
        }

    var onItemClick: ( (dataFormContainer: Container, index: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        containerList?.let {
            holder.setData(it[position])
        }
    }

    override fun getItemCount(): Int {
        return containerList?.size ?: 0
    }

    fun deleteItem(index: Int): Boolean {
        return if (containerList == null || containerList!!.size <= index || index < 0) {
            false
        } else {
            containerList!!.removeAt(index)
            notifyItemRemoved(index)
            notifyItemRangeChanged(index, containerList!!.size)
            true
        }
    }
}