package com.example.kotlincodingexercise.api

import com.google.gson.JsonArray
import retrofit.Call
import retrofit.http.GET

interface RetrofitAPI {
    @GET("/hiring.json")
    fun getData(): Call<JsonArray>?
}