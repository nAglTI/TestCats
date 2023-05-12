package com.nagl.test_cats_task.mapper

import com.nagl.test_cats_task.data.model.domain.Cat
import com.nagl.test_cats_task.data.model.remote.NetworkCat
import com.nagl.test_cats_task.di.scope.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CatMapperImpl @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
): CatMapper {
    override suspend fun mapNetworkCatListToDomain(networkCatList: List<NetworkCat>): List<Cat> {
        return withContext(defaultDispatcher) {
            networkCatList.map {
                mapNetworkCatToDomain(it)
            }
        }
    }

    override suspend fun mapNetworkCatToDomain(networkCat: NetworkCat): Cat {
        return Cat(
            id = networkCat.id,
            url = networkCat.url
        )
    }

}