package com.example.aroundegyptapp.ui.theme.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aroundegyptapp.data.model.details.DetailsData
import com.example.aroundegyptapp.data.repo.DetailsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: DetailsRepo) : ViewModel() {
    private val _details = MutableStateFlow<DetailsData?>(null)
    val details: StateFlow<DetailsData?> = _details
    private val _likesMap = MutableStateFlow<Map<String, Int>>(emptyMap())
    val likesMap: StateFlow<Map<String, Int>> = _likesMap

    fun fetchDetails(id: String) {
        viewModelScope.launch {
            try {
                _details.value = repository.getSingleExperience(id)
            } catch (e: Exception) {
                e.message
            }
        }
    }

    fun likeExperience(postId: String) {
        viewModelScope.launch {
            try {
                val newLikes = repository.likeExperience(postId)
                _likesMap.value = _likesMap.value.toMutableMap().apply {
                    put(postId, newLikes)
                }
            } catch (e: Exception) {
                e.message
            }
        }
    }

}
