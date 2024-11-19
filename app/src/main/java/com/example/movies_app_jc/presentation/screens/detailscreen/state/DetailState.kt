package com.example.movies_app_jc.presentation.screens.detailscreen.state

import com.example.movies_app_jc.domain.model.Movie

data class DetailState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)