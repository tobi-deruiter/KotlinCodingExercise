package com.example.kotlincodingexercise.tools

import android.content.Context
import android.widget.Toast
import com.example.kotlincodingexercise.api.RetrofitAPI
import com.example.kotlincodingexercise.datamodels.Item
import com.google.gson.JsonArray
import org.json.JSONObject
import retrofit.Call
import retrofit.Callback
import retrofit.GsonConverterFactory
import retrofit.Response
import retrofit.Retrofit
import java.io.Serializable

/**
 * DataManager
 *
 * This class is used to retrieve, store, and manage the json data from the Retrofit API call.
 */
class DataManager(val context: Context): Serializable {
    // class variables
    private var jsonData: JsonArray = JsonArray()
    var data: MutableMap<Int, ArrayList<Item>> = mutableMapOf()

    /**
     * getData
     *
     * This function makes the API call through Retrofit to get the json data from Fetch.
     * It takes a callback function as a parameter so once the data is retrieved it can be added
     * to the main adapter and displayed on the app.
     */
    fun getData(callback: () -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)
        val call: Call<JsonArray>? = retrofitAPI.getData()

        call!!.enqueue(object : Callback<JsonArray> {
            override fun onResponse(response: Response<JsonArray>?, retrofit: Retrofit?) {
                if (response?.isSuccess == true) {
                    jsonData = response.body()
                    convertJsonDataToSortedItems()
                    callback()
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context, "Failed to retrieve json data...", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    /**
     * convertToSortedItems
     *
     * This function takes the jsonData from the API call and sorts it as described
     * (by listId then by name) into a Map for easy use.
     */
    private fun convertJsonDataToSortedItems() {
        jsonData.let {
            for (i in 0 until it.size()) {
                val item = JSONObject(it.get(i).toString())
                if (item.getString("name") != "null" && item.getString("name") != "") {
                    val id = item.getInt("id")
                    val listId = item.getInt("listId")
                    val name = item.getString("name")

                    if (!data.keys.contains(listId)) {
                        data[listId] = ArrayList<Item>()
                    }
                    data[listId]?.add(Item(id, listId, name))
                }
            }
        }

        data = data.toSortedMap()
        for (key in data.keys) {
            data[key]?.sortBy { it.name }
        }
    }
}