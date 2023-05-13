package com.nagl.test_cats_task.data.repository

import com.nagl.test_cats_task.data.model.domain.Cat
import com.nagl.test_cats_task.data.source.CatRemoteDataSource
import com.nagl.test_cats_task.di.scope.IoDispatcher
import com.nagl.test_cats_task.mapper.CatMapper
import com.nagl.test_cats_task.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val catRemoteDataSource: CatRemoteDataSource,
    private val catMapper: CatMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): CatsRepository { // TODO: load and map returned data
    override suspend fun getCatsImage(page: Int): Result<List<Cat>> {
        return withContext(ioDispatcher) {
            when (val response = catRemoteDataSource.getImagesByPage(page)) {
                is Result.Success ->
                    if (response.data != null)
                        Result.Success(catMapper.mapNetworkCatListToDomain(response.data))
                    else
                        Result.Success(null)
                is Result.Error -> response
                else -> Result.Loading
            }
        }
    }

}