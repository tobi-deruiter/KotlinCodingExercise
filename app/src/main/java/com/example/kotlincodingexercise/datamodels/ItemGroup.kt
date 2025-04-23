package com.example.kotlincodingexercise.datamodels

import java.io.Serializable

/**
 * ItemGroup
 *
 * A data class used to send a relevant list of Items to the item activity after
 * clicking on a List Id group.
 */
data class ItemGroup(
    val items: ArrayList<Item>
) : Serializable
