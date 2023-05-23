package com.nagl.test_cats_task.data.model.remote

import com.google.gson.annotations.SerializedName

data class NetworkBreed(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin")
    val country: String,
    @SerializedName("temperament")
    val temperament: String,
    @SerializedName("life_span")
    val lifeSpan: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("wikipedia_url")
    val wikiUrl: String,
)
