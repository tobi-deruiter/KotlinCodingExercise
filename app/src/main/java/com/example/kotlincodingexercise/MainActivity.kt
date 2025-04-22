package com.example.kotlincodingexercise

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincodingexercise.api.RetrofitAPI
import com.example.kotlincodingexercise.datamodels.Item
import com.example.kotlincodingexercise.tools.MainAdapter
import com.google.gson.JsonArray
import org.json.JSONObject
import retrofit.Call
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recyclerview)) { v, insets ->
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

        getData()
    }

    private fun getData() {
        var data: JsonArray = JsonArray()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)
        val call: Call<JsonArray>? = retrofitAPI.getData()

        call!!.enqueue(object : Callback<JsonArray> {
            override fun onResponse(response: Response<JsonArray>?, retrofit: Retrofit?) {
                if (response?.isSuccess == true) {
                    findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                    data = response.body()
                    addAdapter(data)
                }
            }

            override fun onFailure(t: Throwable?) {
                findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
                Toast.makeText(this@MainActivity, "Failed to retrieve json data...", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    private fun addAdapter(jsonData: JsonArray) {
        val data = ArrayList<Item>()
        jsonData.let {
            for (i in 0 until it.size()) {
                val item = JSONObject(it.get(i).toString())
                data.add(Item(item.getInt("id"), item.getInt("listId"), item.getString("name")))
            }
        }
        val adapter = MainAdapter(data)

        val recyclerview: RecyclerView = findViewById(R.id.recyclerview)
        recyclerview.layoutManager = GridLayoutManager(this, 2)
        recyclerview.adapter = adapter
    }


}