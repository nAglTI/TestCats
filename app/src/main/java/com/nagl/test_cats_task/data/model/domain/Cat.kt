package com.nagl.test_cats_task.data.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    val id: String,
    val url: String,
    val breeds: Breed,
) : Parcelable
