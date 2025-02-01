package com.example.aroundegyptapp.ui.theme.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.com.example.aroundegyptapp.data.model.recommended.Data
import com.example.aroundegyptapp.data.repo.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    private val _allExperiences = MutableStateFlow<List<Data>>(emptyList())
    private val _searchQuery = MutableStateFlow<List<Data>>(emptyList())
    val searchResult: StateFlow<List<Data>> = _searchQuery

    private val _likesMap = MutableStateFlow<Map<String, Int>>(emptyMap())
    val likesMap: StateFlow<Map<String, Int>> = _likesMap

    val recommended: StateFlow<List<Data>> =
        _allExperiences.map { list -> list.filter { it.recommended == 1 } }.stateIn(
            viewModelScope, SharingStarted.Lazily, emptyList()
        )

    val recent: StateFlow<List<Data>> =
        _allExperiences.map { list -> list.filter { it.recommended == 0 } }.stateIn(
            viewModelScope, SharingStarted.Lazily, emptyList()
        )


    init {
        fetchExperiences()
    }


    private fun fetchExperiences() {
        viewModelScope.launch {
            try {
                _allExperiences.value = repository.fetchExperiences()
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching experiences: ${e.message}")
            }
        }
    }


    init {
        fetchExperiences()
    }

    fun likeExperience(postId: String) {
        viewModelScope.launch {
            try {
                val newLikes = repository.likeExperience(postId)
                _likesMap.value = _likesMap.value.toMutableMap().apply {
                    put(postId, newLikes)
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error liking post: ${e.message}")
            }
        }
    }

    fun executeSearch(query: String) {
        viewModelScope.launch {
            try {
                val results = repository.searchExperience(query)
                _searchQuery.value = results
            } catch (e: Exception) {
                e.message
            }

        }
    }

    fun clearSearch() {
        _searchQuery.value = emptyList()
    }
}