package com.nagl.test_cats_task.di

import com.nagl.test_cats_task.data.source.CatRemoteDataSource
import com.nagl.test_cats_task.data.source.CatRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    // TODO: provide required dependencies
    @Binds
    abstract fun bindCatRemoteDataSource(catRemoteDataSource: CatRemoteDataSourceImpl): CatRemoteDataSource
}