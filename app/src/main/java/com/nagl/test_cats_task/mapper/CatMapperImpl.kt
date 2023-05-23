package com.nagl.test_cats_task.mapper

import com.nagl.test_cats_task.data.model.domain.Breed
import com.nagl.test_cats_task.data.model.domain.Cat
import com.nagl.test_cats_task.data.model.remote.NetworkBreed
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
            url = networkCat.url,
            breeds = mapNetworkBreedToDomain(networkCat.breeds[0])
        )
    }

    override suspend fun mapNetworkBreedToDomain(networkBreed: NetworkBreed): Breed {
        return Breed(
            id = networkBreed.id,
            name = networkBreed.name,
            temperament = networkBreed.temperament,
            country = networkBreed.country,
            description = networkBreed.description,
            wikiUrl = networkBreed.wikiUrl,
            lifeSpan = networkBreed.lifeSpan,
        )
    }

}