package com.example.aroundegyptapp.ui.theme.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegyptapp.data.model.Data
import com.example.aroundegyptapp.data.repo.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _recommended = MutableStateFlow<List<Data>>(emptyList())
    val recommended: StateFlow<List<Data>> = _recommended

    private val _recent =
        MutableStateFlow<List<com.example.aroundegyptapp.data.model.recent.Data>>(emptyList())
    val recent: StateFlow<List<com.example.aroundegyptapp.data.model.recent.Data>> = _recent

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery
    init {
        fetchRecommended()
        fetchRecents()
        executeSearch()
    }

    private fun executeSearch() {
        viewModelScope.launch {
            searchQuery.collect { query ->
                if (query.isNotEmpty()) {
//                    try {
//                        _searchQuery.value = repository.searchExperience(query)
//                    } catch (e: Exception) {
//                        // Handle error
//                        e.message
//                    }
                }
            }

        }
    }

    private fun fetchRecents() {
        viewModelScope.launch {
            try {
                _recent.value = repository.fetchRecent()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    private fun fetchRecommended() {
        viewModelScope.launch {
            try {
                _recommended.value = repository.fetchRecommended()
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}