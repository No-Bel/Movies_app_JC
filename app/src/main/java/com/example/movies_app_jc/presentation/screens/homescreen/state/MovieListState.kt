package com.example.movies_app_jc.presentation.screens.homescreen.state

import com.example.movies_app_jc.domain.model.Movie

data class MovieListState(

    val isLoading: Boolean = false,

    val popularMovieListPage: Int = 1,
    val upcomingMovieListPage: Int = 1,

    val isCurrentPopularScreen: Boolean = true,

    val popularMovieList: List<Movie> = emptyList(),
    val upcomingMovieList: List<Movie> = emptyList()
)