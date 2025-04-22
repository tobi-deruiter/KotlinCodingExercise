package com.example.kotlincodingexercise.activities

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincodingexercise.MainActivity
import com.example.kotlincodingexercise.R
import com.example.kotlincodingexercise.datamodels.Item
import com.example.kotlincodingexercise.datamodels.ItemGroup
import com.example.kotlincodingexercise.tools.DataManager
import com.example.kotlincodingexercise.tools.ItemsAdapter
import com.example.kotlincodingexercise.tools.MainAdapter
import java.util.ArrayList

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_items)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.items)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.itemsRecyclerview)) { v, insets ->
            val innerPadding = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            v.setPadding(
                innerPadding.left,
                innerPadding.top,
                innerPadding.right,
                innerPadding.bottom)
            insets
        }

        var listId = intent.extras?.getInt("listId")
        var itemGroup = intent.extras?.getSerializable("items", ItemGroup::class.java)

        findViewById<TextView>(R.id.listId_title).text = getString(R.string.item_activity_title, listId)


        addAdapter(itemGroup?.items ?: ArrayList<Item>())
    }

    private fun addAdapter(items: ArrayList<Item>) {
        findViewById<ProgressBar>(R.id.listIdProgressBar).visibility = View.GONE

        val adapter = ItemsAdapter(this@ItemsActivity, items)

        val recyclerview: RecyclerView = findViewById(R.id.itemsRecyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }
}