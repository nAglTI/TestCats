package com.nagl.test_cats_task.data.source

import com.nagl.test_cats_task.data.model.remote.NetworkCat
import com.nagl.test_cats_task.utils.Result

interface CatRemoteDataSource {
    suspend fun getImagesByPage(page: Int): Result<List<NetworkCat>>
}