package com.nagl.test_cats_task.data.model.remote

import com.google.gson.annotations.SerializedName


data class NetworkCat(
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("breeds")
    val breeds: List<NetworkBreed>
)