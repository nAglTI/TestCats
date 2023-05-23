package com.nagl.test_cats_task.data.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Breed(
    val id: String,
    val name: String,
    val country: String,
    val temperament: String,
    val lifeSpan: String,
    val description: String,
    val wikiUrl: String,
): Parcelable
