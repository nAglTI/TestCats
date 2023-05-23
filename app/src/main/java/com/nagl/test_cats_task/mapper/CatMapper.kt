package com.nagl.test_cats_task.mapper

import com.nagl.test_cats_task.data.model.domain.Breed
import com.nagl.test_cats_task.data.model.domain.Cat
import com.nagl.test_cats_task.data.model.remote.NetworkBreed
import com.nagl.test_cats_task.data.model.remote.NetworkCat

interface CatMapper {
    suspend fun mapNetworkCatListToDomain(
        networkCatList: List<NetworkCat>
    ): List<Cat>

    suspend fun mapNetworkCatToDomain(
        networkCat: NetworkCat
    ): Cat

    suspend fun mapNetworkBreedToDomain(
        networkBreed: NetworkBreed
    ): Breed
}