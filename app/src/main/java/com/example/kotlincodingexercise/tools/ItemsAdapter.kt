package com.example.kotlincodingexercise.tools

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincodingexercise.R
import com.example.kotlincodingexercise.activities.ItemsActivity
import com.example.kotlincodingexercise.datamodels.Item

class ItemsAdapter(private val context: Context, private val items: ArrayList<Item>)
    : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = itemView.findViewById(R.id.item_id)
        val nameView: TextView = itemView.findViewById(R.id.item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = items[position]
        holder.idView.text = context.getString(R.string.item_id, item.id)
        holder.nameView.text = item.name
    }

    override fun getItemCount(): Int {
        return items.size
    }
}