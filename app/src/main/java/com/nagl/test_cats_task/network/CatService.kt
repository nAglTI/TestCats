package com.nagl.test_cats_task.network

import com.nagl.test_cats_task.data.model.remote.NetworkCat
import com.nagl.test_cats_task.utils.NetworkUtils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {
    @GET(NetworkUtils.IMAGES + "search")
    suspend fun getCatsImagesByPage(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 30,
        @Query("order") order: String = "ASC"
    ): Response<List<NetworkCat>>
}