package com.example.kotlincodingexercise.activities

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincodingexercise.R
import com.example.kotlincodingexercise.datamodels.Item
import com.example.kotlincodingexercise.datamodels.ItemGroup
import com.example.kotlincodingexercise.tools.ItemsAdapter
import java.util.ArrayList

/**
 * ItemsActivity
 *
 * This class is an activity to show all items with the given listId. Both the group of items and
 * listId are passed through Intent.
 * After getting the listId and itemGroup through Intent, the activity page title is set and
 * an adapter is made and added to the RecyclerView on the activity_items layout.
 */
class ItemsActivity : AppCompatActivity() {
    /**
     * onCreate
     *
     * Create the activity, get the listId and itemGroup from Intent, and display
     */
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

    /**
     * addAdapter
     *
     * This function will take the list items given to create an ItemsAdapter and add it to the
     * recyclerview.
     */
    private fun addAdapter(items: ArrayList<Item>) {
        findViewById<ProgressBar>(R.id.listIdProgressBar).visibility = View.GONE

        val adapter = ItemsAdapter(this@ItemsActivity, items)

        val recyclerview: RecyclerView = findViewById(R.id.itemsRecyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter
    }
}