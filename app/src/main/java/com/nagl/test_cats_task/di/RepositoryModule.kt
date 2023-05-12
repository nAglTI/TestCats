package com.nagl.test_cats_task.di

import com.nagl.test_cats_task.data.repository.CatsRepository
import com.nagl.test_cats_task.data.repository.CatsRepositoryImpl
import com.nagl.test_cats_task.mapper.CatMapper
import com.nagl.test_cats_task.mapper.CatMapperImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindCatMapperImpl(catMapperImpl: CatMapperImpl): CatMapper

    @Binds
    abstract fun bindCatsRepositoryImpl(catsRepositoryImpl: CatsRepositoryImpl): CatsRepository

}