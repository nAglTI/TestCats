package com.nagl.test_cats_task.data.repository

import com.nagl.test_cats_task.data.source.CatRemoteDataSource
import com.nagl.test_cats_task.mapper.CatMapper
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val catRemoteDataSource: CatRemoteDataSource,
    private val catMapper: CatMapper
): CatsRepository { // TODO: load and map returned data

}