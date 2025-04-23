package com.example.kotlincodingexercise.api

import com.google.gson.JsonArray
import retrofit.Call
import retrofit.http.GET

/**
 * RetrofitAPI
 *
 * This interface is used to get the json data from Fetch.
 */
interface RetrofitAPI {
    @GET("/hiring.json")
    fun getData(): Call<JsonArray>?
}