package com.example.movies_app_jc.presentation.screens.detailscreen.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_app_jc.domain.repository.MovieRepository
import com.example.movies_app_jc.presentation.screens.detailscreen.state.DetailState
import com.example.movies_app_jc.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val movieId = savedStateHandle.get<Int>("movieId")

    private var _detailState = MutableStateFlow(DetailState())
    val detailState = _detailState.asStateFlow()

    init {
        getMovie(movieId ?: -1)
    }

    private fun getMovie(movieId: Int) {
        viewModelScope.launch {
            _detailState.update {
                it.copy(isLoading = true)
            }

            movieRepository.getMovie(movieId).collectLatest { result ->
                when(result) {
                    is Resource.Loading -> {
                        _detailState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Success -> {
                        result.data?.let { movie ->
                            _detailState.update {
                                it.copy(movie = movie)
                            }
                        }
                    }
                    is Resource.Error -> {
                        _detailState.update {
                            it.copy(isLoading = false)
                        }
                    }
                }
            }
        }
    }
}