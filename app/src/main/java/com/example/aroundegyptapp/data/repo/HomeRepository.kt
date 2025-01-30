package com.example.aroundegyptapp.data.repo

import com.example.aroundegyptapp.data.model.Data
import com.example.aroundegyptapp.data.network.ApiService

class HomeRepository (private val apiService: ApiService) {
    suspend fun fetchRecommended(): List<Data> {
        return apiService.getRecommendedExperience().data
    }
    suspend fun fetchRecent(): List<com.example.aroundegyptapp.data.model.recent.Data> {
        return apiService.getMostRecentExperience().data
    }
    suspend fun searchExperience(searchText: String) {
        return apiService.searchExperience(searchText)
    }

    }