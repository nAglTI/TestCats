package com.nagl.test_cats_task.data.source

import com.nagl.test_cats_task.data.model.remote.NetworkCat
import com.nagl.test_cats_task.di.scope.IoDispatcher
import com.nagl.test_cats_task.network.CatService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import com.nagl.test_cats_task.utils.Result
import java.lang.Exception
import javax.inject.Inject

class CatRemoteDataSourceImpl @Inject constructor(
    private val catService: CatService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): CatRemoteDataSource {
    override suspend fun getImagesByPage(page: Int): Result<List<NetworkCat>> =
        withContext(ioDispatcher) {
            return@withContext try {
                val response = catService.getCatsImagesByPage(page)
                if (response.isSuccessful) {
                    Result.Success(response.body())
                } else Result.Success(null)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

}