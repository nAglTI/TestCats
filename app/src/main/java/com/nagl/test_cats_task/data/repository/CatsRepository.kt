package com.nagl.test_cats_task.data.repository

import com.nagl.test_cats_task.data.model.domain.Cat
import com.nagl.test_cats_task.utils.Result

interface CatsRepository {
    suspend fun getCatsImage(page: Int): Result<List<Cat>>
}