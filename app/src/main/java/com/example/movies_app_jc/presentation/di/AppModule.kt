package com.example.movies_app_jc.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.movies_app_jc.data.local.movie.MovieDatabase
import com.example.movies_app_jc.data.remote.MovieApi
import com.example.movies_app_jc.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Singleton
    @Provides
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(MovieApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieDatabase(app: Application): MovieDatabase{
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java,
            "MOVIE_DB"
        ).build()
    }
}