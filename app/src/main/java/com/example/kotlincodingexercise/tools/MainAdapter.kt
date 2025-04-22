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
import com.example.kotlincodingexercise.datamodels.ItemGroup

class MainAdapter(private val context: Context, private val dataManager: DataManager)
        : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listIdView: TextView = itemView.findViewById(R.id.card_listid)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_id_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listId = dataManager.data.keys.toList()[position]
        holder.listIdView.text = listId.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ItemsActivity::class.java)
            intent.putExtra("listId", listId)
            intent.putExtra("items", ItemGroup(dataManager.data[listId] ?: ArrayList<Item>()))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataManager.data.keys.size
    }
}