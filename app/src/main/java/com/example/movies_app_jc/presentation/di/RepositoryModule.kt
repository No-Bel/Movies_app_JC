package com.example.movies_app_jc.presentation.di

import com.example.movies_app_jc.data.repository.MovieRepositoryImpl
import com.example.movies_app_jc.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun movieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}