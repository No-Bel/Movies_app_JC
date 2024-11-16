package com.example.movies_app_jc.presentation.event

sealed interface MovieListUiEvent {
    data class Paginate(val category: String): MovieListUiEvent
    object Navigate: MovieListUiEvent
}