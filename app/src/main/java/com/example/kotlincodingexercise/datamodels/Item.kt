package com.example.kotlincodingexercise.datamodels

import java.io.Serializable

/**
 * Item
 *
 * A data class to store the data for each item retrieved from the API call.
 */
data class Item(
    val id: Int,
    val listId: Int,
    val name: String
) : Serializable