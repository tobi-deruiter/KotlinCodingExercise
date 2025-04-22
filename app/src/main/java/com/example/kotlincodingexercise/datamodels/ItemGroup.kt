package com.example.kotlincodingexercise.datamodels

import java.io.Serializable

data class ItemGroup(
    val items: ArrayList<Item>
) : Serializable
